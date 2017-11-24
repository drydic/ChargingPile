package com.tiangong.chargingPile.jit.util.protocalModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/10/27.
 * 当前反馈的最新数据汇总
 */
public class ReceiveCurrentData {


    private ReceiveSta sta=new ReceiveSta();
    private ReceiveStc stc=new ReceiveStc();
    private List<ReceiveStd> stdList=new ArrayList<>();
    private ReceiveStd currentStd = new ReceiveStd();

    public ReceiveStd getCurrentStd() {
        return currentStd;
    }

    public void setCurrentStd(ReceiveStd currentStd) {
        this.currentStd = currentStd;
    }

    public ReceiveSta getSta() {
        return sta;
    }

    public void setSta(ReceiveSta sta) {
        this.sta = sta;
    }

    public ReceiveStc getStc() {
        return stc;
    }

    public void setStc(ReceiveStc stc) {
        this.stc = stc;
    }

    public List<ReceiveStd> getStdList() {
        return stdList;
    }

    public void setStdList(List<ReceiveStd> stdList) {
        this.stdList = stdList;
    }
}
