package com.navegg.challenge.CRUDApi.repository;

import com.navegg.challenge.CRUDApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
