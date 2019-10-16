package com.navegg.challenge.CRUDApi.controller;

import com.navegg.challenge.CRUDApi.model.Item;
import com.navegg.challenge.CRUDApi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<List<Item>>  getItems(){

        if(itemService.getItems() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return new ResponseEntity<List<Item>>(itemService.getItems(), HttpStatus.OK);

    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id){

        if(itemService.getItemById(id) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return new ResponseEntity<Item>(itemService.getItemById(id), HttpStatus.OK);

    }

    @PostMapping("/item")
    public ResponseEntity<Item> saveItem(@RequestBody Item item){

        if(itemService.siteNameExist(item.getName())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return new ResponseEntity<Item>(itemService.saveItem(item), HttpStatus.OK);

    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Item> editItem(@PathVariable String id, @RequestBody Item item){

        if(itemService.getItemById(id) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return new ResponseEntity<>(itemService.updateItem(id, item), HttpStatus.OK);

    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable String id){

        if(itemService.getItemById(id) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return new ResponseEntity<>(itemService.deleteItem(id), HttpStatus.OK);

    }

}
