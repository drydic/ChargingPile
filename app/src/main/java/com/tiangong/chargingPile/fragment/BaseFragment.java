package com.tiangong.chargingPile.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/9/25.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mAtivity ;
    protected View mRootView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAtivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        init();
        initCondition();
        initView();
        initData();
        initAdapt();
        initListner();
        return mRootView;
    }

    protected  void init(){

    }

    protected void initCondition() {

    }

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initAdapt() {

    }


    protected void initListner() {

    }

    protected abstract int getLayoutId();
}
