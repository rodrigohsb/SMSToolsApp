package com.bemobi.app.smstools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.bemobi.app.smstools.bean.SMS;
import com.bemobi.app.smstools.constants.Constants;
import com.bemobi.app.smstools.persistence.Repository;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 *
 * Broadcast que inicia ao receber um sms.
 *
 * Created by rodrigo.bacellar on 10/06/2015.
 */
public class SMSReceiver extends BroadcastReceiver
{
    private Context ctx;

    @Override
    public void onReceive(final Context context, Intent intent)
    {
        ctx = context;

        final Bundle bundle = intent.getExtras();

        try
        {
            if (bundle != null)
            {
                final Object[] pdusObj = (Object[]) bundle.get(Constants.PDUS);

                for (int i = 0; i < pdusObj.length; i++)
                {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String from = currentMessage.getDisplayOriginatingAddress();

                    String message = currentMessage.getDisplayMessageBody();

                    SMS sms = new SMS(from,message,null,new Date());
                    notifyServer(sms);
                }
            }
        }
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

    private void notifyServer(SMS sms)
    {
        try
        {

            URL url = new URL(Constants.SERVER_BASE_URL + Constants.SERVER_RECEIVED_PATH);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                System.out.println("Registration success");
            }
            else
            {
                System.out.println("ResponseCode [ " + responseCode + "]. Registration failed for: " + (Constants.SERVER_BASE_URL + Constants.SERVER_RECEIVED_PATH));
                Repository repository = new Repository(ctx);
                repository.save(sms);
                //TODO Gravar em banco para retentar!
            }
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
