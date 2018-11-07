package com;

import com.controller.ItemControllerImpl;
import com.entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {

    private ItemControllerImpl itemController = new ItemControllerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Item item = itemController.getItemById(id);
            if (item.getName() != null) {
                resp.getWriter().println(item.toString());
            } else {
                resp.getWriter().println("Item with id " + id + " cant find in DB");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.getWriter().println("Cant get item " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = parseJSONToObject(req);
        try {
            itemController.addItem(item);
            resp.getWriter().println(item.toString());
        } catch (IOException e) {
            e.printStackTrace();
            resp.getWriter().println("Cant save item with id " + item.getId() + e.getMessage());
        }

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Item item = itemController.getItemById(id);
            if (item.getName() != null) {
                Item itemForUpdate = parseJSONToObject(req);
                itemForUpdate.setId(item.getId());
                itemController.updateItem(itemForUpdate);
                resp.getWriter().println(itemForUpdate.toString());
            } else {
                resp.getWriter().println("Item with id " + id + " not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.getWriter().println("Cant update item  " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Item item = itemController.getItemById(id);
            if (item.getName() != null) {
                itemController.deleteItem(id);
                resp.getWriter().println("Item " + id + " deleted successfully");
            } else {
                resp.getWriter().println("Item with id " + id + " not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.getWriter().println("Deleting item unsuccessful " + e.getMessage());
        }
    }


    private Item parseJSONToObject(HttpServletRequest req) {

        ObjectMapper mapper = new ObjectMapper();
        Item item = null;
        try (InputStream inputStream = req.getInputStream()) {
            item = mapper.readValue(inputStream, Item.class);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return item;
    }
}
