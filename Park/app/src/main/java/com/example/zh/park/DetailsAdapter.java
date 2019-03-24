package com.example.zh.park;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zh on 16/8/1.
 */
public class DetailsAdapter extends ArrayAdapter<Park> {
    private int resourceId;

    public DetailsAdapter(Context context, int resource, List<Park> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Park park=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView courtname=(TextView)view.findViewById(R.id.textView_details_courtname);
        TextView price=(TextView)view.findViewById(R.id.textView_details_price);
        TextView remark=(TextView)view.findViewById(R.id.textView_details_remark);
        price.setText(park.getPrice());
        remark.setText(park.getRemark());
        courtname.setText(park.getCourtname());
        return view;
    }

}
