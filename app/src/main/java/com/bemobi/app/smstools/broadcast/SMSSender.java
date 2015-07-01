package com.bemobi.app.smstools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by rodrigo.bacellar on 15/06/2015.
 */
public class SMSSender extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {

        //TODO Receber o LA e o texto

        try
        {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("880", null, "ABACAXI", null, null);
            Toast.makeText(context, "SMS enviado!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context, "Falha!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
