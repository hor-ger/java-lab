package org.example.demo.controller;

import org.example.demo.entity.Gep;
import org.example.demo.entity.Szoftver;
import org.example.demo.repository.GepRepository;
import org.example.demo.repository.SzoftverRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private final GepRepository gepRepository;
    private final SzoftverRepository szoftverRepository;

    public RestApiController(GepRepository gepRepository, SzoftverRepository szoftverRepository) {
        this.gepRepository = gepRepository;
        this.szoftverRepository = szoftverRepository;
    }

    // ========== GÉPEK ==========

    @GetMapping("/gepek")
    public List<Gep> osszesGep() {
        return gepRepository.findAll();
    }

    @GetMapping("/gepek/{id}")
    public ResponseEntity<Gep> egyGep(@PathVariable Integer id) {
        return gepRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/gepek")
    public ResponseEntity<Gep> ujGep(@RequestBody Gep gep) {
        gep.setId(null);
        Gep mentett = gepRepository.save(gep);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentett);
    }

    @PutMapping("/gepek/{id}")
    public ResponseEntity<Gep> modositGep(@PathVariable Integer id, @RequestBody Gep gep) {
        if (!gepRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gep.setId(id);
        return ResponseEntity.ok(gepRepository.save(gep));
    }

    @DeleteMapping("/gepek/{id}")
    public ResponseEntity<Void> torolGep(@PathVariable Integer id) {
        if (!gepRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        gepRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ========== SZOFTVEREK ==========

    @GetMapping("/szoftverek")
    public List<Szoftver> osszesSzoftver() {
        return szoftverRepository.findAll();
    }

    @GetMapping("/szoftverek/{id}")
    public ResponseEntity<Szoftver> egySzoftver(@PathVariable Integer id) {
        return szoftverRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/szoftverek")
    public ResponseEntity<Szoftver> ujSzoftver(@RequestBody Szoftver szoftver) {
        szoftver.setId(null);
        Szoftver mentett = szoftverRepository.save(szoftver);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentett);
    }

    @PutMapping("/szoftverek/{id}")
    public ResponseEntity<Szoftver> modositSzoftver(@PathVariable Integer id, @RequestBody Szoftver szoftver) {
        if (!szoftverRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        szoftver.setId(id);
        return ResponseEntity.ok(szoftverRepository.save(szoftver));
    }

    @DeleteMapping("/szoftverek/{id}")
    public ResponseEntity<Void> torolSzoftver(@PathVariable Integer id) {
        if (!szoftverRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        szoftverRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
