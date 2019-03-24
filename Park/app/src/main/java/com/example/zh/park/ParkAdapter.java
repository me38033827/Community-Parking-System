package com.example.zh.park;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zh on 16/7/25.
 */
public class ParkAdapter extends ArrayAdapter<Park>{
    private int resourceId;

    public ParkAdapter(Context context, int resource, List<Park> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Park park=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView price=(TextView)view.findViewById(R.id.textView_Price);
        TextView remark=(TextView)view.findViewById(R.id.textView_remark);
        price.setText(park.getPrice());
        remark.setText(park.getRemark());
        return view;
    }
}
