package com.example.Day5_Singer_MS.controller;

import com.example.Day5_Singer_MS.dto.CommonResponse;
import com.example.Day5_Singer_MS.dto.ItemDto;
import com.example.Day5_Singer_MS.services.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/d5/singer")
public class singerItemsController {

    private final ItemService itemService;

    @Autowired
    public singerItemsController(ItemService itemService) {

        this.itemService = itemService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<CommonResponse> getAllItems() {

        return itemService.getAll();
    }


    @GetMapping("/getItem/{id}")
    public ResponseEntity<CommonResponse> getItemsById(@PathVariable int id) {
        return itemService.serachItemById(id);
    }


    @PostMapping("/saveItem")
    public ResponseEntity<CommonResponse> saveItem(@RequestBody ItemDto itemDto){
        return itemService.saveItem(itemDto);
    }


    @PutMapping("/updateItem")
    public ResponseEntity<CommonResponse> updateItem(@RequestBody ItemDto itemDto){
        return itemService.updateItem(itemDto);
    }


    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<CommonResponse> deleteItem(@PathVariable int id){
        return itemService.deleteItem(id);
    }


    @GetMapping("/check")
    public String get() {

        return "itemService.getAll()";
    }


}
