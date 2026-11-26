package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.entity.Uzenet;
import org.example.demo.repository.GepRepository;
import org.example.demo.repository.SzoftverRepository;
import org.example.demo.repository.TelepitesRepository;
import org.example.demo.repository.UzenetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class Controller1 {

    private final GepRepository gepRepository;
    private final SzoftverRepository szoftverRepository;
    private final TelepitesRepository telepitesRepository;
    private final UzenetRepository uzenetRepository;

    public Controller1(GepRepository gepRepository,
                       SzoftverRepository szoftverRepository,
                       TelepitesRepository telepitesRepository,
                       UzenetRepository uzenetRepository) {
        this.gepRepository = gepRepository;
        this.szoftverRepository = szoftverRepository;
        this.telepitesRepository = telepitesRepository;
        this.uzenetRepository = uzenetRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gepek", gepRepository.findAll());
        return "home";
    }

    @GetMapping("/adatbazis")
    public String adatbazis(@RequestParam(defaultValue = "gep") String tabla, Model model) {
        model.addAttribute("tabla", tabla);
        if ("szoftver".equals(tabla)) {
            model.addAttribute("szoftverek", szoftverRepository.findAll());
        } else if ("telepites".equals(tabla)) {
            model.addAttribute("telepitesek", telepitesRepository.findAll());
        } else {
            model.addAttribute("gepek", gepRepository.findAll());
        }
        return "adatbazis";
    }

    @GetMapping("/kapcsolat")
    public String kapcsolat(Model model) {
        if (!model.containsAttribute("uzenet")) {
            model.addAttribute("uzenet", new Uzenet());
        }
        return "kapcsolat";
    }

    @PostMapping("/kapcsolat")
    public String kapcsolatKuldes(@Valid @ModelAttribute("uzenet") Uzenet uzenet,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "kapcsolat";
        }
        uzenetRepository.save(uzenet);
        redirectAttributes.addFlashAttribute("siker", true);
        return "redirect:/kapcsolat";
    }

    @GetMapping("/uzenetek")
    public String uzenetek(Model model) {
        model.addAttribute("uzenetek", uzenetRepository.findAllByOrderByKuldesIdejeDesc());
        return "uzenetek";
    }

    @GetMapping("/diagram")
    public String diagram(Model model) {
        Map<String, Long> kategoriaStat = szoftverRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        sz -> sz.getKategoria() != null ? sz.getKategoria() : "Egyéb",
                        Collectors.counting()
                ));

        model.addAttribute("kategoriaCimkek", new ArrayList<>(kategoriaStat.keySet()));
        model.addAttribute("kategoriaErtekek", new ArrayList<>(kategoriaStat.values()));

        Map<String, Long> gepTipusStat = gepRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        g -> g.getTipus() != null ? g.getTipus() : "Ismeretlen",
                        Collectors.counting()
                ));

        model.addAttribute("tipusCimkek", new ArrayList<>(gepTipusStat.keySet()));
        model.addAttribute("tipusErtekek", new ArrayList<>(gepTipusStat.values()));

        return "diagram";
    }

    @GetMapping("/rest")
    public String rest() {
        return "rest";
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
