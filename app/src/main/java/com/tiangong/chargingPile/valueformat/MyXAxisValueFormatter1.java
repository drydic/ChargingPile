package com.tiangong.chargingPile.valueformat;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.tiangong.chargingPile.model.ChargingPaileData;

/**
 * Created by Administrator on 2017/11/3.
 */

public class MyXAxisValueFormatter1 implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String s = "";
        try {
            s = ChargingPaileData.getInstance().getTimes().get((int) value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
