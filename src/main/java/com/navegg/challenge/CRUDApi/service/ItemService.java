package com.navegg.challenge.CRUDApi.service;


import com.navegg.challenge.CRUDApi.model.Item;
import com.navegg.challenge.CRUDApi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItems() {

        return itemRepository.findAll();

    }

    public Item getItemById(String id) {

        return itemRepository.findByIdFetchCollections(id);

    }

    public Item saveItem(Item item) {

        item.getUrlList().forEach(url -> url.setItem(item));
        item.getCategoryList().forEach(category -> category.setItem(item));

        return itemRepository.save(item);

    }

    public Item updateItem(String id, Item item) {

        itemRepository.deleteById(id);

        item.getUrlList().forEach(url -> url.setItem(item));
        item.getCategoryList().forEach(category -> category.setItem(item));

        return itemRepository.save(item);

    }

    public Item deleteItem(String id) {

        Item deletedItem = itemRepository.findByIdFetchCollections(id);

        itemRepository.deleteById(id);

        return deletedItem;

    }

    public boolean siteNameExist(String name){

        List<String> nameList = itemRepository.findAll().stream().map(item -> item.getName()).collect(Collectors.toList());

        if(nameList.contains(name)) return true;

        else return false;

    }
}
