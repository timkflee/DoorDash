package com.tim.doordashlite.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.tim.doordashlite.BaseActivity;
import com.tim.doordashlite.R;
import com.tim.doordashlite.manager.FavoriteManager;
import com.tim.doordashlite.manager.RestaurantManager;
import com.tim.doordashlite.model.Restaurant;

import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Home on 6/1/17.
 */

public class DetailActivity extends BaseActivity {

    public static final String RESTAURANT_ID = "restaurant_id";
    public static final String RESTAURANT_NAME = "restaurant_name";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.image) ImageView coverImage;
    @BindView(R.id.type_text) TextView typeText;
    @BindView(R.id.status_text) TextView statusText;
    @BindView(R.id.price_text) TextView priceText;
    @BindView(R.id.add_button) Button addButton;

    @Inject RestaurantManager restaurantManager;
    @Inject FavoriteManager favoriteManager;

    private int restaurantId;
    private String restaurantName;
    private Restaurant restaurant;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey(RESTAURANT_ID)) {
            restaurantId = bundle.getInt(RESTAURANT_ID);
            restaurantName = bundle.getString(RESTAURANT_NAME);
        } else {
            finish();
        }

        setupToolbar();

        final Disposable disposable = Flowable.fromCallable(new Callable<Boolean>() {
                                            @Override
                                            public Boolean call() throws Exception {
                                                return favoriteManager.favoriteExists(restaurantId);
                                            }
                                        }).subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(@NonNull Boolean aBoolean) throws Exception {
                                                if (aBoolean) {
                                                    addButton.setText(R.string.remove_favorite);
                                                } else {
                                                    addButton.setText(R.string.add_favorite);
                                                }
                                            }
                                        });
        compositeDisposable.add(disposable);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Disposable disposable = restaurantManager.getRestaurantObservable(restaurantId)
                .subscribeWith(new DisposableSubscriber<Restaurant>() {
                    @Override
                    public void onNext(Restaurant restaurant) {
                        DetailActivity.this.restaurant = restaurant;
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        setupDetails(restaurant);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.dispose();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(restaurantName);
    }

    private void setupDetails(final Restaurant restaurant) {
        Glide.with(this)
                .load(restaurant.coverImgUrl)
                .into(coverImage);
        typeText.setText(TextUtils.join(", ", restaurant.tags));
        statusText.setText(restaurant.status);
        priceText.setText(String.format("%.2f", restaurant.deliveryFee / 100f));

        final Disposable disposable = RxView.clicks(addButton)
                                        .debounce(100, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<Object>() {
                                            @Override
                                            public void accept(@NonNull Object o) throws Exception {
                                                if (favoriteManager.favoriteExists(restaurantId)) {
                                                    removeRestaurant(restaurant);
                                                } else {
                                                    addRestaurant(restaurant);
                                                }

                                            }
                                        });
        compositeDisposable.add(disposable);
    }

    private void addRestaurant(Restaurant restaurant) {
        final Disposable disposable = Flowable.just(restaurant)
                .flatMapCompletable(new Function<Restaurant, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull Restaurant restaurant) throws Exception {
                        final boolean added = favoriteManager.addRestaurant(restaurant);
                        if (added) {
                            return Completable.complete();
                        }

                        return Completable.error(new Throwable());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        addButton.setText(R.string.remove_favorite);
                        final Toast toast = Toast.makeText(getApplicationContext(), R.string.favorite_added, Toast.LENGTH_LONG);
                        toast.show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        final Toast toast = Toast.makeText(getApplicationContext(), R.string.favorite_added_error, Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void removeRestaurant(Restaurant restaurant) {
        final Disposable disposable = Flowable.just(restaurant)
                .flatMapCompletable(new Function<Restaurant, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull Restaurant restaurant) throws Exception {
                        final boolean added = favoriteManager.removeRestaurant(restaurant);
                        if (added) {
                            return Completable.complete();
                        }

                        return Completable.error(new Throwable());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        addButton.setText(R.string.add_favorite);
                        final Toast toast = Toast.makeText(getApplicationContext(), R.string.favorite_removed, Toast.LENGTH_LONG);
                        toast.show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        final Toast toast = Toast.makeText(getApplicationContext(), R.string.favorite_removed_error, Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
