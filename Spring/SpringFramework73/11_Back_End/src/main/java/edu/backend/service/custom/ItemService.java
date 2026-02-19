package edu.backend.service.custom;

import edu.backend.dto.CustomerDTO;
import edu.backend.dto.ItemDTO;

public interface ItemService {
    public void saveItem(ItemDTO itemDTO);
    public void updateItem(ItemDTO itemDTO);
    public void deleteItem(String itemId);
    public void getAllItems(ItemDTO itemDTO);
}
