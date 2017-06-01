package com.gionee.setup;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

class bluetooth {
    private static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    static boolean isBluetoothEnabled() {
        return mBluetoothAdapter.isEnabled();
    }

    static void setBluetoothEnable(boolean enable) {
        if (enable) {
            mBluetoothAdapter.enable();
        }

        else {
            mBluetoothAdapter.disable();
        }
    }
}
