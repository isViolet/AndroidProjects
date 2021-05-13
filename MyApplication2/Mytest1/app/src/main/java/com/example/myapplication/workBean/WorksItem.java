package com.example.myapplication.workBean;

public class WorksItem {

    public void setWorksTitle(String worksTitle) {
        this.worksTitle = worksTitle;
    }

    public String getWorksTitle() {
        return worksTitle;
    }

    private String worksTitle;
    private String worksLabel;

    public String getWorksLabel() {
        return worksLabel;
    }

    public void setWorksLabel(String worksLabel) {
        this.worksLabel = worksLabel;
    }

    public String getWorksContent() {
        return worksContent;
    }

    public void setWorksContent(String worksContent) {
        this.worksContent = worksContent;
    }

    private String worksContent;
}
