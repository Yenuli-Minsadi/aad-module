package edu.backend.dto;

public class ItemDTO {
    private String itemId;
    private String itemName;
    private String itemQuantity;


    public ItemDTO() {

    }

    public ItemDTO(String itemId, String itemName, String itemQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                '}';
    }
}
