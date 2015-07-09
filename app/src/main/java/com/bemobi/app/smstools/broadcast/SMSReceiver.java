package com.bemobi.app.smstools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.bemobi.app.smstools.bean.SMS;
import com.bemobi.app.smstools.constants.Constants;
import com.bemobi.app.smstools.service.NotifyServerService;

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
    @Override
    public void onReceive(final Context context, Intent intent)
    {
        final Bundle bundle = intent.getExtras();

        try
        {
            if (bundle != null)
            {
                final Object[] pdusObj = (Object[]) bundle.get(Constants.PDUS);

                for (int i = 0; i < pdusObj.length; i++)
                {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String from = currentMessage.getDisplayOriginatingAddress().replace("+","");

                    String message = currentMessage.getDisplayMessageBody();

                    SMS sms = new SMS(from,message,null,new Date());

                    Intent it = new Intent(context, NotifyServerService.class);
                    it.putExtra("sms", sms);
                    context.startService(it);
                }
            }
        }
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}
