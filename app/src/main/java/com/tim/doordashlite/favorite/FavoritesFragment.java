package com.tim.doordashlite.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tim.doordashlite.BaseFragment;
import com.tim.doordashlite.R;
import com.tim.doordashlite.manager.FavoriteManager;
import com.tim.doordashlite.model.Restaurant;
import com.tim.doordashlite.restaurant.adapter.RestaurantAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Home on 6/1/17.
 */

public class FavoritesFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<Restaurant>>{

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.list) RecyclerView recyclerView;

    @Inject FavoriteManager favoriteManager;

    private RestaurantAdapter restaurantAdapter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restaurantAdapter = new RestaurantAdapter();
        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(restaurantAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new FavoriteLoader(getContext(), favoriteManager);
    }

    @Override
    public void onLoadFinished(Loader<List<Restaurant>> loader, List<Restaurant> data) {
        restaurantAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
