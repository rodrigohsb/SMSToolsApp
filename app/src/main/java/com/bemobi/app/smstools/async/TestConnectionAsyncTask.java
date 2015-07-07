package com.bemobi.app.smstools.async;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.bemobi.app.smstools.constants.Constants;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class TestConnectionAsyncTask extends AsyncTask<Void, Void, String>
{

    private Context ctx;

    public TestConnectionAsyncTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(Void... params)
    {
        try
        {
            URL url = new URL(Constants.SERVER_PING_PATH);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            return httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK ? "Pong!" : "Status : " + httpConnection.getResponseCode();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    protected void onPostExecute(final String msg)
    {
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
