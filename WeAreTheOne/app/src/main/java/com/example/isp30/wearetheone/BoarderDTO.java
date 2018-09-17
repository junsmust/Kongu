package com.example.isp30.wearetheone;

public class BoarderDTO {
    private String title;
    private String name;
    private String time;

    public BoarderDTO(String title, String name, String time) {
        this.title = title;
        this.name = name;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
