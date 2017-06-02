package com.tim.doordashlite.network;

import com.tim.doordashlite.model.Restaurant;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Home on 5/31/17.
 */

public interface DoorDashService {

    @GET("v2/restaurant/")
    Flowable<List<Restaurant>> getRestaurants(@QueryMap Map<String, String> queries);

    @GET("v2/restaurant/{id}/")
    Flowable<Restaurant> getRestaurantDetail(@Path("id") int restaurantId);
}
