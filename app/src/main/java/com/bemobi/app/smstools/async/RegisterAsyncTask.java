package com.bemobi.app.smstools.async;

import android.os.AsyncTask;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class RegisterAsyncTask extends AsyncTask<Void,Void,Void>
{

    private String token;

    public RegisterAsyncTask(String token)
    {
        this.token = token;
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        if(token != null)
        {
            //TODO passar token para o server
        }
        return null;
    }
}
