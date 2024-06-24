package com.example.WebGateWay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {
    private int itemId;
    private String itemName;
    private String itemPrice;
    private int quantity;
}
