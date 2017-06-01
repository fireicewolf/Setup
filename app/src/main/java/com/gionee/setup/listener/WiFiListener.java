package com.gionee.setup.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

public class WiFiListener {

    private Context mContext;
    private WLANBroadcastReceiver receiver;
    private WLANStateListener mWLANStateListener;

    public WiFiListener(Context context) {
        mContext = context;
        receiver = new WLANBroadcastReceiver();
    }

    public void register(WLANStateListener listener) {
        mWLANStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private class WLANBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                switch (state) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        if (mWLANStateListener != null) {
                            mWLANStateListener.onStateDisabled();
                        }
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        if (mWLANStateListener != null) {
                            mWLANStateListener.onStateEnabled();
                        }
                        break;
                }
            }
        }
    }


    public interface WLANStateListener {
        void onStateDisabled();

        void onStateEnabled();

    }
}
