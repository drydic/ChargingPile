package com.tiangong.chargingPile.adapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiangong.chargingPile.R;
import com.tiangong.chargingPile.dto.DataManagerDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25.
 */

public class DataManagerAdapt extends BaseAdapter {
    private Context context;
    private List<DataManagerDTO> data;

    public DataManagerAdapt(Context context, List<DataManagerDTO> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
        DataManagerHolder holder = null;
        if (convertView==null){
            holder = new DataManagerHolder();
            convertView = View.inflate(context, R.layout.item_data_manager, null);
            holder.chargingPileID = (TextView) convertView.findViewById(R.id.name);
            holder.employeeId = (TextView) convertView.findViewById(R.id.employeeID);
            holder.tiem = (TextView) convertView.findViewById(R.id.time);
            holder.remark = (TextView) convertView.findViewById(R.id.result);
            holder.result = (TextView) convertView.findViewById(R.id.result);
            convertView.setTag(holder);
        }else {
            holder = (DataManagerHolder) convertView.getTag();
        }
        holder.chargingPileID.setText(data.get(position).getName());
        holder.employeeId.setText(data.get(position).getID());
        holder.tiem.setText(data.get(position).getTime());
        holder.result.setText(data.get(position).getResult());
        holder.remark.setText(data.get(position).getRemark());
        return convertView;
    }

    private class DataManagerHolder{
        private TextView chargingPileID;
        private TextView employeeId;
        private TextView tiem;
        private TextView result;
        private TextView remark;
    }
}
