package com.tiangong.chargingPile.jit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */

public class ChargingPaileData {
    private String voltageMeasure;  // 电压测量值
    private String voltageSetting;  //电压设定值
    private String voltageError;    //电压误差
    private String voltageRipple;  //电压波纹

    private String electrcityMeasure; //电流测量值
    private String electrcitySetting; //电流设定值
    private String electrcityError;    //电流误差
    private String electrcityRipple;   //电流波纹
    private String testTime;        //测试时间
    private String testDate ;       //测试日期
    private String version;         //通用版本
    private String assistPower;      //辅助电源
    private String environmentTemperature ;  //环境温度
    private String environmentHumidity ;  //环境湿度
    private String testState;        //检测状态
    private List<Float> voltages = new ArrayList<>(); // 电压波形图集合
    private List<Float> electrcitys = new ArrayList<>(); //电流波形图集合
    private String mostVoltage;  //最大电压
    private String mostElectrcity; //最大电流
    private String CHMState ;       //CHM 状态
    private String CHMTestContent ;  //CHM 检测内容
    private String BHMState;
    private String BHMTestContent;
    private String CRMState;
    private String CRMTestContent;
    private String BCPState;
    private String BCPTestContent;
    private String CTSState;
    private String CTSTestContent;
    private String CMLState;
    private String CMLTestContent;
    private String BROState;
    private String BROTestContent;
    private String CROState;
    private String CROTestContent;
    private String BCLState;
    private String BCLTestContent;
    private String BSTState;
    private String BSTTestContent;
    private String CSTState;
    private String CSTTestContent;
    private String CSDState;
    private String CSDTestContent;
    private String BSDState;
    private String BSDTestContent;

    public String getCHMState() {
        return CHMState;
    }

    public void setCHMState(String CHMState) {
        this.CHMState = CHMState;
    }

    public String getCHMTestContent() {
        return CHMTestContent;
    }

    public void setCHMTestContent(String CHMTestContent) {
        this.CHMTestContent = CHMTestContent;
    }

    public String getBHMState() {
        return BHMState;
    }

    public void setBHMState(String BHMState) {
        this.BHMState = BHMState;
    }

    public String getBHMTestContent() {
        return BHMTestContent;
    }

    public void setBHMTestContent(String BHMTestContent) {
        this.BHMTestContent = BHMTestContent;
    }

    public String getCRMState() {
        return CRMState;
    }

    public void setCRMState(String CRMState) {
        this.CRMState = CRMState;
    }

    public String getCRMTestContent() {
        return CRMTestContent;
    }

    public void setCRMTestContent(String CRMTestContent) {
        this.CRMTestContent = CRMTestContent;
    }

    public String getBCPState() {
        return BCPState;
    }

    public void setBCPState(String BCPState) {
        this.BCPState = BCPState;
    }

    public String getBCPTestContent() {
        return BCPTestContent;
    }

    public void setBCPTestContent(String BCPTestContent) {
        this.BCPTestContent = BCPTestContent;
    }

    public String getCTSState() {
        return CTSState;
    }

    public void setCTSState(String CTSState) {
        this.CTSState = CTSState;
    }

    public String getCTSTestContent() {
        return CTSTestContent;
    }

    public void setCTSTestContent(String CTSTestContent) {
        this.CTSTestContent = CTSTestContent;
    }

    public String getCMLState() {
        return CMLState;
    }

    public void setCMLState(String CMLState) {
        this.CMLState = CMLState;
    }

    public String getCMLTestContent() {
        return CMLTestContent;
    }

    public void setCMLTestContent(String CMLTestContent) {
        this.CMLTestContent = CMLTestContent;
    }

    public String getBROState() {
        return BROState;
    }

    public void setBROState(String BROState) {
        this.BROState = BROState;
    }

    public String getBROTestContent() {
        return BROTestContent;
    }

    public void setBROTestContent(String BROTestContent) {
        this.BROTestContent = BROTestContent;
    }

    public String getCROState() {
        return CROState;
    }

    public void setCROState(String CROState) {
        this.CROState = CROState;
    }

    public String getCROTestContent() {
        return CROTestContent;
    }

    public void setCROTestContent(String CROTestContent) {
        this.CROTestContent = CROTestContent;
    }

