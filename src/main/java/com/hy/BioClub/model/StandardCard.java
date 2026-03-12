package com.hy.BioClub.model;

public class StandardCard {
    private String title;
    private String description;
    private String pdfPath;

    public StandardCard() {
    }

    public StandardCard(String title, String description, String pdfPath) {
        this.title = title;
        this.description = description;
        this.pdfPath = pdfPath;
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

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }
}
