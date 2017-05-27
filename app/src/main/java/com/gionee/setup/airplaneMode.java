package com.gionee.setup;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

class airplaneMode {

    static boolean isAirplaneModeEnabled(Context context) {
        if (Settings.Global.getString(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON).equals("1")){
            return true;
        }
        else{
            return false;
        }
    }

    static void setAirplaneModeEnabled(Context context, boolean airplaneModeEnabled){
        if (airplaneModeEnabled) {
            Settings.Global.putString(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, "1");
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            context.sendBroadcast(intent);
        }
        else {
            Settings.Global.putString(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, "0");
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            context.sendBroadcast(intent);
        }
    }

}
