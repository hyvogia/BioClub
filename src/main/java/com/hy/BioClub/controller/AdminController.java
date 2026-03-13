package com.hy.BioClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hy.BioClub.model.GameEntry;
import com.hy.BioClub.model.YoutubeEntry;
import com.hy.BioClub.service.GamesService;
import com.hy.BioClub.service.SiteConfigService;
import com.hy.BioClub.service.YoutubeService;

@Controller
public class AdminController {
    private final SiteConfigService siteConfigService;
    private final GamesService gamesService;
    private final YoutubeService youtubeService;

    public AdminController(SiteConfigService siteConfigService, GamesService gamesService, YoutubeService youtubeService) {
        this.siteConfigService = siteConfigService;
        this.gamesService = gamesService;
        this.youtubeService = youtubeService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("navTitle", siteConfigService.getNavTitle());
        model.addAttribute("indexHeading", siteConfigService.getIndexHeading());
        model.addAttribute("indexParagraph", siteConfigService.getIndexParagraph());
        model.addAttribute("games", gamesService.getGames());
        model.addAttribute("youtubeList", youtubeService.getVideos());
        return "admin";
    }

    @PostMapping("/admin/setNavTitle")
    public String setNavTitle(@RequestParam("navTitle") String title) {
        siteConfigService.setNavTitle(title);
        return "redirect:/admin";
    }

    @PostMapping("/admin/resetNavTitle")
    public String resetNavTitle() {
        siteConfigService.resetNavTitle();
        return "redirect:/admin";
    }

    @PostMapping("/admin/setIndexHeading")
    public String setIndexHeading(@RequestParam("indexHeading") String heading) {
        siteConfigService.setIndexHeading(heading);
        return "redirect:/admin";
    }

    @PostMapping("/admin/resetIndexHeading")
    public String resetIndexHeading() {
        siteConfigService.resetIndexHeading();
        return "redirect:/admin";
    }

    @PostMapping("/admin/setIndexParagraph")
    public String setIndexParagraph(@RequestParam("indexParagraph") String p) {
        siteConfigService.setIndexParagraph(p);
        return "redirect:/admin";
    }

    // Games CRUD
    @PostMapping("/admin/games/add")
    public String addGame(@RequestParam String title, @RequestParam String description, @RequestParam String url) {
        GameEntry g = new GameEntry(title, description, url, "Play Game");
        gamesService.add(g);
        return "redirect:/admin#games";
    }

    @PostMapping("/admin/games/edit/{idx}")
    public String editGame(@PathVariable("idx") int idx, @RequestParam String title, @RequestParam String description, @RequestParam String url) {
        GameEntry g = new GameEntry(title, description, url, "Play Game");
        gamesService.update(idx, g);
        return "redirect:/admin#games";
    }

    @PostMapping("/admin/games/delete/{idx}")
    public String deleteGame(@PathVariable("idx") int idx) {
        gamesService.delete(idx);
        return "redirect:/admin#games";
    }

    // YouTube CRUD
    @PostMapping("/admin/youtube/add")
    public String addYoutube(@RequestParam String title, @RequestParam String description, @RequestParam String url) {
        YoutubeEntry v = new YoutubeEntry(title, description, url, "Watch");
        youtubeService.add(v);
        return "redirect:/admin#youtube";
    }

    @PostMapping("/admin/youtube/edit/{idx}")
    public String editYoutube(@PathVariable("idx") int idx, @RequestParam String title, @RequestParam String description, @RequestParam String url) {
        YoutubeEntry v = new YoutubeEntry(title, description, url, "Watch");
        youtubeService.update(idx, v);
        return "redirect:/admin#youtube";
    }

    @PostMapping("/admin/youtube/delete/{idx}")
    public String deleteYoutube(@PathVariable("idx") int idx) {
        youtubeService.delete(idx);
        return "redirect:/admin#youtube";
    }

    @PostMapping("/admin/resetIndexParagraph")
    public String resetIndexParagraph() {
        siteConfigService.resetIndexParagraph();
        return "redirect:/admin";
    }
}
