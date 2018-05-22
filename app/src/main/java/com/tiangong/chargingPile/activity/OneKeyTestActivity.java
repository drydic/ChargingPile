package com.tiangong.chargingPile.activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.databinding.ActivityOneKeyTestBinding;
import com.tiangong.chargingPile.jit.util.DecodeUtil;
import com.tiangong.chargingPile.jit.util.protocalModel.ReceiveCurrentData;
import com.tiangong.chargingPile.listner.CoupleChartGestureListener;
import com.tiangong.chargingPile.model.ChargingPaileData;
import com.tiangong.chargingPile.recever.USBBroadcastReceiver;
import com.tiangong.chargingPile.valueformat.MyXAxisValueFormatter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class OneKeyTestActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private Button start;
    private ChargingPaileData data = ChargingPaileData.getInstance();
    private ActivityOneKeyTestBinding binding;
    private LineChart voltageLineChart;
    private LineChart electrcityLineChart;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startActivity(new Intent(OneKeyTestActivity.this, OneKeyTestReportActivity.class));
                    finish();
                    break;
                case 2:
                    isRead = false;
                    handler.sendEmptyMessageDelayed(1, 3000);
                    break;
            }
            return true;
        }
    });
    private Button stopMsg;


    //USB管理器:负责管理USB设备的类
    private UsbManager manager;
    //设备列表
    private HashMap<String, UsbDevice> deviceList;
    //找到的USB设备

    //代表USB设备的一个接口
    private UsbInterface mInterface;

    //代表一个接口的某个节点的类:写数据节点
    private UsbEndpoint usbEpOut;
    //代表一个接口的某个节点的类:读数据节点
    private UsbEndpoint usbEpIn;
    private UsbDeviceConnection mDeviceConnection;
    private UsbDevice mUsbDevice;
    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";
    private PendingIntent mPermissionIntent;

    //读取板子 数据子线程控制判断
    private boolean isRead = true;

    private USBBroadcastReceiver receiver = new USBBroadcastReceiver();
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                Log.i("tag", "ACTION_USB_DEVICE_ATTACHED\n: ");
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                Log.i("tag", "ACTION_USB_DEVICE_DETACHED\n");
            }
        }
    };
    private long startTiem;

    private boolean isReceiveMsg;  //标识用户点击开始标识

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter usbFilter = new IntentFilter();
        usbFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        usbFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(mUsbReceiver, usbFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDeviceConnection != null)
            mDeviceConnection.close();
        ChargingPaileData.getInstance().setStopTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }


    //点击停止 发送停止通信报文
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stopMsg(String ev) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_one_key_test);
        ChargingPaileData.getInstance().setHandler(handler);
        binding.setChargingPileMode(data);
        initUsbData();
        readUsbData();
        //充电桩插入时间
        ChargingPaileData.getInstance().setChargingLinkTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    @Override
    protected void initView() {
        super.initView();
        back = (ImageView) findViewById(R.id.img_back);
        stopMsg = (Button) findViewById(R.id.oneKey_test_stop);
        start = (Button) findViewById(R.id.oneKey_test_start);
        voltageLineChart = (LineChart) findViewById(R.id.voltageLineChart);
        electrcityLineChart = (LineChart) findViewById(R.id.electrcityLineChart);
        initChart();
    }

    private void initChart() {
        voltageLineChart.getDescription().setEnabled(false);
        voltageLineChart.setNoDataText("数据加载中");
        voltageLineChart.setNoDataTextColor(getResources().getColor(R.color.color666));
        voltageLineChart.getDescription().setEnabled(false);
        voltageLineChart.setViewPortOffsets(35, 30, 30, 6);
        //禁止双击
        voltageLineChart.setDoubleTapToZoomEnabled(false);
        voltageLineChart.setScaleEnabled(false);

        voltageLineChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));

        Legend lineChartLegend = voltageLineChart.getLegend();
        lineChartLegend.setEnabled(false);

        XAxis xAxis = voltageLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 5f, 0f);
        //设置轴数据
        xAxis.setValueFormatter(new MyXAxisValueFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxis.setTextSize(18);

        YAxis axisLeft = voltageLineChart.getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeft.setEnabled(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setTextSize(18);
        axisLeft.setAxisMinimum(0);
        axisLeft.setAxisMaximum(500);
        YAxis axisRight = voltageLineChart.getAxisRight();
        axisRight.setEnabled(false);


        /*
        ===================================================================
         */
        electrcityLineChart.getDescription().setEnabled(false);
        electrcityLineChart.setNoDataText("数据加载中");
        electrcityLineChart.setNoDataTextColor(getResources().getColor(R.color.color666));
        electrcityLineChart.getDescription().setEnabled(false);
        electrcityLineChart.setViewPortOffsets(35, 10, 30, 30);
        //禁止双击 和禁止缩放
        electrcityLineChart.setDoubleTapToZoomEnabled(false);
        electrcityLineChart.setScaleEnabled(false);
        Legend lineChartLegend1 = electrcityLineChart.getLegend();
        lineChartLegend1.setEnabled(false);

        XAxis xAxis1 = electrcityLineChart.getXAxis();
        xAxis1.setValueFormatter(new MyXAxisValueFormatter());
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        //绘制网格
        xAxis1.enableGridDashedLine(10f, 5f, 0f);
        xAxis1.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxis1.setTextSize(18);

        YAxis axisLeft1 = electrcityLineChart.getAxisLeft();
        axisLeft1.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeft1.setEnabled(true);
        axisLeft1.setTextSize(18);
        axisLeft1.setDrawGridLines(false);
        axisLeft1.setAxisMaximum(240);
        axisLeft1.setAxisMinimum(0);
        YAxis axisRight1 = electrcityLineChart.getAxisRight();
        axisRight1.setEnabled(false);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void lineDataAdd(String ev) {
        if (ev.equals("add")) {
            LineData data = voltageLineChart.getData();

            if (data != null) {

                ILineDataSet set = data.getDataSetByIndex(0);
                // set.addEntry(...); // can be called as well

                if (set == null) {
                    set = createSet();
                    data.addDataSet(set);
                }

                data.addEntry(new Entry(data.getEntryCount(), ChargingPaileData.getInstance().getVoltages().get(data.getEntryCount())), 0);
                data.notifyDataChanged();
                voltageLineChart.notifyDataSetChanged();
                voltageLineChart.setVisibleXRangeMaximum(10);
                voltageLineChart.setVisibleXRangeMinimum(10);
                voltageLineChart.moveViewToX(data.getEntryCount());

            }
            LineData data1 = electrcityLineChart.getData();
            if (data1 != null) {

                ILineDataSet set = data1.getDataSetByIndex(0);
                // set.addEntry(...); // can be called as well

                if (set == null) {
                    set = createSet1();
                    data1.addDataSet(set);
                }
                data1.addEntry(new Entry(data1.getEntryCount(), ChargingPaileData.getInstance().getElectrcitys().get(data1.getEntryCount())), 0);
                data1.notifyDataChanged();
                electrcityLineChart.notifyDataSetChanged();
                electrcityLineChart.setVisibleXRangeMaximum(10);
                electrcityLineChart.setVisibleXRangeMinimum(10);
                electrcityLineChart.moveViewToX(data1.getEntryCount());


            }

        }

    }


    //数据读取存入本地的单例Model
    private static void convertToModel(ReceiveCurrentData receive, ChargingPaileData charge) {
        charge.setVoltageMeasure(receive.getCurrentStd().getStd_realMeasureVol() + "");
        charge.setVoltageSetting(receive.getCurrentStd().getStd_measureVol() + "");
        charge.setVoltageError((receive.getCurrentStd().getStd_realMeasureVol() - receive.getCurrentStd().getStd_measureVol()) + "");
        charge.setVoltageRipple(receive.getCurrentStd().getStd_realMeasureWaveVol() + "");
        charge.setElectrcityMeasure(receive.getCurrentStd().getStd_realMeasureAn() + "");
        charge.setElectrcitySetting(receive.getCurrentStd().getStd_measureAn() + "");
//        charge.setElectrcityError((receive.getCurrentStd().getStd_realMeasureAn() - receive.getCurrentStd().getStd_measureAn()) +"");
//        charge.setChargingPileId(receive.getStc().getStcCrm_id() + "");
        charge.setVersion("V" + receive.getStc().getStcChm_version());
        // TODO: 待定
        charge.setElectrcityRipple("--");
        charge.setAssistPower("--");
        charge.setEnvironmentTemperature("--");
        charge.setEnvironmentHumidity("--");
        //数据传输过程中 状态显示检测中

        charge.setMostVoltage(receive.getStc().getStcCml_maxVol() + "V");
        charge.setMostElectrcity(receive.getStc().getStcCml_maxAn() + "A");

        charge.setCHMState(receive.getStc().getChmHex() == null ? "--" : "已获取");
        charge.setCHMTestContent(receive.getStc().getChmHex());
        charge.setBHMState(receive.getStc().getBhmHex() == null ? "--" : "已获取");
        charge.setBHMTestContent(receive.getStc().getBhmHex());
        charge.setCRMState(receive.getStc().getCrmHex() == null ? "--" : "已获取");
        charge.setCRMTestContent(receive.getStc().getCrmHex());
        charge.setBCPState(receive.getStc().getBcpHex() == null ? "--" : "已获取");
        charge.setBCPTestContent(receive.getStc().getBcpHex());
        charge.setCTSState(receive.getStc().getCtsHex() == null ? "--" : "已获取");
        charge.setCTSTestContent(receive.getStc().getCtsHex());
        charge.setCMLState(receive.getStc().getCmlHex() == null ? "--" : "已获取");
        charge.setCMLTestContent(receive.getStc().getCmlHex());
        charge.setBROState(receive.getStc().getBroHex() == null ? "--" : "已获取");
        charge.setBROTestContent(receive.getStc().getBroHex());
        charge.setCROState(receive.getStc().getCroHex() == null ? "--" : "已获取");
        charge.setCROTestContent(receive.getStc().getCroHex());
        charge.setBSTState(receive.getStc().getBstHex() == null ? "--" : "已获取");
        charge.setBSTTestContent(receive.getStc().getBstHex());
        charge.setCSTState(receive.getStc().getCstHex() == null ? "--" : "已获取");
        charge.setCSTTestContent(receive.getStc().getCstHex());
        charge.setCSDState(receive.getStc().getCsdHex() == null ? "--" : "已获取");
        charge.setCSDTestContent(receive.getStc().getCsdHex());
        charge.setBSDState(receive.getStc().getBsdHex() == null ? "--" : "已获取");
        charge.setBSDTestContent(receive.getStc().getBsdHex());

    }

    //子线程读取单片机发送过来的数据
    private void readUsbData() {
        ChargingPaileData.getInstance().setTestDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //充电桩开始检测时间
        ChargingPaileData.getInstance().setStartTestTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                ReceiveCurrentData receiveData = new ReceiveCurrentData();
                while (isRead) {
                    byte[] receiveytes = new byte[16];
                    int readCount = mDeviceConnection.bulkTransfer(usbEpIn, receiveytes, receiveytes.length, 10000);
                    DecodeUtil.decode(receiveytes, receiveData);
                    //todo 1.转换为显示的model
                    convertToModel(receiveData, ChargingPaileData.getInstance());
                    if (isReceiveMsg&&receiveData.getCurrentStd().getStd_realMeasureVol()>0) {
                        ChargingPaileData.getInstance().getVoltages().add((float) receiveData.getCurrentStd().getStd_realMeasureVol());
                        ChargingPaileData.getInstance().getElectrcitys().add((float) receiveData.getCurrentStd().getStd_realMeasureAn());
                        ChargingPaileData.getInstance().getTimes().add(new SimpleDateFormat("HH:mm:ss.S").format(new Date()));
                        EventBus.getDefault().post("add");
                    }
                }
                //结束时间
                ChargingPaileData.getInstance().setEndCharg(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //充电结束显示检测报告生成中
                ChargingPaileData.getInstance().setTestState("检测报告生成中");

                ChargingPaileData.getInstance().setTestTime((int) ((System.currentTimeMillis() - startTiem) / 1000) + "S");
                //todo 2.将显示的model序列化成json
                //释放Usb连接
                mUsbDevice = null;
                mInterface = null;
                mDeviceConnection.close();
                mDeviceConnection = null;
//                finish();//检测结束关闭用户登录页面
            }
        }).start();

        //释放USB 连接

    }

    private void initUsbData() {
        //充电桩设备id：1155
        // 获取USB设备
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        //获取到设备列表
        deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

        while (deviceIterator.hasNext()) {
            UsbDevice usbDevice = deviceIterator.next();

            // TODO: 2018/1/15   设备的vendorId 可能需要修改 -- 232
            if (usbDevice.getVendorId() == 232) {
                mUsbDevice = usbDevice;
                break;
            }
        }

        if (mUsbDevice != null) {
            //权限申请
            mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
            manager.requestPermission(mUsbDevice, mPermissionIntent);
            //获取设备接口
            for (int i = 0; i < mUsbDevice.getInterfaceCount(); i++) {
                // 一般来说一个设备都是一个接口，你可以通过getInterfaceCount()查看接口的个数
                // 这个接口上有两个端点，分别对应OUT 和 IN
                mInterface = mUsbDevice.getInterface(i);
                break;
            }
            //用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
            if (mInterface.getEndpoint(1) != null) {
                usbEpOut = mInterface.getEndpoint(1);
            }
            if (mInterface.getEndpoint(0) != null) {
                usbEpIn = mInterface.getEndpoint(0);
            }
            if (mInterface != null) {
                // 判断是否有权限
                if (manager.hasPermission(mUsbDevice)) {
                    // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
                    mDeviceConnection = manager.openDevice(mUsbDevice);
                    if (mDeviceConnection == null) {
                        Log.i("connection", "null");
                        return;
                    }
                    if (mDeviceConnection.claimInterface(mInterface, true)) {
                        Log.i("tag", "找到设备接口 ");
                    } else {
                        mDeviceConnection.close();
                    }
                } else {
                    Log.i("tag", "没有权限 ");

                }
            } else {
                Log.i("tag", "没有找到设备接口 ");
                Toast.makeText(this, "设备未连接，请连接后尝试", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "未找到对应设备,请重新尝试后连接", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        super.initData();
//        ArrayList<Entry> yValue1 = new ArrayList<>();
//        LineDataSet set1 = new LineDataSet(yValue1,"DataSet 1");
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(ColorTemplate.getHoloBlue());
//        set1.setCircleColor(Color.WHITE);
//        set1.setLineWidth(2f);
//        set1.setCircleRadius(3f);
//        set1.setFillAlpha(65);
//        set1.setFillColor(ColorTemplate.getHoloBlue());
//        set1.setHighLightColor(Color.rgb(244, 117, 117));
//        set1.setDrawCircleHole(false);
        LineData data = new LineData();
        data.setValueTextColor(Color.RED);
        voltageLineChart.setData(data);

        LineData data1 = new LineData();
        electrcityLineChart.setData(data1);

    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(getResources().getColor(R.color.ThemeColor));
        set.setColor(getResources().getColor(R.color.ThemeColor));
        set.setLineWidth(6f);
        set.setCircleRadius(7f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(getResources().getColor(R.color.ThemeColor));
        set.setValueTextColor(getResources().getColor(R.color.ThemeColor));
        set.setValueTextSize(16);
        set.setDrawCircles(false);
        set.setDrawValues(false);
        return set;
    }

    private LineDataSet createSet1() {

        LineDataSet set = new LineDataSet(null, "");
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(getResources().getColor(R.color.colorOrange));
        set.setLineWidth(6f);
        set.setColor(getResources().getColor(R.color.colorOrange));
        set.setCircleRadius(7f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(getResources().getColor(R.color.colorOrange));
        set.setValueTextColor(getResources().getColor(R.color.colorOrange));
        set.setValueTextSize(16);
        return set;
    }

    @Override
    protected void initListner() {
        super.initListner();
        back.setOnClickListener(this);
        start.setOnClickListener(this);
        stopMsg.setOnClickListener(this);
        electrcityLineChart.setOnChartGestureListener(new CoupleChartGestureListener(electrcityLineChart, new Chart[]{voltageLineChart}));
        voltageLineChart.setOnChartGestureListener(new CoupleChartGestureListener(voltageLineChart, new Chart[]{electrcityLineChart}));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
//                ChargingPaileData.getInstance().setEnd(false);
                break;
            case R.id.oneKey_test_start:
                if (mUsbDevice != null && mUsbDevice.getVendorId() == 232) {
                    //标识 开始 接受数据 渲染页面
                    isReceiveMsg = true;
                    //用户点击启动按钮显示 状态检测中
                    ChargingPaileData.getInstance().setTestState("检测中");

                    //获取正确的USB 对象 进入读取操作
                    byte[] send = new byte[1];
                    send[0] = 0x10;
                    int sendCount = mDeviceConnection.bulkTransfer(usbEpOut, send, send.length, 5000);
                    //配置时间
                    ChargingPaileData.getInstance().setConfigTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    ChargingPaileData.getInstance().setChargingState("等待检测");
                    //清空历史数据
                    ChargingPaileData.getInstance().getVoltages().clear();
                    ChargingPaileData.getInstance().getElectrcitys().clear();
                    ChargingPaileData.getInstance().getTimes().clear();
                    //时间记录 记录第一次时间
                    startTiem = System.currentTimeMillis();
                    //点击开始 停止按钮使能开始
                    stopMsg.setClickable(true);
                    stopMsg.setBackgroundResource(R.drawable.one_key_test_stop_background);
                    //开始按钮 灰化 不可点击

                    start.setClickable(false);
                    start.setBackgroundResource(R.drawable.one_key_test_start_background_no);
                } else {
                    //未获得单片机USB对象，则先获取单片机USB 接口再进行读取数据
                    initUsbData();
                    if (mUsbDevice != null && mUsbDevice.getVendorId() == 232) {

                    }
                }

                break;
            case R.id.oneKey_test_stop:
                //点击停止 发送停止报文
                if (mDeviceConnection != null) {
                    byte[] send = new byte[1];
                    send[0] = 0x20;
                    int sendCount = mDeviceConnection.bulkTransfer(usbEpOut, send, send.length, 5000);
                    stopMsg.setClickable(true);
                    stopMsg.setBackgroundResource(R.drawable.one_key_test_start_background_no);

                }
                break;
        }
    }
}
