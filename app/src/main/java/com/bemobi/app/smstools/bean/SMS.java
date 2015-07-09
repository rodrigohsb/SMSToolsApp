package com.bemobi.app.smstools.bean;

import android.provider.BaseColumns;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rodrigo.bacellar on 02/07/2015.
 */
public class SMS implements Serializable
{

    public static String[] columns = new String[]{SmsDB._ID, SmsDB.LA, SmsDB.TEXT, SmsDB.SENT_DATE, SmsDB.RECEIVED_DATE};

    private long id;

    private String la;

    private String text;

    private Date sendDate;

    private Date receivedDate;

    public SMS(){
    }

    public SMS(String la, String text, Date sendDate, Date receivedDate) {
        this.la = la;
        this.text = text;
        this.sendDate = sendDate;
        this.receivedDate = receivedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "la='" + la + '\'' +
                ", text='" + text + '\'' +
                ", sendDate=" + sendDate +
                ", receivedDate=" + receivedDate +
                '}';
    }

    public static final class SmsDB implements BaseColumns
    {
        public static final String DEFAULT_SORT_ORDER = "id ACS";

        public static final String LA = "LA";

        public static final String TEXT = "TEXT";

        public static final String SENT_DATE = "SENT_DATE";

        public static final String RECEIVED_DATE = "RECEIVED_DATE";
    }
}
