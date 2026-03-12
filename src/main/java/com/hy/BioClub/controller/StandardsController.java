package com.hy.BioClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hy.BioClub.model.StandardCard;
import com.hy.BioClub.service.StandardsService;

@Controller
public class StandardsController {

    @GetMapping("/standards/add")
    public String addForm(Model model) {
        model.addAttribute("standard", new StandardCard());
        return "add-standard";
    }

    @PostMapping("/standards/add")
    public String addSubmit(@ModelAttribute StandardCard standard) {
        StandardsService.add(standard);
        return "redirect:/";
    }

    @GetMapping("/standards/edit/{idx}")
    public String editForm(@PathVariable("idx") int idx, Model model) {
        StandardCard s = StandardsService.get(idx);
        model.addAttribute("standard", s);
        model.addAttribute("index", idx);
        return "edit-standard";
    }

    @PostMapping("/standards/edit/{idx}")
    public String editSubmit(@PathVariable("idx") int idx, @ModelAttribute StandardCard standard) {
        StandardsService.update(idx, standard);
        return "redirect:/";
    }

    @GetMapping("/standards/deleteAll")
    public String deleteAll() {
        StandardsService.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/standards/delete/{idx}")
    public String delete(@PathVariable("idx") int idx) {
        StandardsService.delete(idx);
        return "redirect:/";
    }
}
