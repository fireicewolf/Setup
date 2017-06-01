package com.gionee.setup.connections;

import android.content.Context;
import android.net.wifi.WifiManager;

public class wifi {
    public static boolean isWiFiEnabled(Context context) {
        WifiManager mWifiManager = (WifiManager) context.getApplicationContext().
                getSystemService(Context.WIFI_SERVICE);
        return mWifiManager.isWifiEnabled();
    }

    public static void setWiFiEnabled(Context context, boolean enable) {
        WifiManager mWifiManager = (WifiManager) context.getApplicationContext().
                getSystemService(Context.WIFI_SERVICE);
        mWifiManager.setWifiEnabled(enable);
    }
}
