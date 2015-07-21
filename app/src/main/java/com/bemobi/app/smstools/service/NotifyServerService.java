package com.bemobi.app.smstools.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.bemobi.app.smstools.bean.SMS;
import com.bemobi.app.smstools.constants.Constants;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Classe para notificar o server quando mensagem recebida ou enviada.
 *
 * Created by rodrigo.bacellar on 07/07/2015.
 */
public class NotifyServerService extends IntentService
{
    private static final String TAG = "NotifyServerService";

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public NotifyServerService()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {

        Bundle bundle = intent.getExtras();

        SMS sms = (SMS) bundle.getSerializable(Constants.SMS);
        Date sendDate = sms.getSendDate();
        Date receiveDate = sms.getReceivedDate();

        if(sendDate != null)
        {
            registerSentSms(sms);
        }
        else
        {
            registerReceivedSms(sms);
        }
    }

    private void registerReceivedSms(SMS sms)
    {

        String la = sms.getLa();
        try
        {

            URL url = new URL(Constants.SERVER_BASE_URL + Constants.SERVER_RECEIVED_PATH + la);
            StringBuilder body = addParameters(sms);

            int responseCode = send(url, body.toString());

            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                System.out.println("Registration success");
            }
            else
            {
                //TODO Salva em banco e retentar?
            }
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void registerSentSms(SMS sms)
    {
        String la = sms.getLa();
        try
        {

            URL url = new URL(Constants.SERVER_BASE_URL + Constants.SERVER_SENT_PATH + la);
            StringBuilder body = addParameters(sms);

            int responseCode = send(url, body.toString());

            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                System.out.println("Registration success");
            }
            else
            {
                //TODO Salva em banco e retentar?
            }
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private StringBuilder addParameters(SMS sms)
    {
        String la = sms.getLa();
        String text = sms.getText();
        Date receivedDate = sms.getReceivedDate();
        Date sendDate = sms.getSendDate();

        StringBuilder body = new StringBuilder(Constants.TEXT +"=" + text);

        if(receivedDate != null)
        {
            addParameter(body,Constants.RECEIVE_DATE, format.format(receivedDate));
        }
        else
        {
            addParameter(body,Constants.SEND_DATE, format.format(sendDate));
        }
        return body;
    }

    private void addParameter(StringBuilder body, String name, String value)
    {
        body.append('&').append(name).append('=').append(value);
    }


    private int send(URL url, String body)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setFixedLengthStreamingMode(body.getBytes().length);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();
            connection.connect();

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode;
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
        }
        return 500;
    }
}
