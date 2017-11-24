package com.tiangong.chargingPile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tiangong.chargingPile.R;

public class ParamaterSettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramater_setting);
    }

    @Override
    protected void initView() {
        super.initView();
        back = (ImageView) findViewById(R.id.img_back);
    }

    @Override
    protected void initListner() {
        super.initListner();
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
