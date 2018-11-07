package com.service;

import com.dao.ItemDAOImpl;
import com.entity.Item;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDAOImpl itemDAO = new ItemDAOImpl();


    @Override
    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemDAO.deleteItem(itemId);
    }

    @Override
    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemDAO.getItemById(itemId);
    }
}
