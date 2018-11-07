package com.controller;

import com.entity.Item;

import java.util.List;

public interface ItemController {

    void addItem(Item item);

    void deleteItem(Long itemId);

    void updateItem(Item item);

    List<Item> getAllItems();

    Item getItemById(Long itemId);
}
