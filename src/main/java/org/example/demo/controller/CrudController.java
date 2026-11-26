package org.example.demo.controller;

import org.example.demo.entity.Gep;
import org.example.demo.entity.Szoftver;
import org.example.demo.entity.Telepites;
import org.example.demo.repository.GepRepository;
import org.example.demo.repository.SzoftverRepository;
import org.example.demo.repository.TelepitesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crud")
public class CrudController {

    private final GepRepository gepRepository;
    private final SzoftverRepository szoftverRepository;
    private final TelepitesRepository telepitesRepository;

    public CrudController(GepRepository gepRepository,
                          SzoftverRepository szoftverRepository,
                          TelepitesRepository telepitesRepository) {
        this.gepRepository = gepRepository;
        this.szoftverRepository = szoftverRepository;
        this.telepitesRepository = telepitesRepository;
    }

    // READ: táblázat megjelenítése a kiválasztott táblához
    @GetMapping
    public String crudLista(@RequestParam(defaultValue = "gep") String tabla, Model model) {
        model.addAttribute("tabla", tabla);
        if ("szoftver".equals(tabla)) {
            model.addAttribute("szoftverek", szoftverRepository.findAll());
        } else if ("telepites".equals(tabla)) {
            model.addAttribute("telepitesek", telepitesRepository.findAll());
        } else {
            model.addAttribute("gepek", gepRepository.findAll());
        }
        return "crud";
    }

    // --- GÉP CRUD ---
    @GetMapping("/gep/uj")
    public String ujGep(Model model) {
        model.addAttribute("tabla", "gep");
        model.addAttribute("gep", new Gep());
        return "crud-form";
    }

    @GetMapping("/gep/edit/{id}")
    public String editGep(@PathVariable Integer id, Model model) {
        model.addAttribute("tabla", "gep");
        model.addAttribute("gep", gepRepository.findById(id).orElseThrow());
        return "crud-form";
    }

    @PostMapping("/gep/ment")
    public String mentGep(@ModelAttribute Gep gep) {
        gepRepository.save(gep);
        return "redirect:/crud?tabla=gep";
    }

    @GetMapping("/gep/delete/{id}")
    public String deleteGep(@PathVariable Integer id) {
        gepRepository.deleteById(id);
        return "redirect:/crud?tabla=gep";
    }

    // --- SZOFTVER CRUD ---
    @GetMapping("/szoftver/uj")
    public String ujSzoftver(Model model) {
        model.addAttribute("tabla", "szoftver");
        model.addAttribute("szoftver", new Szoftver());
        return "crud-form";
    }

    @GetMapping("/szoftver/edit/{id}")
    public String editSzoftver(@PathVariable Integer id, Model model) {
        model.addAttribute("tabla", "szoftver");
        model.addAttribute("szoftver", szoftverRepository.findById(id).orElseThrow());
        return "crud-form";
    }

    @PostMapping("/szoftver/ment")
    public String mentSzoftver(@ModelAttribute Szoftver szoftver) {
        szoftverRepository.save(szoftver);
        return "redirect:/crud?tabla=szoftver";
    }

    @GetMapping("/szoftver/delete/{id}")
    public String deleteSzoftver(@PathVariable Integer id) {
        szoftverRepository.deleteById(id);
        return "redirect:/crud?tabla=szoftver";
    }

    // --- TELEPÍTÉS CRUD ---
    @GetMapping("/telepites/uj")
    public String ujTelepites(Model model) {
        model.addAttribute("tabla", "telepites");
        model.addAttribute("telepites", new Telepites());
        model.addAttribute("gepek", gepRepository.findAll());
        model.addAttribute("szoftverek", szoftverRepository.findAll());
        return "crud-form";
    }

    @GetMapping("/telepites/edit/{id}")
    public String editTelepites(@PathVariable Integer id, Model model) {
        model.addAttribute("tabla", "telepites");
        model.addAttribute("telepites", telepitesRepository.findById(id).orElseThrow());
        model.addAttribute("gepek", gepRepository.findAll());
        model.addAttribute("szoftverek", szoftverRepository.findAll());
        return "crud-form";
    }

    @PostMapping("/telepites/ment")
    public String mentTelepites(@ModelAttribute Telepites telepites) {
        telepitesRepository.save(telepites);
        return "redirect:/crud?tabla=telepites";
    }

    @GetMapping("/telepites/delete/{id}")
    public String deleteTelepites(@PathVariable Integer id) {
        telepitesRepository.deleteById(id);
        return "redirect:/crud?tabla=telepites";
    }
}