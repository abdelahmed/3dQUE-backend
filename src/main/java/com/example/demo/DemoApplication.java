package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Model class for Filament
    static class Filament {
        private Integer id;
        private Integer filamentTotal;
        private Integer filamentCurr;

        // Constructor
        public Filament(Integer id, Integer filamentTotal, Integer filamentCurr) {
            this.id = id;
            this.filamentTotal = filamentTotal;
            this.filamentCurr = filamentCurr;
        }

        // Getters and setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFilamentTotal() {
            return filamentTotal;
        }

        public void setFilamentTotal(Integer filamentTotal) {
            this.filamentTotal = filamentTotal;
        }

        public Integer getFilamentCurr() {
            return filamentCurr;
        }

        public void setFilamentCurr(Integer filamentCurr) {
            this.filamentCurr = filamentCurr;
        }
    }

    // Service class to manage filaments
    @Service
    static class FilamentService {
        private Map<Integer, Filament> filaments = new HashMap<>();

        @PostConstruct
        public void init() {
            // Initialize with a default filament
            filaments.put(1, new Filament(1, 1000, 500));
        }

        public Filament getFilament(Integer id) {
            return filaments.get(id);
        }

        public Filament updateFilament(Integer id, Filament filament) {
            filaments.put(id, filament);
            return filament;
        }
    }

    // REST Controller to handle requests
    @RestController
    @RequestMapping("/api/filaments")
    static class FilamentController {

        @Autowired
        private FilamentService filamentService;

        @GetMapping("/{id}")
        public Filament getFilament(@PathVariable Integer id) {
            return filamentService.getFilament(id);
        }

        @PutMapping("/{id}")
        public Filament updateFilament(@PathVariable Integer id, @RequestBody Filament filament) {
            return filamentService.updateFilament(id, filament);
        }
    }
}
