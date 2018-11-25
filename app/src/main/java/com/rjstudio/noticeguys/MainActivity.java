package com.rjstudio.noticeguys;

import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rjstudio.noticeguys.Utilize.SmsDatabaseObserver;
import com.rjstudio.noticeguys.Utilize.TextHandle;

public class MainActivity extends AppCompatActivity {

    public static final Uri SMS_MESSAGE_URI = Uri.parse("content://sms");
    private static SmsDatabaseObserver mSmsDatabaseObserver;
    private static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(getBaseContext(),"android.permission.READ_SMS") == PackageManager.PERMISSION_DENIED){
            Log.d("TEST", "onCreate: 没有权限！");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, 1);
        };

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                    Toast.makeText(getApplicationContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                TextHandle textHandle = new TextHandle(msg.obj.toString());
                textHandle.getTicketNumber();
            }
        };
        registerSmsDatabaseChangeObserver(this);

        Button bu_send = (Button) findViewById(R.id.bu_Send);
        bu_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"TEST Rohman",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void registerSmsDatabaseChangeObserver(ContextWrapper contextWrapper){
        try{
            mSmsDatabaseObserver = new SmsDatabaseObserver(mHandler,contextWrapper.getContentResolver());
            contextWrapper.getContentResolver().registerContentObserver(SMS_MESSAGE_URI,true,mSmsDatabaseObserver);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private static void unregisterSmsDatabaseChangeObserver(ContextWrapper contextWrapper)
    {
        try
        {
            contextWrapper.getContentResolver().unregisterContentObserver(mSmsDatabaseObserver);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
