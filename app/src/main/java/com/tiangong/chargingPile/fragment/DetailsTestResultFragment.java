package com.tiangong.chargingPile.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.databinding.FragmentDetailsTestResultBinding;
import com.tiangong.chargingPile.model.ChargingPaileData;
import com.tiangong.chargingPile.valueformat.MyXAxisValueFormatter;
import com.tiangong.chargingPile.valueformat.MyXAxisValueFormatter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public class DetailsTestResultFragment extends Fragment {
    private ChargingPaileData data = ChargingPaileData.getInstance();
    private FragmentDetailsTestResultBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_test_result,container,false);

        binding.setChargingPileMode(data);
        initView();

        return binding.getRoot();
    }

    private void initData() {

        LineData lineData = new LineData();
        LineDataSet set = createSet();
        lineData.addDataSet(set);
        binding.voltageLineChart.setData(lineData);
        binding.voltageLineChart.invalidate();
//        binding.voltageLineChart.notifyDataSetChanged();

        binding.voltageLineChart.setVisibleXRangeMaximum(6);
        binding.voltageLineChart.moveViewToX(set.getEntryCount()-10);


        LineData lineData1 = new LineData();
        LineDataSet set1 = createSet1();
        lineData1.addDataSet(set1);
        binding.electrcityLineChart.setData(lineData1);
        binding.electrcityLineChart.notifyDataSetChanged();
//        binding.electrcityLineChart.moveViewToX(set1.getEntryCount());
        Log.i("tag", "initData: =========>"+lineData1.getEntryCount()+"         "+ChargingPaileData.getInstance().getTimes().size());
        binding.electrcityLineChart.moveViewToX(lineData1.getEntryCount()-10);
        binding.electrcityLineChart.setVisibleXRangeMaximum(6);
    }

    private LineDataSet createSet1() {

        LineDataSet set = new LineDataSet(null, "");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(getResources().getColor(R.color.colorOrange));
        set.setLineWidth(4f);
        set.setColor(getResources().getColor(R.color.colorOrange));
        set.setCircleRadius(7f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(getResources().getColor(R.color.colorOrange));
        set.setValueTextColor(getResources().getColor(R.color.colorOrange));
        set.setValueTextSize(16);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        List<Entry> entries = new ArrayList<>();
        List<Float> electrcitys = ChargingPaileData.getInstance().getElectrcitys();
        for (int i = 0;i<electrcitys.size();i++){
            entries.add(new Entry(i,ChargingPaileData.getInstance().getElectrcitys().get(i)));
        }
        set.setValues(entries);
        return set;
    }
    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(getResources().getColor(R.color.ThemeColor));
        set.setColor(getResources().getColor(R.color.ThemeColor));
        set.setLineWidth(4f);
        set.setCircleRadius(7f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(getResources().getColor(R.color.ThemeColor));
        set.setValueTextColor(getResources().getColor(R.color.ThemeColor));
        set.setValueTextSize(16);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        List<Entry> entries = new ArrayList<>();
        List<Float> voltages = ChargingPaileData.getInstance().getVoltages();
        for (int i = 0;i<voltages.size();i++){
            entries.add(new Entry(i,ChargingPaileData.getInstance().getVoltages().get(i)));
        }
        set.setValues(entries);
        return set;
    }
    private void initView() {
        
        binding.voltageLineChart.getDescription().setEnabled(false);
        binding.voltageLineChart.setNoDataText("数据加载中");
        binding.voltageLineChart.setNoDataTextColor(getResources().getColor(R.color.color666));
        binding.voltageLineChart.getDescription().setEnabled(false);
        binding.voltageLineChart.setViewPortOffsets(50,30,30,30);


        binding.voltageLineChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));

        Legend lineChartLegend =binding.voltageLineChart.getLegend();
        lineChartLegend.setEnabled(false);

        XAxis xAxis = binding.voltageLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f,5f,0f);
        //设置轴数据
        xAxis.setValueFormatter(new MyXAxisValueFormatter1());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxis.setTextSize(18);

        YAxis axisLeft = binding.voltageLineChart.getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeft.setEnabled(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setTextSize(18);
        axisLeft.setAxisMinimum(0);
        axisLeft.setAxisMaximum(500);
        YAxis axisRight = binding.voltageLineChart.getAxisRight();
        axisRight.setEnabled(false);


        /*
        ===================================================================
         */

        binding.electrcityLineChart.getDescription().setEnabled(false);
        binding.electrcityLineChart.setNoDataText("数据加载中");
        binding.electrcityLineChart.setNoDataTextColor(getResources().getColor(R.color.color666));
        binding.electrcityLineChart.getDescription().setEnabled(false);
        binding.electrcityLineChart.setViewPortOffsets(50,30,30,30);
        Legend lineChartLegend1 =binding.electrcityLineChart.getLegend();
        lineChartLegend1.setEnabled(false);

        XAxis xAxis1 = binding.electrcityLineChart.getXAxis();
//        xAxis1.setValueFormatter(new MyXAxisValueFormatter1());
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        //绘制网格
        xAxis1.enableGridDashedLine(10f,5f,0f);
        xAxis1.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxis1.setTextSize(18);

        YAxis axisLeft1 = binding.electrcityLineChart.getAxisLeft();
        axisLeft1.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeft1.setEnabled(true);
        axisLeft1.setTextSize(18);
        axisLeft1.setDrawGridLines(false);
        axisLeft1.setAxisMaximum(240);
        axisLeft1.setAxisMinimum(0);
        YAxis axisRight1 = binding.electrcityLineChart.getAxisRight();
        axisRight1.setEnabled(false);
        initData();

    }


}
