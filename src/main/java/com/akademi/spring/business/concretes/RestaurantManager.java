package com.akademi.spring.business.concretes;

import com.akademi.spring.business.abstracts.RestaurantService;
import com.akademi.spring.dataAccess.RestaurantDao;
import com.akademi.spring.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantManager implements RestaurantService {

    private final RestaurantDao restaurantDao;

    public RestaurantManager(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {

        if (restaurant.getName().length() < 3){
            return null;
        }

        return restaurantDao.save(restaurant);

    }

    @Override
    public Restaurant getRestaurantById(int id) {
        Optional<Restaurant> restaurant = restaurantDao.findById(id);

        if (!restaurant.isPresent()){
            return null;
        }

        return restaurant.get();

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
     var restaurants = restaurantDao.findAll();

        if (restaurants.isEmpty()){
            return null;
        }

        return restaurants;
    }

    @Override
    public void deleteRestaurant(int id) {
        var restaurant = getRestaurantById(id);

        if (restaurant == null){
            return;
        }

        restaurantDao.deleteById(id);
    }

}
