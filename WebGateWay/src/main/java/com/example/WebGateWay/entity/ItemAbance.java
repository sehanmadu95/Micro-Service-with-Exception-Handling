package com.example.WebGateWay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemAbance {
    private int itemId;
    private String itemName;
    private String itemPrice;
    private String itemQuantity;
}
