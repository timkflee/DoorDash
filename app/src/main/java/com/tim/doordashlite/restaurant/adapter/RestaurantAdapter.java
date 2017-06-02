package com.tim.doordashlite.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tim.doordashlite.R;
import com.tim.doordashlite.model.Restaurant;
import com.tim.doordashlite.restaurant.RestaurantClickCallback;

import java.util.Collections;
import java.util.List;

/**
 * Created by Home on 5/31/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant> restaurants = Collections.emptyList();
    private RestaurantClickCallback restaurantClickCallback;

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view, restaurantClickCallback);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bind(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    @Override
    public void onViewDetachedFromWindow(RestaurantViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void setData(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    public void registerCallback(RestaurantClickCallback restaurantClickCallback) {
        this.restaurantClickCallback = restaurantClickCallback;
    }
}
