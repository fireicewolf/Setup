package com.gionee.setup;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;


class moblieData {

    static boolean isMobileDataEnabled(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try{
            Method getMobileDataEnabled = mTelephonyManager.getClass().getMethod("getDataEnabled");
            if (null != getMobileDataEnabled)
            {
                return (boolean) (Boolean) getMobileDataEnabled.invoke(mTelephonyManager);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void setMobileDataEnabled(Context context,boolean MobileDataEnabled){
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);

        Method setMobileDataEnable;
        try {
            setMobileDataEnable = mTelephonyManager.getClass().getMethod("setDataEnabled", boolean.class);
            setMobileDataEnable.invoke(mTelephonyManager, MobileDataEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
