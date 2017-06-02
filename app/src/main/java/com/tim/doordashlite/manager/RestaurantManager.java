package com.tim.doordashlite.manager;

import com.tim.doordashlite.model.Restaurant;
import com.tim.doordashlite.network.DoorDashService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Home on 5/31/17.
 */
@Singleton
public class RestaurantManager {

    @Inject DoorDashService doorDashService;

    public Flowable<List<Restaurant>> getRestaurantsObservable() {
        Map<String, String> queries = new HashMap<>();
        queries.put("lat", "37.422740");
        queries.put("lng", "-122.139956");

        return doorDashService.getRestaurants(queries)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Restaurant> getRestaurantObservable(int restaurantId) {
        return doorDashService.getRestaurantDetail(restaurantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
