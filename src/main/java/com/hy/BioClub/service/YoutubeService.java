package com.hy.BioClub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hy.BioClub.model.YoutubeEntry;

@Service
public class YoutubeService {
    private final List<YoutubeEntry> videos = new ArrayList<>();

    public YoutubeService() {
        videos.add(new YoutubeEntry("CrashCourse Biology", "Introductory biology videos covering many topics.", "https://www.youtube.com/playlist?list=PL8dPuuaLjXtNgK6MZucdYldNkMybYIHKR", "Watch"));
        videos.add(new YoutubeEntry("Khan Academy Biology", "Clear explanations on a wide range of biology topics.", "https://www.youtube.com/user/khanacademy", "Watch"));
    }

    public List<YoutubeEntry> getVideos() {
        return Collections.unmodifiableList(videos);
    }

    public void add(YoutubeEntry v) {
        videos.add(v);
    }

    public YoutubeEntry get(int idx) {
        return videos.get(idx);
    }

    public void update(int idx, YoutubeEntry v) {
        videos.set(idx, v);
    }

    public void delete(int idx) {
        videos.remove(idx);
    }
}
