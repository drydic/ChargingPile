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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.jit.util.DecodeUtil;
import com.tiangong.chargingPile.jit.util.protocalModel.ReceiveCurrentData;
import com.tiangong.chargingPile.jit.util.protocalModel.ReceiveStd;
import com.tiangong.chargingPile.model.ChargingPaileData;
import com.tiangong.chargingPile.recever.USBBroadcastReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout oneKeyRl;
    private RelativeLayout faultSimulationRl;
    private RelativeLayout dataManagerRl;
    private RelativeLayout dataOutputRl;
    private RelativeLayout systemSrttingRl;
    private RelativeLayout paramaterSettingRl;


//    //USB管理器:负责管理USB设备的类
//    private UsbManager manager;
//    //设备列表
//    private HashMap<String, UsbDevice> deviceList;
//    //找到的USB设备
//
//    //代表USB设备的一个接口
//    private UsbInterface mInterface;
//
//    //代表一个接口的某个节点的类:写数据节点
//    private UsbEndpoint usbEpOut;
//    //代表一个接口的某个节点的类:读数据节点
//    private UsbEndpoint usbEpIn;
//    private UsbDeviceConnection mDeviceConnection;
//    private UsbDevice mUsbDevice;
//    private static final String ACTION_USB_PERMISSION =
//            "com.android.example.USB_PERMISSION";
//    private PendingIntent mPermissionIntent;
//
//
//    private USBBroadcastReceiver receiver = new USBBroadcastReceiver();
//    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
//                Log.i("tag", "ACTION_USB_DEVICE_ATTACHED\n: ");
//            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
//                Log.i("tag", "ACTION_USB_DEVICE_DETACHED\n");
//            }
//        }
//    };
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        IntentFilter usbFilter = new IntentFilter();
//        usbFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        usbFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
//        registerReceiver(mUsbReceiver, usbFilter);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(mUsbReceiver);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EventBus.getDefault().register(this);
//        initUsbData();
//        //充电桩插入时间
//        ChargingPaileData.getInstance().setChargingLinkTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//        ChargingPaileData.getInstance().setChargingState("检测:\n插入阶段");
//
//        //USB状态监听

    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mDeviceConnection!=null)
//        mDeviceConnection.close();
//        ChargingPaileData.getInstance().setStopTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//        ChargingPaileData.getInstance().setChargingState("检测:检测结束");
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void lineDataAdd(String ev) {
//
//    }
//
//    private void initUsbData() {
//        //充电桩设备id：1155
//        // 获取USB设备
//        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
//        //获取到设备列表
//        deviceList = manager.getDeviceList();
//        Log.i("tag", deviceList.keySet().size() + "     111111111");
//        Log.i("tag", deviceList.toString() + "    22222222");
//        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
//
//        while (deviceIterator.hasNext()) {
//            UsbDevice usbDevice = deviceIterator.next();
//            if (usbDevice.getVendorId() == 1155) {
//                mUsbDevice = usbDevice;
//                Log.i("tag", "initUsbData: =============>找到对应USB接口");
//                break;
//            }
//        }
//
//        if (mUsbDevice != null) {
//            //权限申请
//            mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
//            manager.requestPermission(mUsbDevice, mPermissionIntent);
//            //获取设备接口
////        Log.i("tag", mUsbDevice.toString()+"          333333333333333");
////        Log.i("tag",String.valueOf(mUsbDevice.getInterfaceCount())+"           44444444444444444");
//            for (int i = 0; i < mUsbDevice.getInterfaceCount(); i++) {
//                // 一般来说一个设备都是一个接口，你可以通过getInterfaceCount()查看接口的个数
//                // 这个接口上有两个端点，分别对应OUT 和 IN
//                mInterface = mUsbDevice.getInterface(i);
//                break;
//            }
//            //用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
//            if (mInterface.getEndpoint(1) != null) {
//                usbEpOut = mInterface.getEndpoint(1);
//            }
//            if (mInterface.getEndpoint(0) != null) {
//                usbEpIn = mInterface.getEndpoint(0);
//            }
//            if (mInterface != null) {
//                // 判断是否有权限
//                if (manager.hasPermission(mUsbDevice)) {
//                    // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
//                    mDeviceConnection = manager.openDevice(mUsbDevice);
//                    if (mDeviceConnection == null) {
//                        Log.i("connection", "null");
//                        return;
//                    }
//                    if (mDeviceConnection.claimInterface(mInterface, true)) {
//                        Log.i("tag", "找到设备接口 ");
//                        Toast.makeText(this,"设备已连接",Toast.LENGTH_SHORT).show();
//                    } else {
//                        mDeviceConnection.close();
//                    }
//                } else {
//                    Log.i("tag", "没有权限 ");
//
//                }
//            } else {
//                Log.i("tag", "没有找到设备接口 ");
//                Toast.makeText(this, "设备未连接，请连接后尝试", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "未找到对应设备,请重新尝试后连接", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void initView() {
        super.initView();
        oneKeyRl = (RelativeLayout) findViewById(R.id.oneKeyTestRl);
        faultSimulationRl = (RelativeLayout) findViewById(R.id.faultSimulationRl);
        dataManagerRl = (RelativeLayout) findViewById(R.id.dataManagerRl);
        dataOutputRl = (RelativeLayout) findViewById(R.id.dataOutputRl);
        systemSrttingRl = (RelativeLayout) findViewById(R.id.SystemSettingRl);
        paramaterSettingRl = (RelativeLayout) findViewById(R.id.paramaterSettingRl);
    }

    @Override
    protected void initListner() {
        super.initListner();
        oneKeyRl.setOnClickListener(this);
        faultSimulationRl.setOnClickListener(this);
        dataManagerRl.setOnClickListener(this);
        dataOutputRl.setOnClickListener(this);
        systemSrttingRl.setOnClickListener(this);
        paramaterSettingRl.setOnClickListener(this);
    }


    //数据读取存入本地的单例Model
