package com.tiangong.chargingPile.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;


import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.fragment.CommunicationTestResultFragment;
import com.tiangong.chargingPile.fragment.DetailsTestResultFragment;
import com.tiangong.chargingPile.fragment.SynthesizeTestResultFragment;
import com.tiangong.chargingPile.model.ChargingPaileData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class OneKeyTestReportActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private String[] titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_key__test__report_);
        EventBus.getDefault().register(this);

        EventBus.getDefault().post("ExitLogin");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void baseMsg(String ev) {

    }
    @Override
    protected void initData() {
        super.initData();
        fragments = new ArrayList<>();
        fragments.add(new SynthesizeTestResultFragment());
        fragments.add(new DetailsTestResultFragment());
        fragments.add(new CommunicationTestResultFragment());
        titles = new String[]{"综合检测结果","详细检测结果","通讯报文检测"};
    }

    @Override
    protected void initView() {
        super.initView();
        back = (ImageView) findViewById(R.id.img_back);
        tabLayout = (TabLayout) findViewById(R.id.oneKey_test_report_tab);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.oneKey_test_report_vp);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initListner() {
        super.initListner();
        back.setOnClickListener(this);
    }

    @Override
    protected void initAdapt() {
        super.initAdapt();
        viewPager.setAdapter(new ViewPagerAdapt(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                // TODO: 2017/11/21  点击退出报告页面时 将单例下的数据 重置
                ChargingPaileData.getInstance().setOriginalData();
                break;
        }
    }

    private class ViewPagerAdapt extends FragmentPagerAdapter{

        public ViewPagerAdapt(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
