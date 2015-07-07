package com.bemobi.app.smstools.service;

import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;

import com.bemobi.app.smstools.bean.SMS;
import com.bemobi.app.smstools.constants.Constants;
import com.bemobi.app.smstools.persistence.Repository;
import com.google.android.gms.gcm.GcmListenerService;

import java.net.HttpURLConnection;
import java.net.URL;
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
            notifyServer(ctx, sms);
        }
        catch (Exception e)
        {
            //Retentar?
        }

    }
    private void notifyServer(Context ctx,SMS sms)
    {
        try
        {
            URL url = new URL(Constants.SERVER_BASE_URL + Constants.SERVER_SENT_PATH);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                //TODO Gravar em banco para retentar!
                Repository repository = new Repository(ctx);
                repository.save(sms);
            }
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
