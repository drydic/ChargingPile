package com.tiangong.chargingPile.jit.util.protocalModel;

/**
 * Created by mac on 2017/10/27.
 * 板子在充电时时时上传的数据
 */
public class ReceiveStd {

    private double std_measureVol;//当前测量电压 (0.1v)
    private double std_measureAn;//当前测量电流(0.1A / -400A)

    private short std_chargeTime;//累计充电时间 (min)

    private double std_realMeasureVol;//实际测量电压 (0.1v)
    private double std_realMeasureAn;//实际测量电流(0.1A / -400A)

    private double std_realMeasureWaveVol;//实际测量文波电压(0.1A / -400A)

    private double std_chargeNeedVol;//充电需求电压


    public double getStd_measureVol() {
        return std_measureVol;
    }

    public void setStd_measureVol(double std_measureVol) {
        this.std_measureVol = std_measureVol;
    }

    public double getStd_measureAn() {
        return std_measureAn;
    }

    public void setStd_measureAn(double std_measureAn) {
        this.std_measureAn = std_measureAn;
    }

    public short getStd_chargeTime() {
        return std_chargeTime;
    }

    public void setStd_chargeTime(short std_chargeTime) {
        this.std_chargeTime = std_chargeTime;
    }

    public double getStd_realMeasureVol() {
        return std_realMeasureVol;
    }

    public void setStd_realMeasureVol(double std_realMeasureVol) {
        this.std_realMeasureVol = std_realMeasureVol;
    }

    public double getStd_realMeasureAn() {
        return std_realMeasureAn;
    }

    public void setStd_realMeasureAn(double std_realMeasureAn) {
        this.std_realMeasureAn = std_realMeasureAn;
    }

    public double getStd_realMeasureWaveVol() {
        return std_realMeasureWaveVol;
    }

    public void setStd_realMeasureWaveVol(double std_realMeasureWaveVol) {
        this.std_realMeasureWaveVol = std_realMeasureWaveVol;
    }

    public double getStd_chargeNeedVol() {
        return std_chargeNeedVol;
    }

    public void setStd_chargeNeedVol(double std_chargeNeedVol) {
        this.std_chargeNeedVol = std_chargeNeedVol;
    }
}
