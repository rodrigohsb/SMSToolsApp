package com.bemobi.app.smstools.async;

import android.content.Context;
import android.os.AsyncTask;

import com.bemobi.app.smstools.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by rodrigo.bacellar on 02/07/2015.
 */
public class GcmTokenAsyncTask extends AsyncTask<Void, Void, Void>
{
    private Context ctx;

    public GcmTokenAsyncTask(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    protected Void doInBackground(Void... params)
    {

        InstanceID instanceID = InstanceID.getInstance(ctx);

        String token = null;

        try
        {
            token = instanceID.getToken(ctx.getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        new RegisterAsyncTask(token).execute();

        return null;
    }
}
