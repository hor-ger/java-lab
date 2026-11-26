package org.example.demo.repository;

import org.example.demo.entity.Gep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GepRepository extends JpaRepository<Gep, Integer> {
}