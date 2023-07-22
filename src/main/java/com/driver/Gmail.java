package com.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    List<Message> inbox ;
    List<Message> trash;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new ArrayList<>();
        trash = new ArrayList<>();

    }

    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        Message message1 = new Message(date, sender, message);
        if (inbox.size() == inboxCapacity){
            Message oldest = inbox.remove(inbox.size ()- 1);
            trash.add(oldest);
        }
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        inbox.add(message1);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for (Message message1 : inbox){
            if (message1.getMessage().equals(message)){
                trash.add(message1);
                inbox.remove(message1);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        if (inbox.size() == 0) return null;
        // Else, return the message of the latest mail present in the inbox
        Message latest = inbox.get(inbox.size() - 1);
        return latest.getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        if (inbox.size() == 0) return  null;
        // Else, return the message of the oldest mail present in the inbox
        Message oldest = inbox.get(0);
        return oldest.getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        int mails = 0;
        if (inbox.size() == 0) return 0;
        for (int i = 0; i< inbox.size(); i++){
            Message message = inbox.get(i);
            if (message.getDate().equals(start)){
                while (i < inbox.size() ){
                    Message message1 = inbox.get(i);
                    if (message.getDate().equals(end)) return mails;
                    mails++;
                }
            }
        }
        //It is guaranteed that start date <= end date
        return 0;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        if (trash.isEmpty()) return;
        trash.removeAll(trash);
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return  this.inboxCapacity;
    }
}
