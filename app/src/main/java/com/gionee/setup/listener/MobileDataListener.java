package com.gionee.setup.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MobileDataListener {
    private Context mContext;
    private MobileDataBroadcastReceiver receiver;
    private MobileDataStateListener mMobileDataStateListener;

    public MobileDataListener(Context context) {
        mContext = context;
        receiver = new MobileDataBroadcastReceiver();
    }

    public void register(MobileDataStateListener listener) {
        mMobileDataStateListener = listener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            mContext.registerReceiver(receiver, filter);
        }
    }

    public void unregister() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
        }
    }

    private static class NetworkUtil {
        static final int NONE_NETWOKR = -1;
        static final int MOBILE_OFF= 0;
        static final int MOBILE_ON = 1;

        private NetworkUtil() {}

        /**
         * 获取网络状态
         *
         * @param context
         * @return one of TYPE_NONE, TYPE_MOBILE, TYPE_WIFI
         * @permission android.permission.ACCESS_NETWORK_STATE
         */
         static int getNetWorkStates(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return NONE_NETWOKR;//没网
            }

            int type = activeNetworkInfo.getType();
            if(type == ConnectivityManager.TYPE_MOBILE) {
                return MOBILE_ON;
            }
            else if(type != ConnectivityManager.TYPE_MOBILE) {
                return MOBILE_OFF;
            }

//            switch (type) {
//                case ConnectivityManager.TYPE_MOBILE:
//                    return TYPE_MOBILE;//移动数据
//                case ConnectivityManager.TYPE_WIFI:
//                    return TYPE_WIFI;//WIFI
//                default:
//                    break;
//            }
            return NONE_NETWOKR;
        }
    }

    private class MobileDataBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int netWorkStates = NetworkUtil.getNetWorkStates(context);

            switch (netWorkStates) {
                case NetworkUtil.NONE_NETWOKR:
                    mMobileDataStateListener.onStateDisabled();
                    break;
                case NetworkUtil.MOBILE_ON:
                    mMobileDataStateListener.onStateEnabled();
                    break;
            }
        }
    }


    public interface MobileDataStateListener {
        void onStateDisabled();

        void onStateEnabled();

    }
}
