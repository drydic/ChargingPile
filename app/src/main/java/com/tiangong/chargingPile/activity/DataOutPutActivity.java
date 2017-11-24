package com.tiangong.chargingPile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.adapt.DataOutPutAdapt;

public class DataOutPutActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_out_put);
    }

    @Override
    protected void initView() {
        super.initView();
        back = (ImageView) findViewById(R.id.img_back);
        listView = (ListView) findViewById(R.id.data_output_listview);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initAdapt() {
        super.initAdapt();

        listView.setAdapter(new DataOutPutAdapt(this));
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
