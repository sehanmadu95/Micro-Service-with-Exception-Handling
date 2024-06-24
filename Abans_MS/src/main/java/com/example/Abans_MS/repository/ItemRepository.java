package com.example.Abans_MS.repository;

import com.example.Abans_MS.entity.ItemAbance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemAbance,Integer> {
}
