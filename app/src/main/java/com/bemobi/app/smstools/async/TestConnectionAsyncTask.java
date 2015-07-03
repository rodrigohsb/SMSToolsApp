package com.bemobi.app.smstools.async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class TestConnectionAsyncTask extends AsyncTask<Void, Void, Boolean>
{

    private Context ctx;

    public TestConnectionAsyncTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        //TODO Fazer um ping no servidor!
        return true;
    }

    @Override
    protected void onPostExecute(Boolean pong)
    {
        final String msg;
        if (pong)
        {
            msg = "Conexão Ok";
        }
        else
        {
            msg = "Deu alguma merda";
        }
        ((Activity) ctx).runOnUiThread(new Runnable()
        {
            @Override
            public void run ()
            {
                Toast.makeText(ctx.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
