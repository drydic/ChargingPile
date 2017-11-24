package com.tiangong.chargingPile.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        initIntnet();
        initCondition();
        initView();
        initData();
        initAdapt();
        initListner();
    }

    protected void initCondition() {

    }

    protected  void initIntnet(){

    }

    protected  void initListner(){

    }

    protected  void initAdapt(){

    }

    protected void initView(){

    }

    protected void initData(){

    }



}
