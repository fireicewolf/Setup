package com.gionee.setup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gionee.setup.connections.airplaneMode;
import com.gionee.setup.connections.bluetooth;
import com.gionee.setup.connections.moblieData;
import com.gionee.setup.connections.wifi;
import com.gionee.setup.listener.AirplaneModeListener;
import com.gionee.setup.listener.BluetoothListener;
import com.gionee.setup.listener.WiFiListener;

public class MainActivity extends AppCompatActivity {

    Switch airplaneModeSwitch;
    Switch mobileDataSwitch;
    Switch wifiSwitch;
    Switch bluetoothSwitch;
    TextView airplaneModeStatus;
    TextView mobileDataStatus;
    TextView wifiStatus;
    TextView bluetoothStatus;

    private AirplaneModeListener airplaneModeListener;
    private WiFiListener wifiListener;
    private BluetoothListener bluetoothListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplication();

        airplaneModeListener = new AirplaneModeListener(this);
        airplaneModeListener.register(new AirplaneModeListener.AirplaneModeStateListener() {
            @Override
            public void onStateDisabled() {
                airplaneModeSwitch.setChecked(false);
                airplaneModeStatus.setText(R.string.status_off);
                mobileDataSwitch.setEnabled(true);
            }

            @Override
            public void onStateEnabled() {
                airplaneModeSwitch.setChecked(true);
                airplaneModeStatus.setText(R.string.status_on);
                mobileDataSwitch.setEnabled(false);
            }
        });

        wifiListener = new WiFiListener(this);
        wifiListener.register(new WiFiListener.WLANStateListener(){
            @Override
            public void onStateDisabled() {
                wifiSwitch.setChecked(false);
                wifiStatus.setText(R.string.status_off);
            }

            @Override
            public void onStateEnabled() {
                wifiSwitch.setChecked(true);
                wifiStatus.setText(R.string.status_on);
            }
        });

        bluetoothListener = new BluetoothListener(this);
        bluetoothListener.register(new BluetoothListener.BluetoothStateListener(){
            @Override
            public void onStateDisabled() {
                bluetoothSwitch.setChecked(false);
                bluetoothStatus.setText(R.string.status_off);
            }

            @Override
            public void onStateEnabled() {
                bluetoothSwitch.setChecked(true);
                bluetoothStatus.setText(R.string.status_on);
            }
        });

        airplaneModeSwitch = (Switch)findViewById(R.id.airplaneMode_switch);
        airplaneModeStatus = (TextView)findViewById(R.id.airplaneMode_status);

        mobileDataSwitch = (Switch)findViewById(R.id.mobileDate_switch);
        mobileDataStatus = (TextView)findViewById(R.id.mobileDate_status);

        wifiSwitch = (Switch)findViewById(R.id.wifi_switch);
        wifiStatus = (TextView)findViewById(R.id.wifi_status);

        bluetoothSwitch = (Switch)findViewById(R.id.bluetooth_switch);
        bluetoothStatus = (TextView)findViewById(R.id.bluetooth_status);

        airplaneModeSwitch.setChecked((airplaneMode.isAirplaneModeEnabled(context)));
        airplaneModeStatus.setText(airplaneMode.isAirplaneModeEnabled(context)
                ? R.string.status_on : R.string.status_off);
        mobileDataSwitch.setChecked((moblieData.isMobileDataEnabled(context)));
        mobileDataStatus.setText(moblieData.isMobileDataEnabled(context)
                ? R.string.status_on : R.string.status_off);

        bluetoothSwitch.setChecked((bluetooth.isBluetoothEnabled()));
        bluetoothStatus.setText(bluetooth.isBluetoothEnabled()
                ? R.string.status_on : R.string.status_off);

        airplaneModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!airplaneMode.isAirplaneModeEnabled(context)) {
                        airplaneMode.setAirplaneModeEnabled(context,true);
                        airplaneModeStatus.setText(R.string.status_on);
                        mobileDataSwitch.setEnabled(false);
                    }
                }
                else {
                    if (airplaneMode.isAirplaneModeEnabled(context)) {
                        airplaneMode.setAirplaneModeEnabled(context,false);
                        airplaneModeStatus.setText(R.string.status_off);
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
                        mobileDataStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (moblieData.isMobileDataEnabled(context)) {
                        moblieData.setMobileDataEnabled(context,false);
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
                        bluetoothStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (bluetooth.isBluetoothEnabled()) {
                        bluetooth.setBluetoothEnable(false);
                        bluetoothStatus.setText(R.string.status_off);
                    }
                }
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
        if (bluetoothListener != null) {
            bluetoothListener.unregister();
        }
    }
}
