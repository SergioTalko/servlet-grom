package com.dao;

import com.entity.Item;

import java.util.List;

public interface ItemDAO {

    void addItem(Item item);

    void deleteItem(Long itemId);

    void updateItem(Item item);

    List<Item> getAllItems();


    Item getItemById(Long itemId);
}
