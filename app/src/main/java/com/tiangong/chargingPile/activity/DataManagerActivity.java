package com.tiangong.chargingPile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.adapt.DataManagerAdapt;
import com.tiangong.chargingPile.dto.DataManagerDTO;

import java.util.ArrayList;
import java.util.List;

public class DataManagerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private ListView listView;
    private List<DataManagerDTO> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_manager);
    }


    @Override
    protected void initView() {
        super.initView();
        back = (ImageView) findViewById(R.id.img_back);
        listView = (ListView) findViewById(R.id.data_manager_listview);
    }

    @Override
    protected void initData() {
        super.initData();
        data = new ArrayList<>();
        data.add(new DataManagerDTO("9527","1122","2017-08-31","通过","暂无备注"));
        data.add(new DataManagerDTO("9533","0024","2017-08-25","通过","设备正常"));
        data.add(new DataManagerDTO("9534","1789","2017-08-21","通过","暂无备注"));
        data.add(new DataManagerDTO("0321","5678","2017-08-15","通过","暂无备注"));
        data.add(new DataManagerDTO("0052","3690","2017-08-11","通过","暂无备注"));
        data.add(new DataManagerDTO("7732","0342","2017-08-07","通过","暂无备注"));
        data.add(new DataManagerDTO("1524","0745","2017-08-05","通过","暂无备注"));
        data.add(new DataManagerDTO("1167","5831","2017-07-27","通过","暂无备注"));
        data.add(new DataManagerDTO("4489","9943","2017-07-24","通过","暂无备注"));
        data.add(new DataManagerDTO("0945","5601","2017-07-21","通过","暂无备注"));


    }

    @Override
    protected void initAdapt() {
        super.initAdapt();
        listView.setAdapter(new DataManagerAdapt(this,data));
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
