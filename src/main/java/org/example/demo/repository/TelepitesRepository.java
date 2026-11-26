package org.example.demo.repository;

import org.example.demo.entity.Telepites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelepitesRepository extends JpaRepository<Telepites, Integer> {
}