package com.tiangong.chargingPile.adapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tiangong.chargingPile.R;

/**
 * Created by Administrator on 2017/10/26.
 */

public class DataOutPutAdapt extends BaseAdapter {
    private Context context;

    public DataOutPutAdapt(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_data_output,null);
        return convertView;
    }
}
