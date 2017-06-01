package com.gionee.setup;

import android.content.Context;
import android.net.wifi.WifiManager;

class wifi {
    static boolean isWiFiEnabled(Context context) {
        WifiManager mWifiManager = (WifiManager) context.getApplicationContext().
                getSystemService(Context.WIFI_SERVICE);
        return mWifiManager.isWifiEnabled();
    }

    static void setWiFiEnabled(Context context, boolean enable) {
        WifiManager mWifiManager = (WifiManager) context.getApplicationContext().
                getSystemService(Context.WIFI_SERVICE);
        mWifiManager.setWifiEnabled(enable);
    }
}
