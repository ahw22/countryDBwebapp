package com.example.countryDB.controller;

import com.example.countryDB.dto.CountryFormDTO;
import com.example.countryDB.dto.CountryViewDTO;
import com.example.countryDB.repository.CapitalRepository;
import com.example.countryDB.repository.ContinentRepository;
import com.example.countryDB.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private CapitalRepository capitalRepository;

    @GetMapping
    public String listCountries(Model model) {
        List<CountryViewDTO> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        return "country/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("country", new CountryFormDTO());
        model.addAttribute("continents", continentRepository.findAll());
        model.addAttribute("capitals", capitalRepository.findAll());
        return "country/form";
    }

    @PostMapping("/save")
    public String saveCountry(@ModelAttribute("country") CountryFormDTO dto) {
        countryService.save(dto);
        return "redirect:/countries";
    }

    @PostMapping("/{id}/delete")
    public String deleteCountry(@PathVariable int id) {
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        CountryFormDTO countryFormDTO = countryService.getEditFormById(id);
        model.addAttribute("country", countryFormDTO);
        model.addAttribute("continents", continentRepository.findAll());
        return "country/form";
    }
}
