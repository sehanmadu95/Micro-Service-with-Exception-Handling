package com.example.Abans_MS.controller;

import com.example.Abans_MS.dto.CommonResponse;
import com.example.Abans_MS.dto.ItemAbanceDto;
import com.example.Abans_MS.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/d5/abance")
@Slf4j
public class AbanceItemController {

    private  final ItemService itemService;

    @Autowired
    public AbanceItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<CommonResponse> getAll(){
        return itemService.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<CommonResponse> saveItem(@RequestBody ItemAbanceDto itemAbanceDto){
        log.info("hit save controller");
        return itemService.saveItem(itemAbanceDto);
    }
}
