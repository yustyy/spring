package com.akademi.spring.business.abstracts;

import com.akademi.spring.entities.RestaurantTable;
import com.akademi.spring.entities.dtos.AddRestaurantTableDto;

import java.util.List;

public interface RestaurantTableService {


    RestaurantTable addRestaurantTable(AddRestaurantTableDto addRestaurantTableDto);

    RestaurantTable getRestaurantTableById(int id);

    List<RestaurantTable> getAllRestaurantTables();

    List<RestaurantTable> getRestaurantTablesByRestaurantId(int id);


    RestaurantTable reserveTable(int id, String reservedBy);


}
