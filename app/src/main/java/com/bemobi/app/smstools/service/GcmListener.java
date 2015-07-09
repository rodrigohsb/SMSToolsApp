package com.bemobi.app.smstools.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

import com.bemobi.app.smstools.bean.SMS;
import com.bemobi.app.smstools.constants.Constants;
import com.google.android.gms.gcm.GcmListenerService;

import java.util.Date;

/**
 *
 * Listener que inicia ao receber uma mensagem do GCM
 *
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class GcmListener extends GcmListenerService
{
    private Context ctx;

    public static SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onMessageReceived(String to, Bundle data)
    {
        ctx = this;

        String la = data.getString(Constants.LA);
        String text = data.getString(Constants.TEXT);

        try
        {
            smsManager.sendTextMessage(la, null, text, null, null);
            SMS sms = new SMS(la,text,new Date(),null);

            Intent it = new Intent(ctx, NotifyServerService.class);
            it.putExtra(Constants.SMS, sms);
            ctx.startService(it);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //Retentar?
        }
    }
}
