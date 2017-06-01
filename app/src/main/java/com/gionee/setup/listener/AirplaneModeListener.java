package com.gionee.setup.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;

public class AirplaneModeListener {

    private Context mContext;
    private AirplaneModeBroadcastReceiver receiver;
    private AirplaneModeStateListener mAirplaneModeStateListener;

    public AirplaneModeListener(Context context) {
        mContext = context;
        receiver = new AirplaneModeBroadcastReceiver();
    }

    public void register(AirplaneModeStateListener listener) {
        mAirplaneModeStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int state = 0;
                try {
                    state = Settings.Global.getInt(context.getContentResolver(),
                            Settings.Global.AIRPLANE_MODE_ON);
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }
                switch (state) {
                    case 0:
                        if (mAirplaneModeStateListener != null) {
                            mAirplaneModeStateListener.onStateDisabled();
                        }
                        break;
                    case 1:
                        if (mAirplaneModeStateListener != null) {
                            mAirplaneModeStateListener.onStateEnabled();
                        }
                        break;
                }
            }
        }
    }


    public interface AirplaneModeStateListener {
        void onStateDisabled();

        void onStateEnabled();

    }
}
