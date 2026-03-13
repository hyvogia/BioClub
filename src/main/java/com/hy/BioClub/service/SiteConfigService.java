package com.hy.BioClub.service;

import org.springframework.stereotype.Service;

@Service
public class SiteConfigService {

    private String navTitle = "BioClub";

    private String indexHeading = "Welcome To<br>\n                        <span class=\"fw-bold text-white\">Biology</span>\n                        <span class=\"fw-bold\" style=\"color: #9bd46a;\">200</span>";

    private String indexParagraph = "Study with AI or custom Flashcard.";

    public String getNavTitle() {
        return navTitle;
    }

    public void setNavTitle(String title) {
        navTitle = title == null ? "" : title;
    }

    public void resetNavTitle() {
        navTitle = "BioClub";
    }

    public String getIndexHeading() {
        return indexHeading;
    }

    public void setIndexHeading(String headingHtml) {
        indexHeading = headingHtml == null ? "" : headingHtml;
    }

    public void resetIndexHeading() {
        indexHeading = "Welcome To<br>\n                        <span class=\"fw-bold text-white\">Biology</span>\n                        <span class=\"fw-bold\" style=\"color: #9bd46a;\">200</span>";
    }

    public String getIndexParagraph() {
        return indexParagraph;
    }

    public void setIndexParagraph(String p) {
        indexParagraph = p == null ? "" : p;
    }

    public void resetIndexParagraph() {
        indexParagraph = "Study with AI or custom Flashcard.";
    }
}
