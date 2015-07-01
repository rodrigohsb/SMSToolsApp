package com.bemobi.app.smstools.async;

import android.os.AsyncTask;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class NotifyServerAcyncTask extends AsyncTask<Void, Void, Void> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //TODO Mandar SMS pro servidor!
        return null;
    }

    @Override
    protected void onPostExecute(Void done) {
        super.onPostExecute(done);
    }
}
