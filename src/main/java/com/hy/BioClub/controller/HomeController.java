package com.hy.BioClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hy.BioClub.service.GamesService;
import com.hy.BioClub.service.SiteConfigService;
import com.hy.BioClub.service.StandardsService;
import com.hy.BioClub.service.YoutubeService;

@Controller
public class HomeController {
    private final StandardsService standardsService;
    private final SiteConfigService siteConfigService;
    private final GamesService gamesService;
    private final YoutubeService youtubeService;

    public HomeController(StandardsService standardsService, SiteConfigService siteConfigService, GamesService gamesService, YoutubeService youtubeService) {
        this.standardsService = standardsService;
        this.siteConfigService = siteConfigService;
        this.gamesService = gamesService;
        this.youtubeService = youtubeService;
    }

    @GetMapping({"/", "/index.html"})
    public String index(Model model) {
        model.addAttribute("standards", standardsService.getStandards());
        model.addAttribute("navTitle", siteConfigService.getNavTitle());
        model.addAttribute("indexHeading", siteConfigService.getIndexHeading());
        model.addAttribute("indexParagraph", siteConfigService.getIndexParagraph());
        return "index";
    }

    @GetMapping("/games.html")
    public String games(Model model) {
        model.addAttribute("navTitle", siteConfigService.getNavTitle());
        model.addAttribute("games", gamesService.getGames());
        return "games";
    }

    @GetMapping("/youtube.html")
    public String youtube(Model model) {
        model.addAttribute("navTitle", siteConfigService.getNavTitle());
        model.addAttribute("youtubeList", youtubeService.getVideos());
        return "youtube";
    }
    
}
