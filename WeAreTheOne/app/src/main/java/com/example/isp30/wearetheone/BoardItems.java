package com.example.isp30.wearetheone;

public class BoardItems {
    private String title;
    private String wirter;
    private String time;

    public BoardItems(String title, String wirter, String time) {
        this.title = title;
        this.wirter = wirter;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWirter() {
        return wirter;
    }

    public void setWirter(String wirter) {
        this.wirter = wirter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
