package com.mapscloud.wangyongcan.getpackagemanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangyongcan on 2017/9/23.
 */

public class MyAdapter extends BaseAdapter {
    private List<PackageInfo> installedPackages;
    private Context context;
    private String packageName;

    public MyAdapter (Context context, List<PackageInfo> installedPackages, String packageName){
        this.installedPackages = installedPackages;
        this.context = context;
        this.packageName = packageName;

    }
    @Override
    public int getCount() {
        return installedPackages.size();
    }

    @Override
    public PackageInfo getItem(int position) {
        return installedPackages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView textView = null;
//        if (convertView == null) {
//            convertView =  new TextView(convertView.getContext());
//            textView.setText(getItem(position).packageName);
//        }
//
//        textView.setText(getItem(position).packageName);
//        return textView;


        TextView textView = new TextView(context);
        textView.setText(getItem(position).packageName);
        if (packageName.equals(getItem(position).packageName)) {
            textView.setTextColor(Color.RED);

        }


        return textView;
    }
}
