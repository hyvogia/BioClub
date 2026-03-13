package com.hy.BioClub.model;

public class YoutubeEntry {
    private String title;
    private String description;
    private String url;
    private String buttonLabel;

    public YoutubeEntry() {}

    public YoutubeEntry(String title, String description, String url, String buttonLabel) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.buttonLabel = buttonLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }
}