//    private static void convertToModel(ReceiveCurrentData receive, ChargingPaileData charge) {
//        charge.setVoltageMeasure(receive.getCurrentStd().getStd_realMeasureVol() + "V");
//        charge.setVoltageSetting(receive.getCurrentStd().getStd_measureVol() + "V");
//        charge.setVoltageError((receive.getCurrentStd().getStd_realMeasureVol() - receive.getCurrentStd().getStd_measureVol()) + "V");
//        charge.setVoltageRipple(receive.getCurrentStd().getStd_realMeasureWaveVol() + "V");
//        charge.setElectrcityMeasure(receive.getCurrentStd().getStd_realMeasureAn() + "A");
//        charge.setElectrcitySetting(receive.getCurrentStd().getStd_measureAn() + "V");
//        charge.setElectrcityError((receive.getCurrentStd().getStd_realMeasureAn() - receive.getCurrentStd().getStd_measureAn()) + "A");
////        charge.setChargingPileId(receive.getStc().getStcCrm_id() + "");
//        charge.setVersion("V" + receive.getStc().getStcChm_version());
//        // TODO: 待定
//        charge.setElectrcityRipple("--");
//        charge.setAssistPower("--");
//        charge.setEnvironmentTemperature("--");
//        charge.setEnvironmentHumidity("--");
//        charge.setTestState(receive.getStc().isStcBro() ? "充电中" : "未充电");
//
//        charge.setMostVoltage(receive.getStc().getStcCml_maxVol() + "V");
//        charge.setMostElectrcity(receive.getStc().getStcCml_maxAn() + "A");
//
//        charge.setCHMState(receive.getStc().getChmHex() == null ? "获取中" : "已获取");
//        charge.setCHMTestContent(receive.getStc().getChmHex());
//        charge.setBHMState(receive.getStc().getBhmHex() == null ? "获取中" : "已获取");
//        charge.setBHMTestContent(receive.getStc().getBhmHex());
//        charge.setCRMState(receive.getStc().getCrmHex() == null ? "获取中" : "已获取");
//        charge.setCRMTestContent(receive.getStc().getCrmHex());
//        charge.setBCPState(receive.getStc().getBcpHex() == null ? "获取中" : "已获取");
//        charge.setBCPTestContent(receive.getStc().getBcpHex());
//        charge.setCTSState(receive.getStc().getCtsHex() == null ? "获取中" : "已获取");
//        charge.setCTSTestContent(receive.getStc().getCtsHex());
//        charge.setCMLState(receive.getStc().getCmlHex() == null ? "获取中" : "已获取");
//        charge.setCMLTestContent(receive.getStc().getCmlHex());
//        charge.setBROState(receive.getStc().getBroHex() == null ? "获取中" : "已获取");
//        charge.setBROTestContent(receive.getStc().getBroHex());
//        charge.setCROState(receive.getStc().getCroHex() == null ? "获取中" : "已获取");
//        charge.setCROTestContent(receive.getStc().getCroHex());
//        charge.setBSTState(receive.getStc().getBstHex() == null ? "获取中" : "已获取");
//        charge.setBSTTestContent(receive.getStc().getBstHex());
//        charge.setCSTState(receive.getStc().getCstHex() == null ? "获取中" : "已获取");
//        charge.setCSTTestContent(receive.getStc().getCstHex());
//        charge.setCSDState(receive.getStc().getCsdHex() == null ? "获取中" : "已获取");
//        charge.setCSDTestContent(receive.getStc().getCsdHex());
//        charge.setBSDState(receive.getStc().getBsdHex() == null ? "获取中" : "已获取");
//        charge.setBSDTestContent(receive.getStc().getBsdHex());
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oneKeyTestRl:
//                if (mUsbDevice != null && mUsbDevice.getVendorId() == 1155) {
//                    //获取正确的USB 对象 进入读取操作
//                    readUsbData();
//                } else {
//                    //未获得单片机USB对象，则先获取单片机USB 接口再进行读取数据
//                    initUsbData();
//                    if (mUsbDevice != null && mUsbDevice.getVendorId() == 1155){
//                        readUsbData();
//                    }
//
//                }
                UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
                Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
                String msg = "硬件故障，请重新连接";
                while (deviceIterator.hasNext()) {
                    UsbDevice usbDevice = deviceIterator.next();
                    if (usbDevice.getVendorId() == 232) {
                        startActivity(new Intent(this,LoginActivity.class));
                        msg = "设备连接成功";
                        break;
                    }
                }
                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
                break;
            case R.id.faultSimulationRl:
                startActivity(new Intent(this, FaultSimulationActivity.class));
                break;
            case R.id.dataManagerRl:
                startActivity(new Intent(this, DataManagerActivity.class));
                break;
            case R.id.dataOutputRl:
                startActivity(new Intent(this, DataOutPutActivity.class));
                break;
            case R.id.SystemSettingRl:
                startActivity(new Intent(this, SystemSettingActivity.class));
                break;
            case R.id.paramaterSettingRl:
                startActivity(new Intent(this, ParamaterSettingActivity.class));
                break;
        }
    }


    //子线程读取单片机发送过来的数据
