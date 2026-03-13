package com.hy.BioClub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hy.BioClub.model.YoutubeEntry;

public class YoutubeService {
    private static final List<YoutubeEntry> VIDEOS = new ArrayList<>();

    static {
        VIDEOS.add(new YoutubeEntry("CrashCourse Biology", "Introductory biology videos covering many topics.", "https://www.youtube.com/playlist?list=PL8dPuuaLjXtNgK6MZucdYldNkMybYIHKR", "Watch"));
        VIDEOS.add(new YoutubeEntry("Khan Academy Biology", "Clear explanations on a wide range of biology topics.", "https://www.youtube.com/user/khanacademy", "Watch"));
    }

    public static List<YoutubeEntry> getVideos() {
        return Collections.unmodifiableList(VIDEOS);
    }

    public static void add(YoutubeEntry v) {
        VIDEOS.add(v);
    }

    public static YoutubeEntry get(int idx) {
        return VIDEOS.get(idx);
    }

    public static void update(int idx, YoutubeEntry v) {
        VIDEOS.set(idx, v);
    }

    public static void delete(int idx) {
        VIDEOS.remove(idx);
    }
}
