package com.balaji.utils;

import com.balaji.items.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InventoryInitializer {

    private InventoryInitializer() {

    }

    public static List<Item> buildInventory() {
        List<Item> items = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src\\main\\resouces\\items.json"));


            ObjectMapper objectMapper = new ObjectMapper();

            for (String line : lines) {
                Item item = objectMapper.readValue(line, Item.class);
                items.add(item);
            }

            return items;

        } catch (IOException ex) {
            System.exit(-1);
            System.out.println("Initializer error");
        }

        return items;
    }

}
