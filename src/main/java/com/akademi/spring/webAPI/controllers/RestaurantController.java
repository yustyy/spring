package com.akademi.spring.webAPI.controllers;

import com.akademi.spring.business.abstracts.RestaurantService;
import com.akademi.spring.entities.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping("/addRestaurant")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {

        var result = restaurantService.addRestaurant(restaurant);

        if (result == null) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.status(201).body(result);

    }



    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
        var result = restaurantService.getRestaurantById(id);

        if (result == null) {
            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<Iterable<Restaurant>> getAllRestaurants() {
        var result = restaurantService.getAllRestaurants();

        if (result == null) {
            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);

        return ResponseEntity.status(204).body(null);
    }
}
