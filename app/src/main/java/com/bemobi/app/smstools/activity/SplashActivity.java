package com.bemobi.app.smstools.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.bemobi.app.smstools.R;
import com.bemobi.app.smstools.constants.Constants;
import com.bemobi.app.smstools.service.RegistrationIntentService;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class SplashActivity extends Activity
{
    private Context ctx;
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ctx = this;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        /** Primeiro acesso ou limpou o cache */
        Boolean alreadyRegister = sharedPreferences.getBoolean(Constants.ALREADY_REGISTER, false);

        if(!alreadyRegister)
        {
            startService(new Intent(ctx, RegistrationIntentService.class));
        }

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(ctx, HomeActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
