package com.gionee.setup.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WiFiSignalStateListener {
    private Context mContext;
    private WLANSignalBroadcastReceiver receiver;
    private WLANSignalStateListener mWLANSignalStateListener;


    private static final int LEVEL_DGREE = 4;
    //定义wifi信号等级
    private static final int LEVEL_NONE = -1;
    private static final int LEVEL_0 = 0;
    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;


    public WiFiSignalStateListener(Context context) {
        mContext = context;
        receiver = new WLANSignalBroadcastReceiver();
    }

    public void register(WLANSignalStateListener listener) {
        mWLANSignalStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private void handleStateChanged(int level) {
        switch (level) {
            case LEVEL_NONE:
                if (mWLANSignalStateListener != null) {
                    mWLANSignalStateListener.onStateLEVEL_NONE();
                }
                break;
            case LEVEL_0:
                if (mWLANSignalStateListener != null) {
                    mWLANSignalStateListener.onStateLEVEL_0();
                }
                break;
            case LEVEL_1:
                if (mWLANSignalStateListener != null) {
                    mWLANSignalStateListener.onStateLEVEL_1();
                }
                break;
            case LEVEL_2:
                if (mWLANSignalStateListener != null) {
                    mWLANSignalStateListener.onStateLEVEL_2();
                }
                break;
            case LEVEL_3:
                if (mWLANSignalStateListener != null) {
                    mWLANSignalStateListener.onStateLEVEL_3();
                }
        }
    }

    private class WLANSignalBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext()
                    .getSystemService(Context.WIFI_SERVICE);
            if (action.equals(WifiManager.RSSI_CHANGED_ACTION)) {
                //当信号的rssi值发生变化时，在这里处理
                if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
                    WifiInfo info = mWifiManager.getConnectionInfo();
                    //计算wifi的信号等级
                    int level = WifiManager.calculateSignalLevel(info.getRssi(), LEVEL_DGREE);
                    handleStateChanged(level);
                }
            }
        }
    }

    public interface WLANSignalStateListener {

        void onStateLEVEL_NONE();

        void onStateLEVEL_0();

        void onStateLEVEL_1();

        void onStateLEVEL_2();

        void onStateLEVEL_3();
    }
}
