package com.tim.doordashlite.network;

/**
 * Created by Home on 5/31/17.
 */

public class RestaurantsRequestProperties {

    public Double lat;
    public Double lng;

    public static class Builder {

        private Double lat;
        private Double lng;

        public Builder latitude(double lat) {
            this.lat = lat;
            return this;
        }

        public Builder longitude(double lng) {
            this.lng = lng;
            return this;
        }

        public RestaurantsRequestProperties build() {
            final RestaurantsRequestProperties properties = new RestaurantsRequestProperties();

            if (lat != null) {
                properties.lat = lat;
            }
            if (lng != null) {
                properties.lng = lng;
            }
            return properties;
        }
    }
}
