package com.navegg.challenge.CRUDApi.repository;

import com.navegg.challenge.CRUDApi.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
}
