package com.example.springsecuity.repository;

import com.example.springsecuity.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<CustomUser, Integer> {

     CustomUser findByUsername(String name);

}
