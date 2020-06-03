package com.mapscloud.wangyongcan.getpackagemanager;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    String TAG = this.getClass().getName();
    private List<PackageInfo> installedPackages;
    private String str_packageName = "com.hwacreate.bdstatus";     //设置查询的应用包名

    @SuppressLint("HandlerLeak")
    Handler MyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplication(), "获取到了", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
//    class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//        }
//
//
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_getApp = (Button) findViewById(R.id.bt_getApp);
        final TextView tv_name = (TextView) findViewById(R.id.tv_name);
        Button bt_resetName  = (Button) findViewById(R.id.bt_resetName);
        Button bt_getAllPackageName = (Button) findViewById(R.id.bt_getAllPackageName);
        final ListView lv_showPackageName = (ListView) findViewById(R.id.lv_showPackageName);

        bt_getApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PackageInfo packageInfo = getPackageManager().getPackageInfo(str_packageName, 0);
//                    PackageInfo packageInfo = getPackageManager().getPackageInfo("com.mapscloud.wangyongcan.testgetpackagename",0);
//                    PackageInfo packageInfo = getPackageManager().getPackageInfo("com.tencent.mobileqq",0);
//                    Message message = new Message();
//                    message.arg1 = 1;
//                    MyHandler.sendMessage(message);
                    Toast.makeText(getApplication(), "获取到了", Toast.LENGTH_SHORT).show();
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    String packageName = applicationInfo.packageName;
                    tv_name.setText(packageName);
                } catch (Exception e) {
                    e.printStackTrace();
                    tv_name.setText(e.toString());
                    Log.e(TAG, e.toString());
                }
            }
        });


        bt_resetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_name.setText("包名");
            }
        });

        bt_getAllPackageName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    PackageInfo packInfo = getPackageManager().getPackageInfo(str_packageName, 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "报错了。。。。", Toast.LENGTH_SHORT).show();
                }
                final PackageManager packageManager = getPackageManager();//获取packagemanager
                //获取所有已安装程序的包信息
                installedPackages = packageManager.getInstalledPackages(0);
                lv_showPackageName.setAdapter(new MyAdapter(v.getContext(), installedPackages, str_packageName));
                for (PackageInfo p : installedPackages) {
                    String packageName = p.packageName;
                    if (str_packageName.equals(packageName)) {
                        tv_name.setText("找到了:" + packageName);

                        break;
                    }else {

                        tv_name.setText("未找到!!!");
                    }

                }
            }
        });



    }
}
