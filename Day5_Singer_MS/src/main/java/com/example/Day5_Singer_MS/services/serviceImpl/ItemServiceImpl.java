package com.example.Day5_Singer_MS.services.serviceImpl;

import com.example.Day5_Singer_MS.dto.CommonResponse;
import com.example.Day5_Singer_MS.dto.ItemDto;
import com.example.Day5_Singer_MS.entity.Item;
import com.example.Day5_Singer_MS.exception.InternalServerExceptionHandler;
import com.example.Day5_Singer_MS.exception.ItemNotFoundException;
import com.example.Day5_Singer_MS.exception.RecordExistingException;
import com.example.Day5_Singer_MS.repository.ItemRepository;
import com.example.Day5_Singer_MS.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final String internalServerEx ="Internal server exception: ";
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public ResponseEntity<CommonResponse> getAll() {

        try {

            List<ItemDto> list = itemRepository.findAll()
                    .stream()
                    .map(item -> ItemDto.builder()
                            .itemId(item.getItemId())
                            .itemName(item.getItemName())
                            .itemPrice(item.getItemPrice())
                            .quantity(item.getQuantity())
                            .build()).toList();


            return ResponseEntity.ok(CommonResponse.builder()
                    .data(list)
                    .message("Retrieved all items successfully..")
                    .responseCode(HttpStatus.OK)
                    .build());


        } catch (Exception e) {
            log.error("Exception ", e);
            throw new InternalServerExceptionHandler("All items retrieved failed..");


        }


    }

    @Override
    public ResponseEntity<CommonResponse> saveItem(ItemDto itemDto) {

        if (itemRepository.existsById(itemDto.getItemId())) {
            log.error("Item with ID {} already exists", itemDto.getItemId());
            throw new RecordExistingException("Item with this ID <" + itemDto.getItemId() + "> already exists");

        } else {
            try {
                itemRepository.save(Item.builder()
                        .itemId(itemDto.getItemId())
                        .itemName(itemDto.getItemName())
                        .itemPrice(itemDto.getItemPrice())
                        .quantity(itemDto.getQuantity())
                        .build());

                return new ResponseEntity<>(CommonResponse.builder()
                        .message("Item saved successfully")
                        .responseCode(HttpStatus.CREATED)
                        .data(itemDto)
                        .build(), HttpStatus.CREATED);

            } catch (RuntimeException e) {
                throw new InternalServerExceptionHandler(internalServerEx + e.getMessage());
            }


        }

    }

    @Override
    public ResponseEntity<CommonResponse> updateItem(ItemDto itemDto) {


        if (itemRepository.existsById(itemDto.getItemId())) {
            try {
                itemRepository.save(Item.builder()
                        .itemId(itemDto.getItemId())
                        .itemName(itemDto.getItemName())
                        .itemPrice(itemDto.getItemPrice())
                        .quantity(itemDto.getQuantity())
                        .build());

                return new ResponseEntity<>(CommonResponse.builder()
                        .message("Item Updated successfully..")
                        .responseCode(HttpStatus.OK)
                        .data(itemDto)
                        .build(), HttpStatus.OK);

            } catch (RuntimeException e) {
                throw new InternalServerExceptionHandler(internalServerEx + e.getMessage());
            }
        } else {
            log.error("Item with ID {} not found...", itemDto.getItemId());
            throw new ItemNotFoundException("Item with this ID <" + itemDto.getItemId() + "> not found..");
        }


    }

    @Override
    public ResponseEntity<CommonResponse> deleteItem(int itemId) {

        if (!itemRepository.existsById(itemId)) {
            log.error("Item with ID {} not found", itemId);
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }

        try {

            Optional<Item> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isEmpty()) {
                throw new RuntimeException("Item not found for ID " + itemId);
            }
            Item item = optionalItem.get();


            itemRepository.deleteById(itemId);


            ItemDto itemDto = mapToDto(item);

            CommonResponse commonResponse = CommonResponse.builder()
                    .message("Item Deleted successfully")
                    .responseCode(HttpStatus.OK)
                    .data(itemDto)
                    .build();

            return ResponseEntity.ok(commonResponse);

        } catch (RuntimeException e) {
            throw new InternalServerExceptionHandler(internalServerEx + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CommonResponse> serachItemById(int itemId) {

        if (!itemRepository.existsById(itemId)) {
            log.error("Item with ID {} not found..", itemId);
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }

        try {
            Optional<Item> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isEmpty()) {
                throw new RuntimeException("Item not found for ID " + itemId);
            }

            Item item = optionalItem.get();
            ItemDto itemDto = mapToDto(item);

            CommonResponse commonResponse = CommonResponse.builder()
                    .message("Item Found succesfully..")
                    .responseCode(HttpStatus.FOUND)
                    .data(itemDto)
                    .build();

            return ResponseEntity.ok(commonResponse);


        } catch (RuntimeException e) {
            throw new InternalServerExceptionHandler(internalServerEx + e.getMessage());
        }

    }

    private ItemDto mapToDto(Item item) {
        return ItemDto.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .quantity(item.getQuantity())
                .build();
    }

}
