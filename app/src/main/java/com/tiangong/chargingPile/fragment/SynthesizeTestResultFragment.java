package com.tiangong.chargingPile.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.databinding.FragmentSynthesizeTestResultBinding;
import com.tiangong.chargingPile.model.ChargingPaileData;

/**
 * Created by Administrator on 2017/10/17.
 */

public class SynthesizeTestResultFragment extends Fragment {
    private ChargingPaileData data = ChargingPaileData.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSynthesizeTestResultBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_synthesize_test_result, container, false);
        dataBinding.setChargingPileMode(data);
        return dataBinding.getRoot();
    }
}
