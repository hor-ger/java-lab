package org.example.demo.repository;

import org.example.demo.entity.Szoftver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SzoftverRepository extends JpaRepository<Szoftver, Integer> {
}