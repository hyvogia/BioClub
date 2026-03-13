package com.hy.BioClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hy.BioClub.service.SiteConfigService;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("navTitle", SiteConfigService.getNavTitle());
        model.addAttribute("indexHeading", SiteConfigService.getIndexHeading());
        model.addAttribute("indexParagraph", SiteConfigService.getIndexParagraph());
        model.addAttribute("games", com.hy.BioClub.service.GamesService.getGames());
        model.addAttribute("youtubeList", com.hy.BioClub.service.YoutubeService.getVideos());
        return "admin";
    }

    @PostMapping("/admin/setNavTitle")
    public String setNavTitle(@RequestParam("navTitle") String title) {
        SiteConfigService.setNavTitle(title);
        return "redirect:/admin";
    }

    @PostMapping("/admin/resetNavTitle")
    public String resetNavTitle() {
        SiteConfigService.resetNavTitle();
        return "redirect:/admin";
    }

    @PostMapping("/admin/setIndexHeading")
    public String setIndexHeading(@RequestParam("indexHeading") String heading) {
        SiteConfigService.setIndexHeading(heading);
        return "redirect:/admin";
    }

    @PostMapping("/admin/resetIndexHeading")
    public String resetIndexHeading() {
        SiteConfigService.resetIndexHeading();
        return "redirect:/admin";
    }

    @PostMapping("/admin/setIndexParagraph")
    public String setIndexParagraph(@RequestParam("indexParagraph") String p) {
        SiteConfigService.setIndexParagraph(p);
        return "redirect:/admin";
    }

    // Games CRUD
    @PostMapping("/admin/games/add")
    public String addGame(@RequestParam String title, @RequestParam String description, @RequestParam String url) {
        com.hy.BioClub.model.GameEntry g = new com.hy.BioClub.model.GameEntry(title, description, url, "Play Game");
        com.hy.BioClub.service.GamesService.add(g);
        return "redirect:/admin#games";
    }

    @PostMapping("/admin/games/edit/{idx}")
    public String editGame(@PathVariable("idx") int idx, @RequestParam String title, @RequestParam String description, @RequestParam String url) {
        com.hy.BioClub.model.GameEntry g = new com.hy.BioClub.model.GameEntry(title, description, url, "Play Game");
        com.hy.BioClub.service.GamesService.update(idx, g);
        return "redirect:/admin#games";
    }

    @PostMapping("/admin/games/delete/{idx}")
    public String deleteGame(@PathVariable("idx") int idx) {
        com.hy.BioClub.service.GamesService.delete(idx);
        return "redirect:/admin#games";
    }

    // YouTube CRUD
    @PostMapping("/admin/youtube/add")
    public String addYoutube(@RequestParam String title, @RequestParam String description, @RequestParam String url) {
        com.hy.BioClub.model.YoutubeEntry v = new com.hy.BioClub.model.YoutubeEntry(title, description, url, "Watch");
        com.hy.BioClub.service.YoutubeService.add(v);
        return "redirect:/admin#youtube";
    }

    @PostMapping("/admin/youtube/edit/{idx}")
    public String editYoutube(@PathVariable("idx") int idx, @RequestParam String title, @RequestParam String description, @RequestParam String url) {
        com.hy.BioClub.model.YoutubeEntry v = new com.hy.BioClub.model.YoutubeEntry(title, description, url, "Watch");
        com.hy.BioClub.service.YoutubeService.update(idx, v);
        return "redirect:/admin#youtube";
    }

    @PostMapping("/admin/youtube/delete/{idx}")
    public String deleteYoutube(@PathVariable("idx") int idx) {
        com.hy.BioClub.service.YoutubeService.delete(idx);
        return "redirect:/admin#youtube";
    }

    @PostMapping("/admin/resetIndexParagraph")
    public String resetIndexParagraph() {
        SiteConfigService.resetIndexParagraph();
        return "redirect:/admin";
    }
}
