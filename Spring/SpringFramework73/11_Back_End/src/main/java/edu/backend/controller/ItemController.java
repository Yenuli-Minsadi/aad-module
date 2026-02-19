package edu.backend.controller;

import edu.backend.dto.CustomerDTO;
import edu.backend.dto.ItemDTO;
import edu.backend.service.custom.impl.CustomerServiceImpl;
import edu.backend.service.custom.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    private final ItemServiceImpl itemService;
    @PostMapping
    public void saveItem(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        System.out.println("saveItem");
//        System.out.println(customerDTO.getCid());
//        System.out.println(customerDTO.getNcame());
//        System.out.println(customerDTO.getCaddress());
    }

    @PutMapping
    public void updateItem(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
        System.out.println("updateItem");

    }

    @DeleteMapping
    public void deleteItem(@RequestBody ItemDTO itemDTO) {
        itemService.deleteItem(itemDTO.getItemId());
        System.out.println("deleteItem");
    }
}
