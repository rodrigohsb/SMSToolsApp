package com.bemobi.app.smstools.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class Connection {

    public static boolean wifiEnable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

}
