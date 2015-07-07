package com.bemobi.app.smstools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.bemobi.app.smstools.constants.Constants;

import java.util.Arrays;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class Connection {

    /**
     *
     * Checa se o device esta conectado em alguma rede da bemobi (visitante ou corporativa)
     *
     * @param ctx
     * @return
     */
    public static boolean isConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnectedToWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
        return isConnectedToWifi ? isWifiCoorp(ctx) : false;
    }

    private static boolean isWifiCoorp(Context ctx)
    {
        WifiManager wifiManager = (WifiManager) ctx.getSystemService(ctx.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        for(String ssidNetwork : Arrays.asList(Constants.SSID))
        {
            if(wifiInfo.getSSID().contains(ssidNetwork))
            {
                return true;
            }
        }
        return false;
    }
}
