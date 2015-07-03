package com.bemobi.app.smstools.async;

import android.os.AsyncTask;

import com.bemobi.app.smstools.bean.SMS;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class NotifyServerAcyncTask extends AsyncTask<Void, Void, Void>
{

    private SMS sms;

    public NotifyServerAcyncTask(SMS sms)
    {
        this.sms = sms;
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        //TODO passar o SMS recebido para o server.
        return null;
    }
}
