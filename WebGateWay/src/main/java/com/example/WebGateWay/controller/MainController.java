package com.example.WebGateWay.controller;

import com.example.WebGateWay.entity.Item;
import com.example.WebGateWay.entity.ResponseItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/d5/ms")
public class MainController {

    @GetMapping("/get")
    public String getString(){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject("http://localhost:8080/api/d5/singer/check"+"", String.class);

    }


    @GetMapping("/getSingerAll")
    public ResponseItem getSingerAllItems() {
        RestTemplate restTemplate=new RestTemplate();
        String url = "http://localhost:8080/api/d5/singer/getAll";
        ResponseItem listItem= restTemplate.getForObject(url, ResponseItem.class);

        List<Item> itemL=listItem.getData();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Items:\n");
        for (Item item : itemL) {
            stringBuilder.append("Item ID: ").append(item.getItemId()).append("\n");
            stringBuilder.append("Item Name: ").append(item.getItemName()).append("\n");
            stringBuilder.append("Item Price: ").append(item.getItemPrice()).append("\n");
            stringBuilder.append("Quantity: ").append(item.getQuantity()).append("\n\n");
        }
       log.info(stringBuilder.toString());
        return listItem;
    }


    @GetMapping("/getAbanceAll")
    public ResponseItem getAbanceAllItems() {
        RestTemplate restTemplateAbance=new RestTemplate();
        String url = "http://localhost:8081/api/d5/abance/getAll";
        ResponseItem listItem= restTemplateAbance.getForObject(url, ResponseItem.class);

        List<Item> itemL=listItem.getData();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Items:\n");
        for (Item item : itemL) {
            stringBuilder.append("Item ID: ").append(item.getItemId()).append("\n");
            stringBuilder.append("Item Name: ").append(item.getItemName()).append("\n");
            stringBuilder.append("Item Price: ").append(item.getItemPrice()).append("\n");
            stringBuilder.append("Quantity: ").append(item.getQuantity()).append("\n\n");
        }
        log.info(stringBuilder.toString());
        return listItem;
    }


    @GetMapping("/getAll")
    public ResponseItem getAllItems() {
        RestTemplate restTemplateAbance=new RestTemplate();
        String urlAbance = "http://localhost:8081/api/d5/abance/getAll";
        ResponseItem listItemAbance= restTemplateAbance.getForObject(urlAbance, ResponseItem.class);
        List<Item> itemAbance=listItemAbance.getData();


        RestTemplate restTemplateSinger=new RestTemplate();
        String urlSinger = "http://localhost:8080/api/d5/singer/getAll";
        ResponseItem listItemSinger= restTemplateSinger.getForObject(urlSinger, ResponseItem.class);
        List<Item> itemSinger=listItemSinger.getData();


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n==== Items Details =====\n");
        stringBuilder.append("\n **** Items In Abance Stores ****\n");
        for (Item item : itemAbance) {
            stringBuilder.append("Item ID: ").append(item.getItemId()).append("\n");
            stringBuilder.append("Item Name: ").append(item.getItemName()).append("\n");
            stringBuilder.append("Item Price: ").append(item.getItemPrice()).append("\n");
            stringBuilder.append("Quantity: ").append(item.getQuantity()).append("\n\n");
        }



        stringBuilder.append("\n **** Items In Singer Stores ****\n");
        for (Item item : itemSinger) {
            stringBuilder.append("Item ID: ").append(item.getItemId()).append("\n");
            stringBuilder.append("Item Name: ").append(item.getItemName()).append("\n");
            stringBuilder.append("Item Price: ").append(item.getItemPrice()).append("\n");
            stringBuilder.append("Quantity: ").append(item.getQuantity()).append("\n\n");
        }
        log.info(stringBuilder.toString());
        return listItemAbance  ;
    }


    //http://localhost:8081/api/d5/abance/getAll
}
