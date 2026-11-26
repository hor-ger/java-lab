package org.example.demo.controller;

import org.example.demo.repository.GepRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller1 {

    private final GepRepository gepRepository;

    public Controller1(GepRepository gepRepository) {
        this.gepRepository = gepRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gepek", gepRepository.findAll());
        return "home";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}