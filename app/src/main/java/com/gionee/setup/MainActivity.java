package com.gionee.setup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch airplaneModeSwitch;
    Switch mobileDataSwitch;
    Switch wifiSwitch;
    Switch bluetoothSwitch;
    TextView airplaneModeStatus;
    TextView mobileDataStatus;
    TextView wifiStatus;
    TextView bluetoothStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplication();

        airplaneModeSwitch = (Switch)findViewById(R.id.airplaneMode_switch);
        airplaneModeStatus = (TextView)findViewById(R.id.airplaneMode_status);

        mobileDataSwitch = (Switch)findViewById(R.id.mobileDate_switch);
        mobileDataStatus = (TextView)findViewById(R.id.mobileDate_status);

        wifiSwitch = (Switch)findViewById(R.id.wifi_switch);
        wifiStatus = (TextView)findViewById(R.id.wifi_status);

        bluetoothSwitch = (Switch)findViewById(R.id.bluetooth_switch);
        bluetoothStatus = (TextView)findViewById(R.id.bluetooth_status);

        if (airplaneMode.isAirplaneModeEnabled(context)) {
            airplaneModeSwitch.setChecked(true);
            airplaneModeStatus.setText(R.string.status_on);
            mobileDataSwitch.setEnabled(false);
        }

        else {
            airplaneModeSwitch.setChecked(false);
            airplaneModeStatus.setText(R.string.status_off);
            mobileDataSwitch.setEnabled(true);
        }

        if (moblieData.isMobileDataEnabled(context)) {
            mobileDataSwitch.setChecked(true);
            mobileDataStatus.setText(R.string.status_on);
        }

        else {
            mobileDataSwitch.setChecked(false);
            mobileDataStatus.setText(R.string.status_off);
        }

        if (wifi.isWiFiEnabled(context)){
            wifiSwitch.setChecked(true);
            wifiStatus.setText(R.string.status_on);
        }

        else {
            wifiSwitch.setChecked(false);
            wifiStatus.setText(R.string.status_off);
        }

        if (bluetooth.isBluetoothEnabled()){
            bluetoothSwitch.setChecked(true);
            bluetoothStatus.setText(R.string.status_on);
        }
        else {
            bluetoothSwitch.setChecked(false);
            bluetoothStatus.setText(R.string.status_off);
        }

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
                        wifiStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (wifi.isWiFiEnabled(context)) {
                        wifi.setWiFiEnabled(context, false);
                        wifiStatus.setText(R.string.status_off);
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
}
