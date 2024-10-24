package com.akademi.spring.dataAccess;

import com.akademi.spring.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTableDao extends JpaRepository<RestaurantTable, Integer> {

    List<RestaurantTable> findAllRestaurantTablesByRestaurantId(int id);


}
