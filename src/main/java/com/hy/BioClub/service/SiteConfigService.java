package com.hy.BioClub.service;

public class SiteConfigService {

    private static String navTitle = "BioClub";

    private static String indexHeading = "Welcome To<br>\n                        <span class=\"fw-bold text-white\">Biology</span>\n                        <span class=\"fw-bold\" style=\"color: #9bd46a;\">200</span>";

    private static String indexParagraph = "Study with AI or custom Flashcard.";

    public static String getNavTitle() {
        return navTitle;
    }

    public static void setNavTitle(String title) {
        navTitle = title == null ? "" : title;
    }

    public static void resetNavTitle() {
        navTitle = "BioClub";
    }

    public static String getIndexHeading() {
        return indexHeading;
    }

    public static void setIndexHeading(String headingHtml) {
        indexHeading = headingHtml == null ? "" : headingHtml;
    }

    public static void resetIndexHeading() {
        indexHeading = "Welcome To<br>\n                        <span class=\"fw-bold text-white\">Biology</span>\n                        <span class=\"fw-bold\" style=\"color: #9bd46a;\">200</span>";
    }

    public static String getIndexParagraph() {
        return indexParagraph;
    }

    public static void setIndexParagraph(String p) {
        indexParagraph = p == null ? "" : p;
    }

    public static void resetIndexParagraph() {
        indexParagraph = "Study with AI or custom Flashcard.";
    }
}
