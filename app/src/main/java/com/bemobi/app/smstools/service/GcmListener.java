package com.bemobi.app.smstools.service;

import android.os.Bundle;

import com.bemobi.app.smstools.broadcast.SMSSender;
import com.bemobi.app.smstools.constants.Constants;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class GcmListener extends GcmListenerService
{
    @Override
    public void onMessageReceived(String to, Bundle data)
    {
        super.onMessageReceived(to, data);
        SMSSender.send(data.getString(Constants.LA), data.getString(Constants.COMMAND));
    }
}
