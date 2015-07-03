package com.bemobi.app.smstools.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bemobi.app.smstools.R;
import com.bemobi.app.smstools.async.RegisterAsyncTask;
import com.bemobi.app.smstools.constants.Constants;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by rodrigo.bacellar on 03/07/2015.
 */
public class RegistrationIntentService extends IntentService
{
    public RegistrationIntentService()
    {
        super("RegistrationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try
        {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            new RegisterAsyncTask(token).execute();

            sharedPreferences.edit().putBoolean(Constants.SENT_TOKEN_TO_SERVER, true).apply();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            sharedPreferences.edit().putBoolean(Constants.SENT_TOKEN_TO_SERVER, false).apply();
        }


    }
}
