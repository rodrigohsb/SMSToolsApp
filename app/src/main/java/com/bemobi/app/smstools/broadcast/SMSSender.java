package com.bemobi.app.smstools.broadcast;

import android.telephony.SmsManager;

/**
 * Created by rodrigo.bacellar on 15/06/2015.
 */
public class SMSSender
{
    public static SmsManager smsManager = SmsManager.getDefault();

    public static void send(String la, String command)
    {
        try
        {
            smsManager.sendTextMessage(la, null, command, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
