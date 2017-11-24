package com.tiangong.chargingPile.activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.jit.util.DecodeUtil;
import com.tiangong.chargingPile.jit.util.protocalModel.ReceiveCurrentData;
import com.tiangong.chargingPile.model.ChargingPaileData;
import com.tiangong.chargingPile.recever.USBBroadcastReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button jump;
    private Button ensure;
    private EditText employeeID;
    private EditText chargingPileID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void initView() {
        super.initView();
        jump = (Button) findViewById(R.id.login_jump);
        ensure = (Button) findViewById(R.id.login_ensure);
        employeeID = (EditText) findViewById(R.id.employeeID);
        chargingPileID = (EditText) findViewById(R.id.chargID);
    }

    @Override
    protected void initListner() {
        super.initListner();
        jump.setOnClickListener(this);
        ensure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_ensure:
                //员工选择登录则录入员工的ID和充电桩ID
                if (!employeeID.getText().toString().equals("") && !chargingPileID.getText().toString().equals("")) {
                    ChargingPaileData data = ChargingPaileData.getInstance();
                    data.setEmployeeID(employeeID.getText().toString());
                    data.setChargingPileId(chargingPileID.getText().toString());
                    startActivity(new Intent(this, OneKeyTestActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "请输入员工号码和充电桩ID", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_jump:
                startActivity(new Intent(this, OneKeyTestActivity.class));
                finish();
                break;
        }
    }

}
