package com.bemobi.app.smstools.activity;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bemobi.app.smstools.R;

public class HomeActivity extends Activity
{

    Button sendButton;
    EditText textPhoneNo;
    EditText textSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        sendButton = (Button) findViewById(R.id.sendButton);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phoneNo = textPhoneNo.getText().toString();
                String sms = textSMS.getText().toString();

                try
                {

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS enviado!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Falha!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
}
