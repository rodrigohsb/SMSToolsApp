package com.bemobi.app.smstools.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bemobi.app.smstools.R;
import com.bemobi.app.smstools.async.TestConnectionAsyncTask;
import com.bemobi.app.smstools.utils.AlertUtils;
import com.bemobi.app.smstools.utils.Connection;

public class HomeActivity extends Activity
{

    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ctx = this;

        findViewById(R.id.testButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (Connection.wifiEnable(ctx) && Connection.isCooopWifi(ctx))
                {
                    new TestConnectionAsyncTask(ctx).execute();
                }
                else
                {
                    DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.dismiss();
                        }
                    };

                    new AlertUtils(ctx).getAlertDialog("Por favor, conecte ao wifi corporativo!", null, false, positiveButton, "Ok", null, null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                startActivity(new Intent(ctx, SettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
