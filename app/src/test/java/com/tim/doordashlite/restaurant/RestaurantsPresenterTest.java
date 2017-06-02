package com.tim.doordashlite.restaurant;

import android.app.Application;

import com.tim.doordashlite.manager.RestaurantManager;
import com.tim.doordashlite.model.Restaurant;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import toothpick.testing.ToothPickRule;

/**
 * Created by Home on 6/1/17.
 */

public class RestaurantsPresenterTest extends EasyMockSupport {

    @Rule public EasyMockRule easyMockRule = new EasyMockRule(this);
    @Rule public ToothPickRule toothPickRule = new ToothPickRule(this);

    @Mock Application application;
    @Mock RestaurantsView view;
    @Mock RestaurantManager manager;

    private RestaurantsPresenter presenter;

    @Before
    public void setUp() {
        toothPickRule.setScopeName(application);
        presenter = new RestaurantsPresenter(application, view);
    }

    @Test
    public void onRestaurantClick_should_goToDetails() {
        // GIVEN
        final Restaurant restaurant = new Restaurant();
        restaurant.id = 1;
        restaurant.name = "Restaurant";
        view.goToDetails(restaurant.id, restaurant.name);
        replayAll();

        // WHEN
        presenter.onRestaurantClick(restaurant);

        // THEN
        verifyAll();
    }
}
