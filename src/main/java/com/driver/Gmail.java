package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    List<Mail> inbox ;
    List<Mail> trash;
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
        Mail mail1 = new Mail(date, sender, message);
        if (inbox.size() == inboxCapacity){
            Mail oldest = inbox.get(0);
            trash.add(oldest);
            inbox.remove(0);
        }
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        inbox.add(mail1);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for (Mail mail1 : inbox){
            if (mail1.getMessage().equals(message)){
                trash.add(mail1);
                inbox.remove(mail1);
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        if (inbox.size() == 0) return null;
        // Else, return the message of the latest mail present in the inbox
        Mail latest = inbox.get(inbox.size() - 1);
        return latest.getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        if (inbox.size() == 0) return  null;
        // Else, return the message of the oldest mail present in the inbox
        Mail oldest = inbox.get(0);
        return oldest.getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        inbox.sort(Comparator.comparing(Mail::getDate));
        int mails = 0;
        if (inbox.size() == 0) return 0;

        for (Mail mail : inbox){
            if (mail.getDate().after(start) && mail.getDate().before(end)){
                mails++;
            } else if(mail.getDate().equals(start) || mail.getDate().equals(end)) {
                mails++;
            }
        }
        //It is guaranteed that start date <= end date
        return mails;
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
