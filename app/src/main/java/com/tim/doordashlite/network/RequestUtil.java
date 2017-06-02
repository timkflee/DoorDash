package com.tim.doordashlite.network;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

/**
 * Created by Home on 6/1/17.
 */
@Singleton
public class RequestUtil {

    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lng";

    public Map<String, String> createRestaurantsQueryMap(RestaurantsRequestProperties properties) {
        final Map<String, String> map = new HashMap<>();

        if (properties.lat != null) {
            map.put(LATITUDE, properties.lat.toString());
        }

        if (properties.lng != null) {
            map.put(LONGITUDE, properties.lng.toString());
        }

        return map;
    }
}
