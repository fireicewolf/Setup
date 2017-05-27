package com.gionee.setup;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch mobileDataSwitch;
    Switch wifiSwitch;
    Switch bluetoothSwitch;
    TextView mobileDataStatus;
    TextView wifiStatus;
    TextView bluetoothStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WifiManager mWifiManager = (WifiManager) getApplicationContext().
                getSystemService(Context.WIFI_SERVICE);

        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mobileDataSwitch = (Switch)findViewById(R.id.mobileDate_switch);
        mobileDataStatus = (TextView)findViewById(R.id.mobileDate_status);

        wifiSwitch = (Switch)findViewById(R.id.wifi_switch);
        wifiStatus = (TextView)findViewById(R.id.wifi_status);

        bluetoothSwitch = (Switch)findViewById(R.id.bluetooth_switch);
        bluetoothStatus = (TextView)findViewById(R.id.bluetooth_status);

        final Context context = getApplication();

        if (moblieData.isMobileDataEnabled(context)) {
            mobileDataSwitch.setChecked(true);
            mobileDataStatus.setText(R.string.status_on);
        }

        else {
            mobileDataSwitch.setChecked(false);
            mobileDataStatus.setText(R.string.status_off);
        }

        if (mWifiManager.isWifiEnabled()){
            wifiSwitch.setChecked(true);
            wifiStatus.setText(R.string.status_on);
        }

        else {
            wifiSwitch.setChecked(false);
            wifiStatus.setText(R.string.status_off);
        }

        if (mBluetoothAdapter.isEnabled()){
            bluetoothSwitch.setChecked(true);
            bluetoothStatus.setText(R.string.status_on);
        }
        else {
            bluetoothSwitch.setChecked(false);
            bluetoothStatus.setText(R.string.status_off);
        }

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
                    if (!mWifiManager.isWifiEnabled()) {
                        mWifiManager.setWifiEnabled(true);
                        wifiStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (mWifiManager.isWifiEnabled()) {
                        mWifiManager.setWifiEnabled(false);
                        wifiStatus.setText(R.string.status_off);
                    }
                }
            }
        });

        bluetoothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable();
                        bluetoothStatus.setText(R.string.status_on);
                    }
                }
                else {
                    if (mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.disable();
                        bluetoothStatus.setText(R.string.status_off);
                    }
                }
            }
        });
    }
}
