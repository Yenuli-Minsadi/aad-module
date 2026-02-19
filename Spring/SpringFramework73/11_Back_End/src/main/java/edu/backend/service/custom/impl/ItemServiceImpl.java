package edu.backend.service.custom.impl;

import edu.backend.dto.ItemDTO;
import edu.backend.dto.repository.ItemRepository;
import edu.backend.entity.Item;
import edu.backend.service.custom.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepository.save(new Item(itemDTO.getItemId(),itemDTO.getItemName(),itemDTO.getItemQuantity()));
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        itemRepository.save(new Item(itemDTO.getItemId(),itemDTO.getItemName(),itemDTO.getItemQuantity()));
    }

    @Override
    public void deleteItem(String itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public void getAllItems(ItemDTO itemDTO) {
        itemRepository.findAll();

    }
}
