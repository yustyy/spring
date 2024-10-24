package com.akademi.spring.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRestaurantTableDto {

    private int capacity;

    private int restaurantId;


}
