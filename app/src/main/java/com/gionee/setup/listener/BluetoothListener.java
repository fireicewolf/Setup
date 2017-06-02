package com.gionee.setup.listener;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BluetoothListener {

    private Context mContext;
    private BluetoothBroadcastReceiver receiver;
    private BluetoothStateListener mBluetoothStateListener;

    public BluetoothListener(Context context) {
        mContext = context;
        receiver = new BluetoothBroadcastReceiver();
    }

    public void register(BluetoothStateListener listener) {
        mBluetoothStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private class BluetoothBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        if (mBluetoothStateListener != null) {
                            mBluetoothStateListener.onStateDisabled();
                        }
                        break;
                    case BluetoothAdapter.STATE_ON:
                        if (mBluetoothStateListener != null) {
                            mBluetoothStateListener.onStateEnabled();
                        }
                        break;
                }
            }
        }
    }


    public interface BluetoothStateListener {
        void onStateDisabled();

        void onStateEnabled();

    }
}
