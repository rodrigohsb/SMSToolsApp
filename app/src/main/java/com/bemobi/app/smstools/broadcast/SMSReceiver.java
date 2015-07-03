package com.bemobi.app.smstools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.bemobi.app.smstools.async.NotifyServerAcyncTask;
import com.bemobi.app.smstools.bean.SMS;

import java.util.Date;

/**
 * Created by rodrigo.bacellar on 10/06/2015.
 */
public class SMSReceiver extends BroadcastReceiver
{

    public static final String PDUS = "pdus";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        final Bundle bundle = intent.getExtras();

        try
        {
            if (bundle != null)
            {
                final Object[] pdusObj = (Object[]) bundle.get(PDUS);

                for (int i = 0; i < pdusObj.length; i++)
                {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String message = currentMessage.getDisplayMessageBody();

                    Toast.makeText(context, "senderNum: "+ phoneNumber + ", message: " + message, Toast.LENGTH_LONG).show();

                    SMS sms = new SMS("",message,new Date());

                    //Enviar pro Servidor
                    new NotifyServerAcyncTask(sms).execute();

                }
            }
        }
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

}
