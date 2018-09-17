package com.example.isp30.wearetheone;

public class KoreanItems {
    private int img;
    private String text;
    public KoreanItems(){

    }

    public KoreanItems(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
