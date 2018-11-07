package com.controller;

import com.entity.Item;
import com.service.ItemServiceImpl;

import java.util.List;

public class ItemControllerImpl implements ItemController {

    private ItemServiceImpl itemService = new ItemServiceImpl();



    @Override
    public void addItem(Item item) {
        itemService.addItem(item);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemService.deleteItem(itemId);
    }

    @Override
    public void updateItem(Item item) {
        itemService.updateItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemService.getItemById(itemId);
    }
}
