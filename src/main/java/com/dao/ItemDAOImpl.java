package com.dao;

import com.config.DbUtil;
import com.entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private Connection connection;

    public ItemDAOImpl() {
        connection = DbUtil.getConnection();
    }

    public void addItem(Item item) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO item (ITEM_ID,NAME,DATE_CREATED,DATE_UPDATED, DESCRIPTION) VALUES (?,?,?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setLong(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setDate(3, new Date(item.getDateCreated().getTime()));
            preparedStatement.setDate(4, new Date(item.getLastUpdatedDate().getTime()));
            preparedStatement.setString(5, item.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(Long itemId) {

        try (
                PreparedStatement statementStr = connection.prepareStatement("DELETE FROM ITEM WHERE Item_ID = ?")) {


            statementStr.setLong(1, itemId);
            statementStr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateItem(Item item) {

        try (
                PreparedStatement statement = connection.prepareStatement("UPDATE ITEM SET  NAME = ?, DATE_UPDATED = ?, DESCRIPTION = ? WHERE Item_ID = ?")) {


            statement.setString(1, item.getName());
            statement.setDate(2, new Date(item.getLastUpdatedDate().getTime()));
            statement.setString(3, item.getDescription());
            statement.setLong(4, item.getId());
            statement.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ITEM");
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("ITEM_ID"));
                item.setName(rs.getString("NAME"));
                item.setDateCreated(rs.getDate("DATE_CREATED"));
                item.setLastUpdatedDate(rs.getDate("DATE_UPDATED"));
                item.setDescription(rs.getString("DESCRIPTION"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Item getItemById(Long itemId) {

        Item resultItem = new Item();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEM WHERE ITEM_ID = ?")) {
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                resultItem.setId(resultSet.getLong("ITEM_ID"));
                resultItem.setName(resultSet.getString("NAME"));
                resultItem.setDateCreated(resultSet.getDate("DATE_CREATED"));
                resultItem.setLastUpdatedDate(resultSet.getDate("DATE_UPDATED"));
                resultItem.setDescription(resultSet.getString("DESCRIPTION"));

                resultItem.setId(itemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultItem;
    }
}
