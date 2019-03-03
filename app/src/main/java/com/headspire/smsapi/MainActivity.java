package com.headspire.smsapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @version 1.0
 * @author Ashish yadav 03-03-2019
 * send SMS
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CODE =2 ;
    private Button send;
    private EditText phoneNumber;
    private EditText message;
    private SmsManager smsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=findViewById(R.id.sendsms);
        phoneNumber=findViewById(R.id.phonenumber);
        message=findViewById(R.id.messagebody);
        send.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(this
        ,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this
            ,new String[]{Manifest.permission.SEND_SMS},REQUEST_CODE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sendsms:
                sendMessage(phoneNumber.getText().toString(),message.getText().toString());
                break;
        }
    }

    private void sendMessage(String phoneNumber,String message) {
        smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,message,null,null);
        Toast.makeText(this,"sms sent",Toast.LENGTH_SHORT).show();
    }

}
