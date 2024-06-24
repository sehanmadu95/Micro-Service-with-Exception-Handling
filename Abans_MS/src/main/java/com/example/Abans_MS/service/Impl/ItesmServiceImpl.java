package com.example.Abans_MS.service.Impl;



import com.example.Abans_MS.dto.CommonResponse;
import com.example.Abans_MS.dto.ItemAbanceDto;
import com.example.Abans_MS.entity.ItemAbance;
import com.example.Abans_MS.exception.InternalServerExceptionHandler;
import com.example.Abans_MS.exception.ItemNotFoundException;
import com.example.Abans_MS.exception.NullObjectFound;
import com.example.Abans_MS.exception.RecordExistingException;
import com.example.Abans_MS.repository.ItemRepository;
import com.example.Abans_MS.service.ItemService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItesmServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItesmServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public ResponseEntity<CommonResponse> getAll() {

        if(itemRepository.findAll().isEmpty()){
            log.error("No data found in abance DB");
            throw new ItemNotFoundException("NO data found in abance DB");
        }

        try{
            List<ItemAbanceDto> itemAbanceDtoList=itemRepository.findAll()
                    .stream()
                    .map(itemAbance -> ItemAbanceDto.builder()
                            .itemId(itemAbance.getItemId())
                            .itemName(itemAbance.getItemName())
                            .itemPrice(itemAbance.getItemPrice())
                            .itemQuantity(itemAbance.getItemQuantity())
                            .build()).toList();

            if(itemAbanceDtoList.isEmpty()){
                throw  new ItemNotFoundException("NO data found in abance DB");
            }
            else {
                return new ResponseEntity<>(CommonResponse.builder()
                        .data(itemAbanceDtoList)
                        .message("All data Retrieved Successfully..")
                        .responseCode(HttpStatus.FOUND).build(),HttpStatus.FOUND);
            }

        }catch (RuntimeException e){
            throw new InternalServerExceptionHandler("Internal Server Error: "+e.getMessage());
        }




    }

    @Override
    public ResponseEntity<CommonResponse> saveItem(ItemAbanceDto itemAbanceDto) {
            log.info("hit save serviceImpl class");
        if(itemAbanceDto.getItemName()==null || itemAbanceDto.getItemPrice()==null || itemAbanceDto.getItemQuantity()==null){
            log.info("hit save null check");
            throw new NullObjectFound("Cannot save null data");
        }
        else {
            if (itemRepository.existsById(itemAbanceDto.getItemId())){
                log.error("Item with ID {} already exists", itemAbanceDto.getItemId());
                throw new RecordExistingException("Item with this ID <" + itemAbanceDto.getItemId() + "> already exists");
            }
            try {
                itemRepository.save(mapToItem(itemAbanceDto));

                return new ResponseEntity<>(CommonResponse.builder()
                        .data(itemAbanceDto)
                        .message("Data Saving successfully.")
                        .responseCode(HttpStatus.OK)
                        .build(),HttpStatus.OK);
            }
            catch (RuntimeException e){
                throw new InternalServerExceptionHandler("Internal Server Error: "+e.getMessage());
            }
        }


    }

    @Override
    public ResponseEntity<CommonResponse> updateItem(ItemAbanceDto itemAbanceDto) {
        return null;
    }

    @Override
    public ResponseEntity<CommonResponse> deleteItem(int id) {
        return null;
    }

    @Override
    public ResponseEntity<CommonResponse> searchById(int id) {
        return null;
    }

    private ItemAbance mapToItem(ItemAbanceDto itemAbanceDto){
        return ItemAbance.builder()
                .itemId(itemAbanceDto.getItemId())
                .itemName(itemAbanceDto.getItemName())
                .itemPrice(itemAbanceDto.getItemPrice())
                .itemQuantity(itemAbanceDto.getItemQuantity())
                .build();
    }

    private ItemAbanceDto mapToDto(ItemAbance itemAbance){
        return ItemAbanceDto.builder()
                .itemId(itemAbance.getItemId())
                .itemName(itemAbance.getItemName())
                .itemPrice(itemAbance.getItemPrice())
                .itemQuantity(itemAbance.getItemQuantity())
                .build();
    }
}
