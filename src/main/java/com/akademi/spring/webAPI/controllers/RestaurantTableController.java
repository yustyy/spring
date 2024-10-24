package com.akademi.spring.webAPI.controllers;

import com.akademi.spring.business.abstracts.RestaurantTableService;
import com.akademi.spring.entities.RestaurantTable;
import com.akademi.spring.entities.dtos.AddRestaurantTableDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantTables")
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    public RestaurantTableController(RestaurantTableService restaurantTableService) {
        this.restaurantTableService = restaurantTableService;
    }

    @PostMapping("/addRestaurantTable")
    public ResponseEntity<RestaurantTable> addRestaurantTable(@RequestBody AddRestaurantTableDto addRestaurantTableDto){

        var result = restaurantTableService.addRestaurantTable(addRestaurantTableDto);

        if (result == null){
            return ResponseEntity.badRequest().body(result);
        }


        return ResponseEntity.status(201).body(result);

    }

    @GetMapping("/getRestaurantTableById/{id}")
    public ResponseEntity<RestaurantTable> getRestaurantTableById(@PathVariable int id){
        var result = restaurantTableService.getRestaurantTableById(id);


        if (result == null){
            return ResponseEntity.status(404).body(result);
        }


        return ResponseEntity.ok(result);
    }


    @GetMapping("/getRestaurantTables/{restaurantId}")
    public ResponseEntity<List<RestaurantTable>> getRestaurantTablesByRestaurantId(@PathVariable int restaurantId){
        var result = restaurantTableService.getRestaurantTablesByRestaurantId(restaurantId);

        if (result == null){
            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllRestaurantTables")
    public ResponseEntity<List<RestaurantTable>> getAllRestaurantTables(){
        var result = restaurantTableService.getAllRestaurantTables();

        if (result == null){
            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/reserveTable/{tableId}")
    public ResponseEntity<RestaurantTable> reserveTable(@PathVariable int tableId, @RequestBody String reservedBy){
        var result = restaurantTableService.reserveTable(tableId, reservedBy);

        if (result == null){
            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(result);
    }





}
