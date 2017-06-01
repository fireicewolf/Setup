package com.gionee.setup.connections;

import android.bluetooth.BluetoothAdapter;

public class bluetooth {
    private static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public static boolean isBluetoothEnabled() {
        return mBluetoothAdapter.isEnabled();
    }

    public static void setBluetoothEnable(boolean enable) {
        if (enable) {
            mBluetoothAdapter.enable();
        }

        else {
            mBluetoothAdapter.disable();
        }
    }
}
