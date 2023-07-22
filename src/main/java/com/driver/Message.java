package com.driver;

import java.util.Date;

public class Message {
   Date date;
   String sender, message;

    public Message(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

}
