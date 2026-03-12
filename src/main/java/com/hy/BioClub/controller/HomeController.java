package com.hy.BioClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hy.BioClub.service.StandardsService;

@Controller
public class HomeController {

    @GetMapping({"/", "/index.html"})
    public String index(Model model) {
        model.addAttribute("standards", StandardsService.getStandards());
        return "index";
    }

    @GetMapping("/games.html")
    public String games() {
        return "games";
    }

    @GetMapping("/youtube.html")
    public String youtube() {
        return "youtube";
    }
    
}
