package com.bemobi.app.smstools.constants;

/**
 * Created by rodrigo.bacellar on 01/07/2015.
 */
public class Constants
{
    public static String SERVER_BASE_URL = "http://10.0.0.135:8090/smstools" ;

    public static String SERVER_REGISTER_PATH = "/device/register/%s" ;

    public static String SERVER_SENT_PATH = "/sent" ;

    public static String SERVER_RECEIVED_PATH = "/received" ;

    public static String SERVER_PING_PATH = "http://10.0.0.135:8081/ping/health" ;

    public static String TEXT = "text" ;

    public static String LA = "la" ;

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";

    public static final String PDUS = "pdus";

    public static final String[] SSID = {"m4u_me_vis","m4u_me_ntw"};

    public static String ALREADY_REGISTER = "alreadyRegister" ;

}
