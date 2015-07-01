package com.bemobi.app.smstools.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bemobi.app.smstools.R;
import com.bemobi.app.smstools.async.TestConnectionAsyncTask;
import com.bemobi.app.smstools.constants.Constants;
import com.bemobi.app.smstools.utils.AlertUtils;
import com.bemobi.app.smstools.utils.Connection;

public class HomeActivity extends Activity {

    private Context ctx;

    private TextView ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ctx = this;

        ip = (TextView) findViewById(R.id.ip);

        ip.setText(Constants.SERVER_URL);

        findViewById(R.id.testButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Connection.wifiEnable(ctx))
                {
                    new TestConnectionAsyncTask(ctx).execute();
                }
                else
                {
                    DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
                    {
                        public void onClick (DialogInterface dialog, int id)
                        {
                            dialog.dismiss();
                        }
                    };
                    new AlertUtils(ctx).getAlertDialog("Nenhuma conexâo ativa pelo Wifi.", null, false, positiveButton, "Ok", null, null).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
