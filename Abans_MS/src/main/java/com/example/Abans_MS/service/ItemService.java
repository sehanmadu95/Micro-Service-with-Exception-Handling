package com.example.Abans_MS.service;

import com.example.Abans_MS.dto.CommonResponse;
import com.example.Abans_MS.dto.ItemAbanceDto;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    ResponseEntity<CommonResponse> getAll();

    ResponseEntity<CommonResponse>saveItem(ItemAbanceDto itemAbanceDto);

    ResponseEntity<CommonResponse> updateItem(ItemAbanceDto itemAbanceDto);

    ResponseEntity<CommonResponse> deleteItem(int id);

    ResponseEntity<CommonResponse> searchById(int id);
}
