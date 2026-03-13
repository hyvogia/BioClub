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
        model.addAttribute("navTitle", com.hy.BioClub.service.SiteConfigService.getNavTitle());
        model.addAttribute("indexHeading", com.hy.BioClub.service.SiteConfigService.getIndexHeading());
        model.addAttribute("indexParagraph", com.hy.BioClub.service.SiteConfigService.getIndexParagraph());
        return "index";
    }

    @GetMapping("/games.html")
    public String games(Model model) {
        model.addAttribute("navTitle", com.hy.BioClub.service.SiteConfigService.getNavTitle());
        model.addAttribute("games", com.hy.BioClub.service.GamesService.getGames());
        return "games";
    }

    @GetMapping("/youtube.html")
    public String youtube(Model model) {
        model.addAttribute("navTitle", com.hy.BioClub.service.SiteConfigService.getNavTitle());
        model.addAttribute("youtubeList", com.hy.BioClub.service.YoutubeService.getVideos());
        return "youtube";
    }
    
}