    public String getBCLState() {
        return BCLState;
    }

    public void setBCLState(String BCLState) {
        this.BCLState = BCLState;
    }

    public String getBCLTestContent() {
        return BCLTestContent;
    }

    public void setBCLTestContent(String BCLTestContent) {
        this.BCLTestContent = BCLTestContent;
    }

    public String getBSTState() {
        return BSTState;
    }

    public void setBSTState(String BSTState) {
        this.BSTState = BSTState;
    }

    public String getBSTTestContent() {
        return BSTTestContent;
    }

    public void setBSTTestContent(String BSTTestContent) {
        this.BSTTestContent = BSTTestContent;
    }

    public String getCSTState() {
        return CSTState;
    }

    public void setCSTState(String CSTState) {
        this.CSTState = CSTState;
    }

    public String getCSTTestContent() {
        return CSTTestContent;
    }

    public void setCSTTestContent(String CSTTestContent) {
        this.CSTTestContent = CSTTestContent;
    }

    public String getCSDState() {
        return CSDState;
    }

    public void setCSDState(String CSDState) {
        this.CSDState = CSDState;
    }

    public String getCSDTestContent() {
        return CSDTestContent;
    }

    public void setCSDTestContent(String CSDTestContent) {
        this.CSDTestContent = CSDTestContent;
    }

    public String getBSDState() {
        return BSDState;
    }

    public void setBSDState(String BSDState) {
        this.BSDState = BSDState;
    }

    public String getBSDTestContent() {
        return BSDTestContent;
    }

    public void setBSDTestContent(String BSDTestContent) {
        this.BSDTestContent = BSDTestContent;
    }

    public String getMostVoltage() {
        return mostVoltage;
    }

    public void setMostVoltage(String mostVoltage) {
        this.mostVoltage = mostVoltage;
    }

    public String getMostElectrcity() {
        return mostElectrcity;
    }

    public void setMostElectrcity(String mostElectrcity) {
        this.mostElectrcity = mostElectrcity;
    }

    public String getElectrcityMeasure() {
        return electrcityMeasure;
    }

    public void setElectrcityMeasure(String electrcityMeasure) {
        this.electrcityMeasure = electrcityMeasure;
    }

    public String getElectrcitySetting() {
        return electrcitySetting;
    }

    public void setElectrcitySetting(String electrcitySetting) {
        this.electrcitySetting = electrcitySetting;
    }

    public String getElectrcityError() {
        return electrcityError;
    }

    public void setElectrcityError(String electrcityError) {
        this.electrcityError = electrcityError;
    }

    public String getElectrcityRipple() {
        return electrcityRipple;
    }

    public void setElectrcityRipple(String electrcityRipple) {
        this.electrcityRipple = electrcityRipple;
    }

    public List<Float> getVoltages() {
        return voltages;
    }

    public void setVoltages(List<Float> voltages) {
        this.voltages = voltages;
    }

    public List<Float> getElectrcitys() {
        return electrcitys;
    }

    public void setElectrcitys(List<Float> electrcitys) {
        this.electrcitys = electrcitys;
    }

    public String getVoltageMeasure() {
        return voltageMeasure;
    }

    public void setVoltageMeasure(String voltageMeasure) {
        this.voltageMeasure = voltageMeasure;
    }

    public String getVoltageSetting() {
        return voltageSetting;
    }

    public void setVoltageSetting(String voltageSetting) {
        this.voltageSetting = voltageSetting;
    }

    public String getVoltageError() {
        return voltageError;
    }

    public void setVoltageError(String voltageError) {
        this.voltageError = voltageError;
    }

    public String getVoltageRipple() {
        return voltageRipple;
    }

    public void setVoltageRipple(String voltageRipple) {
        this.voltageRipple = voltageRipple;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAssistPower() {
        return assistPower;
    }

    public void setAssistPower(String assistPower) {
        this.assistPower = assistPower;
    }

    public String getEnvironmentTemperature() {
        return environmentTemperature;
    }

    public void setEnvironmentTemperature(String environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
    }

    public String getEnvironmentHumidity() {
        return environmentHumidity;
    }

    public void setEnvironmentHumidity(String environmentHumidity) {
        this.environmentHumidity = environmentHumidity;
    }

    public String getTestState() {
        return testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
    }
}
