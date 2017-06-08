package com.gionee.setup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.gionee.setup.connections.airplaneMode;
import com.gionee.setup.connections.bluetooth;
import com.gionee.setup.connections.moblieData;
import com.gionee.setup.connections.wifi;
import com.gionee.setup.listener.AirplaneModeListener;
import com.gionee.setup.listener.BluetoothListener;
import com.gionee.setup.listener.WiFiListener;
import com.gionee.setup.listener.WiFiSignalStateListener;

public class MainActivity extends AppCompatActivity {

    Switch airplaneModeSwitch;
    Switch mobileDataSwitch;
    Switch wifiSwitch;
    Switch bluetoothSwitch;
    TextView airplaneModeStatus;
    TextView mobileDataStatus;
    TextView wifiStatus;
    TextView bluetoothStatus;
    ImageView airplaneModeIcon;
    ImageView mobileDataIcon;
    ImageView bluetoothIcon;
    ImageView wifiIcon;

    private AirplaneModeListener airplaneModeListener;
    private WiFiListener wifiListener;
    private WiFiSignalStateListener wifiSignalStateListener;
    private BluetoothListener bluetoothListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplication();

        airplaneModeSwitch = (Switch) findViewById(R.id.airplaneMode_switch);
        airplaneModeStatus = (TextView) findViewById(R.id.airplaneMode_status);
        airplaneModeIcon = (ImageView) findViewById(R.id.airplaneMode_icon);

        mobileDataSwitch = (Switch) findViewById(R.id.mobileDate_switch);
        mobileDataStatus = (TextView) findViewById(R.id.mobileDate_status);
        mobileDataIcon = (ImageView) findViewById(R.id.mobileDate_icon);

        wifiSwitch = (Switch) findViewById(R.id.wifi_switch);
        wifiStatus = (TextView) findViewById(R.id.wifi_status);
        wifiIcon = (ImageView) findViewById(R.id.wifi_icon);

        bluetoothSwitch = (Switch) findViewById(R.id.bluetooth_switch);
        bluetoothStatus = (TextView) findViewById(R.id.bluetooth_status);
        bluetoothIcon = (ImageView) findViewById(R.id.bluetooth_icon);

        airplaneModeSwitch.setChecked((airplaneMode.isAirplaneModeEnabled(context)));
        airplaneModeStatus.setText(airplaneMode.isAirplaneModeEnabled(context)
                ? R.string.status_on : R.string.status_off);
        airplaneModeIcon.getDrawable().setLevel((airplaneMode.isAirplaneModeEnabled(context)
                ? 1 : 0));

        mobileDataSwitch.setChecked((moblieData.isMobileDataEnabled(context)));
        mobileDataStatus.setText(moblieData.isMobileDataEnabled(context)
                ? R.string.status_on : R.string.status_off);
        mobileDataIcon.getDrawable().setLevel(moblieData.isMobileDataEnabled(context)
                ? 1 : 0);

        bluetoothSwitch.setChecked((bluetooth.isBluetoothEnabled()));
        bluetoothStatus.setText(bluetooth.isBluetoothEnabled()
                ? R.string.status_on : R.string.status_off);
        bluetoothIcon.getDrawable().setLevel(bluetooth.isBluetoothEnabled()
                ? 1 : 0);

        airplaneModeListener = new AirplaneModeListener(this);

        airplaneModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!airplaneMode.isAirplaneModeEnabled(context)) {
                        airplaneMode.setAirplaneModeEnabled(context,true);
                        airplaneModeStatus.setText(R.string.status_on);
                        airplaneModeIcon.getDrawable().setLevel(1);
                        mobileDataSwitch.setEnabled(false);

                    }
                }
                else {
                    if (airplaneMode.isAirplaneModeEnabled(context)) {
                        airplaneMode.setAirplaneModeEnabled(context,false);
                        airplaneModeStatus.setText(R.string.status_off);
                        airplaneModeIcon.getDrawable().setLevel(0);
                        mobileDataSwitch.setEnabled(true);
                    }
                }
            }
        });

        mobileDataSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!moblieData.isMobileDataEnabled(context)) {
                        moblieData.setMobileDataEnabled(context,true);
                        mobileDataIcon.getDrawable().setLevel(1);
                        mobileDataStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (moblieData.isMobileDataEnabled(context)) {
                        moblieData.setMobileDataEnabled(context,false);
                        mobileDataIcon.getDrawable().setLevel(0);
                        mobileDataStatus.setText(R.string.status_off);
                    }
                }
            }
        });

        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!wifi.isWiFiEnabled(context)) {
                        wifi.setWiFiEnabled(context, true);
                    }
                }
                else {
                    if (wifi.isWiFiEnabled(context)) {
                        wifi.setWiFiEnabled(context, false);
                    }
                }
            }
        });

        bluetoothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!bluetooth.isBluetoothEnabled()) {
                        bluetooth.setBluetoothEnable(true);
                        bluetoothIcon.getDrawable().setLevel(1);
                        bluetoothStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (bluetooth.isBluetoothEnabled()) {
                        bluetooth.setBluetoothEnable(false);
                        bluetooth.setBluetoothEnable(true);
                        bluetoothIcon.getDrawable().setLevel(0);
                        bluetoothStatus.setText(R.string.status_off);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        airplaneModeListener.register(new AirplaneModeListener.AirplaneModeStateListener() {
            @Override
            public void onStateDisabled() {
                airplaneModeSwitch.setChecked(false);
                airplaneModeStatus.setText(R.string.status_off);
                airplaneModeIcon.getDrawable().setLevel(0);
                mobileDataSwitch.setEnabled(true);
            }

            @Override
            public void onStateEnabled() {
                airplaneModeSwitch.setChecked(true);
                airplaneModeStatus.setText(R.string.status_on);
                airplaneModeIcon.getDrawable().setLevel(1);
                mobileDataSwitch.setEnabled(false);
            }
        });

        wifiListener = new WiFiListener(this);
        wifiListener.register(new WiFiListener.WLANStateListener(){
            @Override
            public void onStateDisabled() {
                wifiSwitch.setChecked(false);
                wifiSwitch.setEnabled(true);
                wifiIcon.getDrawable().setLevel(0);
                wifiStatus.setText(R.string.status_off);
            }

            @Override
            public void onStateDisabling() {
                wifiSwitch.setEnabled(false);
            }

            @Override
            public void onStateEnabled() {
                wifiSwitch.setChecked(true);
                wifiSwitch.setEnabled(true);
                wifiIcon.getDrawable().setLevel(1);
                wifiStatus.setText(R.string.status_on);
            }

            @Override
            public void onStateEnabling() {
                wifiSwitch.setEnabled(false);
            }

            @Override
            public void onStateDefault() {
                wifiSwitch.setChecked(false);
                wifiSwitch.setEnabled(true);
                wifiStatus.setText(R.string.status_off);
            }
        });

        wifiSignalStateListener = new WiFiSignalStateListener(this);
        wifiSignalStateListener.register(new WiFiSignalStateListener.WLANSignalStateListener() {
            @Override
            public void onStateLEVEL_NONE() {
                wifiIcon.getDrawable().setLevel(1);
            }

            @Override
            public void onStateLEVEL_0() {
                wifiIcon.getDrawable().setLevel(2);
            }

            @Override
            public void onStateLEVEL_1() {
                wifiIcon.getDrawable().setLevel(3);
            }

            @Override
            public void onStateLEVEL_2() {
                wifiIcon.getDrawable().setLevel(4);
            }

            @Override
            public void onStateLEVEL_3() {
                wifiIcon.getDrawable().setLevel(5);
            }
        });

        bluetoothListener = new BluetoothListener(this);
        bluetoothListener.register(new BluetoothListener.BluetoothStateListener(){
            @Override
            public void onStateDisabled() {
                bluetoothSwitch.setChecked(false);
                bluetoothSwitch.setEnabled(true);
                bluetoothIcon.getDrawable().setLevel(0);
                bluetoothStatus.setText(R.string.status_off);
            }

            @Override
            public void onStateDisabling() {
                bluetoothSwitch.setEnabled(false);
            }

            @Override
            public void onStateEnabled() {
                bluetoothSwitch.setChecked(true);
                bluetoothSwitch.setEnabled(true);
                bluetoothIcon.getDrawable().setLevel(1);
                bluetoothStatus.setText(R.string.status_on);
            }

            @Override
            public void onStateEnabling() {
                bluetoothSwitch.setEnabled(false);
            }

            @Override
            public void onStateDefault() {
                bluetoothSwitch.setChecked(false);
                bluetoothSwitch.setEnabled(true);
                bluetoothIcon.getDrawable().setLevel(0);
                bluetoothStatus.setText(R.string.status_off);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (airplaneModeListener != null) {
            airplaneModeListener.unregister();
        }
        if (wifiListener != null) {
            wifiListener.unregister();
        }
        if (wifiSignalStateListener != null) {
            wifiSignalStateListener.unregister();
        }
        if (bluetoothListener != null) {
            bluetoothListener.unregister();
        }
    }
}