//    private void readUsbData() {
//        ChargingPaileData.getInstance().setTestDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        //充电桩开始检测时间
//        ChargingPaileData.getInstance().setStartTestTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//        ChargingPaileData.getInstance().setChargingState("检测:\n开始阶段");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                byte[] send = new byte[1];
//                send[0] = 0x10;
//                int sendCount = mDeviceConnection.bulkTransfer(usbEpOut, send, send.length, 5000);
//                //配置时间
//                ChargingPaileData.getInstance().setConfigTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//                ChargingPaileData.getInstance().setChargingState("检测:\n配置阶段");
//                ReceiveCurrentData receiveData = new ReceiveCurrentData();
//                long l = System.currentTimeMillis();//10000
//
//                //清空历史数据
//                ChargingPaileData.getInstance().getVoltages().clear();
//                ChargingPaileData.getInstance().getElectrcitys().clear();
//                ChargingPaileData.getInstance().getTimes().clear();
//                while (System.currentTimeMillis() - l < 1000 * 20) {
//                    byte[] receiveytes = new byte[16];
//                    int readCount = mDeviceConnection.bulkTransfer(usbEpIn, receiveytes, receiveytes.length, 10000);
//                    DecodeUtil.decode(receiveytes, receiveData);
//                    //todo 1.转换为显示的model
//                    convertToModel(receiveData, ChargingPaileData.getInstance());
//                    ChargingPaileData.getInstance().getVoltages().add((float) receiveData.getCurrentStd().getStd_realMeasureVol());
//                    ChargingPaileData.getInstance().getElectrcitys().add((float) receiveData.getCurrentStd().getStd_realMeasureAn());
//                    ChargingPaileData.getInstance().getTimes().add( new SimpleDateFormat("ss:SSS").format(new Date()));
//                    long l1 = System.currentTimeMillis();
//                    Log.i("tag", "run: ===========>"+l1);
//                    EventBus.getDefault().post("add");
//                }
//                //结束时间
//                ChargingPaileData.getInstance().setEndCharg(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//                ChargingPaileData.getInstance().setChargingState("检测:\n结束阶段");
//                ChargingPaileData.getInstance().setEnd(true);
//                ChargingPaileData instance = ChargingPaileData.getInstance();
//                ChargingPaileData.getInstance().setTestTime("0.3min");
//                //todo 2.将显示的model序列化成json
//                //释放Usb连接
//                mUsbDevice=null;
//                mInterface = null;
//                mDeviceConnection.close();
//                mDeviceConnection = null;
//            }
//        }).start();
//
//        startActivity(new Intent(this, OneKeyTestActivity.class));
//        //释放USB 连接
//
//    }
}
