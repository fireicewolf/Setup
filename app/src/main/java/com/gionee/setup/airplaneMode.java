package com.gionee.setup;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

class airplaneMode {

    static boolean isAirplaneModeEnabled(Context context) {
        int isAirplaneMode = Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0);
        return (isAirplaneMode == 1);
    }

    static void setAirplaneModeEnabled(Context context, boolean enable){
            Settings.Global.putInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, enable ? 1 : 0);
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED).
                    putExtra("state", enable);
            context.sendBroadcast(intent);
    }

}
