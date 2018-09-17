package com.example.isp30.wearetheone.HelpAll;

import android.graphics.drawable.Drawable;

public class Help_Embassy_item {
    private int flag;
    private String flagName;

    public Help_Embassy_item(int flag, String flagName) {
        this.flag = flag;
        this.flagName = flagName;
    }

    public int getFlag() {
        return flag;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }
}
