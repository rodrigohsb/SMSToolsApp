package com.bemobi.app.smstools.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class TestConnectionAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private Context ctx;

    private ProgressDialog dialog;

    public TestConnectionAsyncTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Testando conexão...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        //TODO Fazer um ping no servidor!
        return null;
    }

    @Override
    protected void onPostExecute(Boolean done) {
        dialog.dismiss();

        final String message = done ? "Sucesso!" : "Deu merda!";

        ((Activity) ctx).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
            }
        });

        super.onPostExecute(done);
    }
}
