package com.bemobi.app.smstools.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.bemobi.app.smstools.R;
import com.bemobi.app.smstools.constants.Constants;

/**
 * Created by rodrigo.bacellar on 02/07/2015.
 */
public class SettingsActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((TextView) findViewById(R.id.ip)).setText(Constants.SERVER_IP);
        ((TextView) findViewById(R.id.port)).setText(Constants.SERVER_PORT);
        ((TextView) findViewById(R.id.contextRoot)).setText(Constants.SERVER_CONTEXT_ROOT);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
