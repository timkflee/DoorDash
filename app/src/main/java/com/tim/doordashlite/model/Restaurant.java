package com.tim.doordashlite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tim.doordashlite.database.FavoriteDao;

import java.util.List;

/**
 * Created by Home on 5/31/17.
 */
@DatabaseTable(tableName = "favorite", daoClass = FavoriteDao.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    @JsonProperty("is_time_surging")
    public Boolean isTimeSurging;
    @JsonProperty("max_order_size")
    public Object maxOrderSize;
    @DatabaseField
    @JsonProperty("delivery_fee")
    public Integer deliveryFee;
    @JsonProperty("max_composite_score")
    public Integer maxCompositeScore;
    @DatabaseField(id = true)
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("average_rating")
    public Double averageRating;
    @JsonProperty("menus")
    public List<Menu> menus = null;
    @JsonProperty("composite_score")
    public Integer compositeScore;
    @JsonProperty("status_type")
    public String statusType;
    @JsonProperty("is_only_catering")
    public Boolean isOnlyCatering;
    @DatabaseField
    @JsonProperty("status")
    public String status;
    @JsonProperty("number_of_ratings")
    public Integer numberOfRatings;
    @JsonProperty("asap_time")
    public Integer asapTime;
    @DatabaseField
    @JsonProperty("description")
    public String description;
    @JsonProperty("business")
    public Business business;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    @JsonProperty("tags")
    public String[] tags = null;
    @JsonProperty("yelp_review_count")
    public Integer yelpReviewCount;
    @JsonProperty("business_id")
    public Integer businessId;
    @JsonProperty("extra_sos_delivery_fee")
    public Integer extraSosDeliveryFee;
    @JsonProperty("yelp_rating")
    public Double yelpRating;
    @DatabaseField
    @JsonProperty("cover_img_url")
    public String coverImgUrl;
    @JsonProperty("header_img_url")
    public String headerImgUrl;
    @JsonProperty("address")
    public Address address;
    @JsonProperty("price_range")
    public Integer priceRange;
    @JsonProperty("slug")
    public String slug;
    @DatabaseField
    @JsonProperty("name")
    public String name;
    @JsonProperty("is_newly_added")
    public Boolean isNewlyAdded;
    @JsonProperty("url")
    public String url;
    @JsonProperty("service_rate")
    public Integer serviceRate;
    @JsonProperty("promotion")
    public Object promotion;
    @JsonProperty("featured_category_description")
    public Object featuredCategoryDescription;


    public Restaurant() {

    }
}
