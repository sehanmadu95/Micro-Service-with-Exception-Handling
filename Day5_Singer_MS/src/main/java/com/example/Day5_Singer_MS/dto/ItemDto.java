package com.example.Day5_Singer_MS.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDto {
    private int itemId;
    private String itemName;
    private String itemPrice;
    private int quantity;
}
