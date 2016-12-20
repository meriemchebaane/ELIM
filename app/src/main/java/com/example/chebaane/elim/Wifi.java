package com.example.chebaane.elim;

/**
 * Created by chebaane on 16/12/2016.
 */

public class Wifi {
    private String SSID;
    private String security;
    private String level;

    public Wifi(String SSID, String security, String level) {
        this.SSID = SSID;

        this.security = security;
        this.level = level;
    }

    public String getSSID() {
        return SSID;
    }

    public String getSecurity() {
        return security;
    }

    public String getLevel() {
        return level;
    }

}
