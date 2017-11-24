package com.tiangong.chargingPile.valueformat;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.tiangong.chargingPile.model.ChargingPaileData;

/**
 * Created by Administrator on 2017/11/3.
 */

public class MyXAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return ChargingPaileData.getInstance().getTimes().get((int)value)+"";
    }
}
