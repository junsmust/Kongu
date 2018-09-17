package com.example.isp30.wearetheone.HelpAll;

public class HelpItem {
    private String title, subTitle;
    public HelpItem(){}
    public HelpItem(String title){
        this.title = title;
    }

    public HelpItem(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
