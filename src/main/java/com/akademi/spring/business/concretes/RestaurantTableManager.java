package com.akademi.spring.business.concretes;

import com.akademi.spring.business.abstracts.RestaurantService;
import com.akademi.spring.business.abstracts.RestaurantTableService;
import com.akademi.spring.dataAccess.RestaurantTableDao;
import com.akademi.spring.entities.RestaurantTable;
import com.akademi.spring.entities.dtos.AddRestaurantTableDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableManager implements RestaurantTableService {

    private final RestaurantTableDao restaurantTableDao;

    private final RestaurantService restaurantService;

    public RestaurantTableManager(RestaurantTableDao restaurantTableDao, RestaurantService restaurantService) {
        this.restaurantTableDao = restaurantTableDao;
        this.restaurantService = restaurantService;
    }


    @Override
    public RestaurantTable addRestaurantTable(int capacity, int restaurantId) {


        if (capacity < 1){
            return null;
        }

        if (restaurantId < 1){
            return null;
        }


        var restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant == null){
            return null;
        }

        var restaurantTable = RestaurantTable.builder()
                        .restaurant(restaurant)
                                .capacity(capacity)
                                        .isReserved(false)
                                                .build();


        return restaurantTableDao.save(restaurantTable);


    }

    @Override
    public RestaurantTable getRestaurantTableById(int id) {
       var restaurantTable = restaurantTableDao.findById(id);

       if (!restaurantTable.isPresent()){
           return null;
       }

         return restaurantTable.get();
    }

    @Override
    public List<RestaurantTable> getAllRestaurantTables() {
        var restaurantTables = restaurantTableDao.findAll();

        if (restaurantTables.isEmpty()){
            return null;
        }

        return restaurantTables;
    }

    @Override
    public List<RestaurantTable> getRestaurantTablesByRestaurantId(int id) {
        var restaurantTables = restaurantTableDao.findAllRestaurantTablesByRestaurantId(id);

        if (restaurantTables == null){
            return null;
        }

        return restaurantTables;
    }

    @Override
    public RestaurantTable reserveTable(int id, String reservedBy) {
        var restaurantTable = getRestaurantTableById(id);

        if (restaurantTable == null){
            return null;
        }

        if (restaurantTable.isReserved()){
            return null;
        }

        restaurantTable.setReserved(true);
        restaurantTable.setReservedBy(reservedBy);

        return restaurantTableDao.save(restaurantTable);
    }

    @Override
    public RestaurantTable cancelReservation(int tableId, String reservedBy) {
        var restaurantTable = getRestaurantTableById(tableId);

        if (restaurantTable == null){
            return null;
        }

        if (!restaurantTable.isReserved()){
            return null;
        }

        if (!restaurantTable.getReservedBy().equals(reservedBy)){
            return null;
        }

        restaurantTable.setReserved(false);
        restaurantTable.setReservedBy(null);

        return restaurantTableDao.save(restaurantTable);
    }

    @Override
    public void deleteRestaurantTable(int id) {
        var restaurantTable = getRestaurantTableById(id);

        if (restaurantTable == null){
            return;
        }

        restaurantTableDao.deleteById(id);

    }
}
