package com.tim.doordashlite.restaurant;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tim.doordashlite.BaseFragment;
import com.tim.doordashlite.R;
import com.tim.doordashlite.detail.DetailActivity;
import com.tim.doordashlite.model.Restaurant;
import com.tim.doordashlite.restaurant.adapter.RestaurantAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.tim.doordashlite.detail.DetailActivity.RESTAURANT_ID;
import static com.tim.doordashlite.detail.DetailActivity.RESTAURANT_NAME;

/**
 * Created by Home on 5/31/17.
 */

public class RestaurantsFragment extends BaseFragment implements RestaurantsView {

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list) RecyclerView recyclerView;

    @Inject Application application;

    private RestaurantsPresenter presenter;
    private RestaurantAdapter restaurantAdapter;

    public static RestaurantsFragment newInstance() {
        return new RestaurantsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RestaurantsPresenter(application, this);
        restaurantAdapter = new RestaurantAdapter();
        restaurantAdapter.registerCallback(new RestaurantClickCallback(presenter));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(restaurantAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));
        swipeRefreshLayout.setOnRefreshListener(new RestaurantsRefreshListener());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadRestaurants();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.dispose();
    }

    @Override
    public void enableSpinner(boolean enable) {
        swipeRefreshLayout.setRefreshing(enable);
    }

    @Override
    public void setData(List<Restaurant> restaurants) {
        restaurantAdapter.setData(restaurants);
    }

    @Override
    public void goToDetails(int restaurantId, String restaurantName) {
        final Bundle extra = new Bundle();
        extra.putInt(RESTAURANT_ID, restaurantId);
        extra.putString(RESTAURANT_NAME, restaurantName);
        final Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtras(extra);

        startActivity(intent);
    }

    private class RestaurantsRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            presenter.loadRestaurants();
        }
    }
}
