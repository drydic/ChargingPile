package com.tiangong.chargingPile.recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/3.
 */

public class USBBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.hardware.usb.action.USB_STATE")){
            if (intent.getExtras().getBoolean("connected")){
                // usb 插入
                Log.i("tag", "onReceive: -------------------------------connected");
                Toast.makeText(context, "插入", Toast.LENGTH_LONG).show();

            }else {
                Log.i("tag", "onReceive: -------------------------------unconnected");
                Toast.makeText(context,"拔出",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
