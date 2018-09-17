package com.example.isp30.wearetheone.TripAll;

import android.graphics.drawable.Drawable;

public class TripItem {
    private Drawable pic;
    private String title, subTitle;
    public TripItem(){

    }
    public TripItem(Drawable pic, String title, String subTitle) {
        this.pic = pic;
        this.title = title;
        this.subTitle = subTitle;
    }

    public Drawable getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setPic(Drawable pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
