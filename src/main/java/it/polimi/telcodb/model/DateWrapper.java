package it.polimi.telcodb.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateWrapper {

    private Date date;

    public DateWrapper(String date) {
        setDate(date);
    }


    public Date getDate() {
        return date;
    }


    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.date);
    }


    private void setDate(String date) {
        try {
            this.date = new SimpleDateFormat("yyyy/MM/dd").parse(parseDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private String parseDate(String date) {
        StringBuilder parsedDate = new StringBuilder();
        String[] dateTokens = date.split("-");
        parsedDate.append(dateTokens[0]).append("/").append(dateTokens[1]).append("/").append(dateTokens[2]);
        return parsedDate.toString();
    }

}
