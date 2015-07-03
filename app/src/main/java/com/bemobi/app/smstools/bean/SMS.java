package com.bemobi.app.smstools.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rodrigo.bacellar on 02/07/2015.
 */
public class SMS implements Serializable {

    private String la;

    private String text;

    private Date date;

    public SMS(String la, String text, Date date) {
        this.la = la;
        this.text = text;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "la='" + la + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
