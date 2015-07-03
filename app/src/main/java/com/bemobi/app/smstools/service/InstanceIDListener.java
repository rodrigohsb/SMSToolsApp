package com.bemobi.app.smstools.service;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class InstanceIDListener extends InstanceIDListenerService
{

    @Override
    public void onTokenRefresh()
    {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
