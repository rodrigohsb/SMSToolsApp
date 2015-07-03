package com.bemobi.app.smstools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.bemobi.app.smstools.async.NotifyServerAcyncTask;
import com.bemobi.app.smstools.bean.SMS;
import com.bemobi.app.smstools.constants.Constants;

import java.util.Date;

/**
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

                    String from = currentMessage.getDisplayOriginatingAddress();

                    final String message = currentMessage.getDisplayMessageBody();

                    new Handler().post(new Runnable()
                    {
                        @Override
                        public void run ()
                        {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(context, "senderNum [" + from + "], message [" + message + "]", Toast.LENGTH_LONG).show();

                    SMS sms = new SMS(from, message, new Date());
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
