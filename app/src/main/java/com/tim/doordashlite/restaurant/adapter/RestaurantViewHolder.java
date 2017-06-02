package com.tim.doordashlite.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tim.doordashlite.R;
import com.tim.doordashlite.model.Restaurant;
import com.tim.doordashlite.restaurant.RestaurantClickCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Home on 5/31/17.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image) ImageView image;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.subtitle) TextView subtitle;
    @BindView(R.id.status) TextView status;

    private RestaurantClickCallback restaurantClickCallback;

    public RestaurantViewHolder(View itemView, RestaurantClickCallback restaurantClickCallback) {
        super(itemView);
        this.restaurantClickCallback = restaurantClickCallback;
        ButterKnife.bind(this, itemView);
    }

    public void bind(Restaurant restaurant) {
        itemView.setOnClickListener(new RestaurantClickListener(restaurant));
        title.setText(restaurant.name);
        subtitle.setText(restaurant.description);

        Glide.with(itemView.getContext())
                .load(restaurant.coverImgUrl)
                .fitCenter()
                .into(image);
        status.setText(restaurant.status);
    }

    public void unbind() {
        itemView.setOnClickListener(null);
    }

    private class RestaurantClickListener implements View.OnClickListener {

        private final Restaurant restaurant;

        public RestaurantClickListener(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        @Override
        public void onClick(View v) {
            if (restaurantClickCallback != null) {
                restaurantClickCallback.onRestaurantClick(restaurant);
            }
        }
    }
}
