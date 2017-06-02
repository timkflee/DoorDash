package com.tim.doordashlite.restaurant;

import android.app.Application;

import com.tim.doordashlite.network.RestaurantsRequestProperties;
import com.tim.doordashlite.manager.RestaurantManager;
import com.tim.doordashlite.model.Restaurant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import toothpick.Toothpick;

/**
 * Created by Home on 5/31/17.
 */

public class RestaurantsPresenter {

    private static final double DOORDASH_LAT = 37.422740;
    private static final double DOORDASH_LNG = -122.139956;

    @Inject RestaurantManager restaurantManager;

    private RestaurantsView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RestaurantsPresenter(Application application, RestaurantsView view) {
        Toothpick.inject(this, Toothpick.openScope(application));
        this.view = view;
    }

    public void loadRestaurants() {
        view.enableSpinner(true);
        final RestaurantsRequestProperties properties = new RestaurantsRequestProperties.Builder()
                .latitude(DOORDASH_LAT)
                .longitude(DOORDASH_LNG)
                .build();

        final Disposable disposable = restaurantManager.getRestaurantsObservable(properties)
                .subscribeWith(new DisposableSubscriber<List<Restaurant>>() {
                    @Override
                    public void onNext(List<Restaurant> restaurants) {
                        view.setData(restaurants);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.enableSpinner(false);
                    }

                    @Override
                    public void onComplete() {
                        view.enableSpinner(false);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void onRestaurantClick(Restaurant restaurant) {
        view.goToDetails(restaurant.id, restaurant.name);
    }

    public void dispose() {
        compositeDisposable.clear();
    }
}
