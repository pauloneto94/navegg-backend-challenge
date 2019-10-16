package com.navegg.challenge.CRUDApi.repository;

import com.navegg.challenge.CRUDApi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    void deleteById(String id);

    @Query("select item from Item item " +
            "join fetch item.urlList " +
            "join fetch item.categoryList " +
            "where item.id=?1")
    Item findByIdFetchCollections(String id);
}
