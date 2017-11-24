package com.tiangong.chargingPile.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.databinding.FragmentCommunicationTestResultBinding;
import com.tiangong.chargingPile.model.ChargingPaileData;

/**
 * Created by Administrator on 2017/10/17.
 */

public class CommunicationTestResultFragment extends Fragment {
    private ChargingPaileData data = ChargingPaileData.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCommunicationTestResultBinding binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_communication_test_result,container,false);
        binding.setChargingPileMode(data);
        return binding.getRoot();
    }
}
