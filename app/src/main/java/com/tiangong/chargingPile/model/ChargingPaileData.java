package com.tiangong.chargingPile.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.util.Log;

import com.tiangong.chargingPile.BR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */

public  class  ChargingPaileData extends BaseObservable {

    private static ChargingPaileData instance=null;
    private ChargingPaileData(){

    }
    public static ChargingPaileData getInstance(){
        if(instance==null){
            synchronized(ChargingPaileData.class){
                if(instance==null){
                    instance=new ChargingPaileData();
                }
            }
        }
        return instance;
    }



    @Bindable
    public String getChargingState() {
        return chargingState;
    }

    public void setChargingState(String chargingState) {
        this.chargingState = chargingState;
        notifyPropertyChanged(BR.chargingState);
    }

    private Handler  handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private String chargingPileId; //充电桩ID
    private String chargingLinkTime ;//充电桩插入时间
    private String startTestTime;  //开始检测时间
    private String handTime ; //握手时间
    private String configTime; //配置时间
    private String chargTime; // 充电阶段
    private String endCharg; //结束阶段
    private String stopTime ; //检测结束
    private String voltageMeasure="-" ;  // 电压测量值
    private String voltageSetting="-";  //电压设定值
    private String voltageError="-" ;    //电压误差
    private String voltageRipple;  //电压波纹
    private boolean isStop;
    private boolean canClick;
    private boolean isFirstAA = true;

    public boolean isFirstAA() {
        return isFirstAA;
    }

    public void setFirstAA(boolean firstAA) {
        isFirstAA = firstAA;
    }

