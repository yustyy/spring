package com.akademi.spring.business.abstracts;

import com.akademi.spring.entities.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(int id);

    List<Restaurant> getAllRestaurants();

    void deleteRestaurant(int id);


}
