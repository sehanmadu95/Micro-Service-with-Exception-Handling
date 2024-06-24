package com.example.Day5_Singer_MS.services;

import com.example.Day5_Singer_MS.dto.CommonResponse;
import com.example.Day5_Singer_MS.dto.ItemDto;
import com.example.Day5_Singer_MS.entity.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemService {
    ResponseEntity<CommonResponse>getAll();
    ResponseEntity<CommonResponse>saveItem(ItemDto itemDto);

    ResponseEntity<CommonResponse> updateItem(ItemDto itemDto);

    ResponseEntity<CommonResponse> deleteItem(int itemId);

    ResponseEntity<CommonResponse> serachItemById(int itenId);
}