    @Bindable
    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
        notifyPropertyChanged(BR.canClick);
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
        if (stop&&handler!=null){
            handler.sendEmptyMessage(2);
        }
    }

    private String employeeID ;//员工ID

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    @Bindable
    public String getChargingPileId() {
        return chargingPileId;
    }

    public void setChargingPileId(String chargingPileId) {
        this.chargingPileId = chargingPileId;
        notifyPropertyChanged(BR.chargingPileId);
    }

    @Bindable
    public String getChargingLinkTime() {
        return chargingLinkTime;
    }

    public void setChargingLinkTime(String chargingLinkTime) {
        this.chargingLinkTime = chargingLinkTime;
        notifyPropertyChanged(BR.chargingLinkTime);
    }


    @Bindable
    public String getStartTestTime() {
        return startTestTime;
    }

    public void setStartTestTime(String startTestTime) {
        this.startTestTime = startTestTime;
        notifyPropertyChanged(BR.startTestTime);
    }
    @Bindable
    public String getHandTime() {
        return handTime;
    }

    public void setHandTime(String handTime) {
        this.handTime = handTime;
        notifyPropertyChanged(BR.handTime);
    }

    @Bindable
    public String getConfigTime() {
        return configTime;

    }

    public void setConfigTime(String configTime) {
        this.configTime = configTime;
        notifyPropertyChanged(BR.configTime);
    }

    @Bindable
    public String getChargTime() {
        return chargTime;
    }

    public void setChargTime(String chargTime) {
        this.chargTime = chargTime;
        notifyPropertyChanged(BR.chargTime);
    }

    @Bindable
    public String getEndCharg() {
        return endCharg;
    }

    public void setEndCharg(String endCharg) {
        this.endCharg = endCharg;
        notifyPropertyChanged(BR.endCharg);
    }

    @Bindable
    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
        notifyPropertyChanged(BR.stopTime);
    }

    private String electrcityMeasure="-" ; //电流测量值
    private String electrcitySetting="-" ; //电流设定值
    private String electrcityError ="-";    //电流误差
    private String electrcityRipple;   //电流波纹
    private String testTime ;        //测试时间
    private String testDate ;       //测试日期
    private String version ;         //通用版本
    private String assistPower ;      //辅助电源
    private String environmentTemperature ;  //环境温度
    private String environmentHumidity ;  //环境湿度
    private String chargingState = "等待检测"; //充电状态
    private String testState = "充电枪未插入" ;        //检测状态
    private List<Float> voltages = new ArrayList<>(); // 电压波形图集合
    private List<Float> electrcitys = new ArrayList<>(); //电流波形图集合
    private List<String> times = new ArrayList<>();//存储电压电流对应时间点


    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    private String mostVoltage ;  //最大电压
    private String mostElectrcity ; //最大电流
    private String CHMState ;       //CHM 状态
    private String CHMTestContent  ;  //CHM 检测内容
    private String BHMState ;
    private String BHMTestContent ;
    private String CRMState ;
    private String CRMTestContent ;
    private String BCPState ;
    private String BCPTestContent ;
    private String CTSState ;
    private String CTSTestContent ;
    private String CMLState ;
    private String CMLTestContent ;
    private String BROState ;
    private String BROTestContent;
    private String CROState ;
    private String CROTestContent ;
    private String BCLState ;
    private String BCLTestContent ;
    private String BSTState ;
    private String BSTTestContent ;
    private String CSTState ;
    private String CSTTestContent ;
    private String CSDState ;
    private String CSDTestContent ;
    private String BSDState ;
    private String BSDTestContent;

    @Bindable
    public String getCHMState() {
        return CHMState;
    }

    public void setCHMState(String CHMState) {
        this.CHMState = CHMState;
        notifyPropertyChanged(BR.cHMState);
    }

    @Bindable
    public String getCHMTestContent() {
        return CHMTestContent;
    }

    public void setCHMTestContent(String CHMTestContent) {
        this.CHMTestContent = CHMTestContent;
        notifyPropertyChanged(BR.cHMTestContent);
    }

    @Bindable
    public String getBHMState() {
        return BHMState;
    }

    public void setBHMState(String BHMState) {
        this.BHMState = BHMState;
        notifyPropertyChanged(BR.bHMState);
    }

    @Bindable
    public String getBHMTestContent() {
        return BHMTestContent;
    }

    public void setBHMTestContent(String BHMTestContent) {
        this.BHMTestContent = BHMTestContent;
        notifyPropertyChanged(BR.bHMTestContent);
    }

    @Bindable
    public String getCRMState() {
        return CRMState;
    }

    public void setCRMState(String CRMState) {
        this.CRMState = CRMState;
        notifyPropertyChanged(BR.cRMState);
    }

    @Bindable
    public String getCRMTestContent() {
        return CRMTestContent;
    }

    public void setCRMTestContent(String CRMTestContent) {
        this.CRMTestContent = CRMTestContent;
        notifyPropertyChanged(BR.cRMTestContent);
    }

    @Bindable
    public String getBCPState() {
        return BCPState;
    }

    public void setBCPState(String BCPState) {
        this.BCPState = BCPState;
        notifyPropertyChanged(BR.bCPState);
    }

    @Bindable
    public String getBCPTestContent() {
        return BCPTestContent;

    }

    public void setBCPTestContent(String BCPTestContent) {
        this.BCPTestContent = BCPTestContent;
        notifyPropertyChanged(BR.bCPTestContent);
    }

    @Bindable
    public String getCTSState() {
        return CTSState;
    }

    public void setCTSState(String CTSState) {
        this.CTSState = CTSState;
        notifyPropertyChanged(BR.cTSState);
    }

    @Bindable
    public String getCTSTestContent() {
        return CTSTestContent;
    }

    public void setCTSTestContent(String CTSTestContent) {
        this.CTSTestContent = CTSTestContent;
        notifyPropertyChanged(BR.cTSTestContent);
    }

    @Bindable
    public String getCMLState() {
        return CMLState;
    }

    public void setCMLState(String CMLState) {
        this.CMLState = CMLState;
        notifyPropertyChanged(BR.cMLState);
    }

    @Bindable
    public String getCMLTestContent() {
        return CMLTestContent;
    }

    public void setCMLTestContent(String CMLTestContent) {

        this.CMLTestContent = CMLTestContent;
        notifyPropertyChanged(BR.cMLTestContent);
    }

    @Bindable
    public String getBROState() {
        return BROState;
    }

    public void setBROState(String BROState) {
        this.BROState = BROState;
        notifyPropertyChanged(BR.bROState);
    }

    @Bindable
    public String getBROTestContent() {
        return BROTestContent;
    }

    public void setBROTestContent(String BROTestContent) {
        this.BROTestContent = BROTestContent;
        notifyPropertyChanged(BR.bROTestContent);
    }

    @Bindable
    public String getCROState() {
        return CROState;
    }

    public void setCROState(String CROState) {
        this.CROState = CROState;
        notifyPropertyChanged(BR.cROState);
    }

    @Bindable
    public String getCROTestContent() {
        return CROTestContent;
    }

    public void setCROTestContent(String CROTestContent) {
        this.CROTestContent = CROTestContent;
        notifyPropertyChanged(BR.cROTestContent);
    }

    @Bindable
    public String getBCLState() {
        return BCLState;
    }

    public void setBCLState(String BCLState) {
        this.BCLState = BCLState;
        notifyPropertyChanged(BR.bCLState);
    }

    @Bindable
    public String getBCLTestContent() {
        return BCLTestContent;
    }

    public void setBCLTestContent(String BCLTestContent) {
        this.BCLTestContent = BCLTestContent;
        notifyPropertyChanged(BR.bCLTestContent);
    }

    @Bindable
    public String getBSTState() {
        return BSTState;
    }

    public void setBSTState(String BSTState) {
        this.BSTState = BSTState;
        notifyPropertyChanged(BR.bSTState);
    }

    @Bindable
    public String getBSTTestContent() {
        return BSTTestContent;
    }

    public void setBSTTestContent(String BSTTestContent) {
        this.BSTTestContent = BSTTestContent;
        notifyPropertyChanged(BR.bSTTestContent);
    }

    @Bindable
    public String getCSTState() {
        return CSTState;
    }

    public void setCSTState(String CSTState) {
        this.CSTState = CSTState;
        notifyPropertyChanged(BR.cSTState);
    }

    @Bindable
    public String getCSTTestContent() {
        return CSTTestContent;
    }

    public void setCSTTestContent(String CSTTestContent) {
        this.CSTTestContent = CSTTestContent;
        notifyPropertyChanged(BR.cSTTestContent);
    }

    @Bindable
    public String getCSDState() {
        return CSDState;
    }

    public void setCSDState(String CSDState) {
        this.CSDState = CSDState;
        notifyPropertyChanged(BR.cSTTestContent);
    }

    @Bindable
    public String getCSDTestContent() {
        return CSDTestContent;
    }

    public void setCSDTestContent(String CSDTestContent) {
        this.CSDTestContent = CSDTestContent;
        notifyPropertyChanged(BR.cSTTestContent);
    }

    @Bindable
    public String getBSDState() {
        return BSDState;
    }

    public void setBSDState(String BSDState) {
        this.BSDState = BSDState;
        notifyPropertyChanged(BR.bSDState);
    }

    @Bindable
    public String getBSDTestContent() {
        return BSDTestContent;
    }

    public void setBSDTestContent(String BSDTestContent) {
        this.BSDTestContent = BSDTestContent;
        notifyPropertyChanged(BR.bSDTestContent);
    }

    @Bindable
    public String getMostVoltage() {
        return mostVoltage;
    }

    public void setMostVoltage(String mostVoltage) {
        this.mostVoltage = mostVoltage;
        notifyPropertyChanged(BR.mostVoltage);
    }

    @Bindable
    public String getMostElectrcity() {
        return mostElectrcity;
    }

    public void setMostElectrcity(String mostElectrcity) {
        this.mostElectrcity = mostElectrcity;
        notifyPropertyChanged(BR.mostElectrcity);
    }

    @Bindable
    public String getElectrcityMeasure() {
        return electrcityMeasure;
    }

    public void setElectrcityMeasure(String electrcityMeasure) {
        BigDecimal bigDecimal = new BigDecimal(electrcityMeasure);
        double v = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.electrcityMeasure = v+"";
        notifyPropertyChanged(BR.electrcityMeasure);
        setElectrcityError("");
    }

    @Bindable
    public String getElectrcitySetting() {
        return electrcitySetting;
    }

    public void setElectrcitySetting(String electrcitySetting) {
        this.electrcitySetting = electrcitySetting;
        notifyPropertyChanged(BR.electrcitySetting);
        setElectrcityError("");
    }

    @Bindable
    public String getElectrcityError() {
        return electrcityError;
    }

    public void setElectrcityError(String electrcityError) {
        if (!electrcityMeasure.equals("-")&&!electrcitySetting.equals("-")&&Double.parseDouble(electrcityMeasure)!=0&&Double.parseDouble(electrcitySetting)!=0){
            BigDecimal bigDecimal = new BigDecimal(electrcityMeasure);
            BigDecimal divide = bigDecimal.divide(new BigDecimal(electrcitySetting), 2, BigDecimal.ROUND_HALF_UP);
            this.electrcityError = (int)(Double.parseDouble(divide.toString())*100)+"";
            notifyPropertyChanged(BR.electrcityError);
        }
    }

    @Bindable
    public String getElectrcityRipple() {
        return electrcityRipple;
    }

    public void setElectrcityRipple(String electrcityRipple) {
        this.electrcityRipple = electrcityRipple;
        notifyPropertyChanged(BR.electrcityRipple);
    }

    @Bindable
    public List<Float> getVoltages() {
        return voltages;
    }

    public void setVoltages(List<Float> voltages) {
        this.voltages = voltages;
        notifyPropertyChanged(BR.voltages);
    }

    @Bindable
    public List<Float> getElectrcitys() {
        return electrcitys;
    }

    public void setElectrcitys(List<Float> electrcitys) {
        this.electrcitys = electrcitys;
        notifyPropertyChanged(BR.electrcitys);
    }

    @Bindable
    public String getVoltageMeasure() {
        return voltageMeasure;
    }

    public void setVoltageMeasure(String voltageMeasure) {
        BigDecimal bigDecimal = new BigDecimal(voltageMeasure);
        double v = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        this.voltageMeasure = v+"";
        notifyPropertyChanged(BR.voltageMeasure);
        setVoltageError("");
    }

    @Bindable
    public String getVoltageSetting() {
        return voltageSetting;
    }

    public void setVoltageSetting(String voltageSetting) {
        this.voltageSetting = voltageSetting;
        notifyPropertyChanged(BR.voltageSetting);
        setVoltageError("");
    }

    @Bindable
    public String getVoltageError() {
        return voltageError;
    }

    public void setVoltageError(String voltageError) {
        if (!voltageMeasure.equals("-")&&!voltageSetting.equals("-")&&Double.parseDouble(voltageMeasure)!=0&&Double.parseDouble(voltageSetting)!=0){
            BigDecimal bigDecimal = new BigDecimal(voltageMeasure);
            BigDecimal divide = bigDecimal.divide(new BigDecimal(voltageSetting), 2, BigDecimal.ROUND_HALF_UP);
            this.voltageError = (int)(Double.parseDouble(divide.toString())*100)+"";
            notifyPropertyChanged(BR.voltageError);
        }

    }

    @Bindable
    public String getVoltageRipple() {
        return voltageRipple;
    }

    public void setVoltageRipple(String voltageRipple) {
        this.voltageRipple = voltageRipple;
        notifyPropertyChanged(BR.voltageRipple);
    }

    @Bindable
    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
        notifyPropertyChanged(BR.testTime);
    }

    @Bindable
    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
        notifyPropertyChanged(BR.testDate);
    }

    @Bindable
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
        notifyPropertyChanged(BR.version);
    }

    @Bindable
    public String getAssistPower() {
        return assistPower;
    }

    public void setAssistPower(String assistPower) {
        this.assistPower = assistPower;
        notifyPropertyChanged(BR.assistPower);
    }

    @Bindable
    public String getEnvironmentTemperature() {
        return environmentTemperature;
    }

    public void setEnvironmentTemperature(String environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
        notifyPropertyChanged(BR.environmentTemperature);
    }
    @Bindable
    public String getEnvironmentHumidity() {
        return environmentHumidity;
    }

    public void setEnvironmentHumidity(String environmentHumidity) {
        this.environmentHumidity = environmentHumidity;
        notifyPropertyChanged(BR.environmentHumidity);
    }

    @Bindable
    public String getTestState() {
        return testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
        notifyPropertyChanged(BR.testState);
    }

    public void setOriginalData(){
//        setChargingPileId("");
//        setChargingLinkTime("");
//        setStartTestTime("");
//        setHandTime("");
//        setConfigTime("");
//        setChargTime("");
//        setEndCharg("");
//        setStopTime("");
//        setVoltageError("");
//        setVoltageRipple("");
//        setAaCount(0);
//        setEmployeeID("");
//        setElectrcityError("");
//        setTestTime("");
//        setTestDate("");
//        setVersion("");
//        setAssistPower("");
//        setEnvironmentTemperature("");
//        setEnvironmentHumidity("");
//        setChargingState("");
//        setTestState("");
//        setVoltages(new ArrayList<Float>());
//        setElectrcitys(new ArrayList<Float>());
//        setTimes(new ArrayList<String>());
//        setEnd(false);
        instance = null;
    }
}
