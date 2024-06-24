package com.example.Abans_MS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemAbanceDto {
    private int itemId;
    private String itemName;
    private String itemPrice;
    private String itemQuantity;
}
