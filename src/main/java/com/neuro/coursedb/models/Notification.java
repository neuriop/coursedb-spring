package com.neuro.coursedb.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FilePath(value = "notifications.json")
public class Notification {
    private int userid;
    private List<Message> messages;

    public void putMessage(String message){
        messages.add(new Message(messages.size(), message));
    }

    public Notification(int userid) {
        this.userid = userid;
        messages = new ArrayList<>();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static class Message{
        public Message(int id, String message) {
            this.id = id;
            this.message = message;
        }

        public Message(){}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private int id;
        private String message;
    }








//    private int userid;
//    private Map<Integer, String> notificationMessages;
//
//    public Notification(int userid) {
//        this.userid = userid;
//        this.notificationMessages = new HashMap<>();
//    }
//
//    public void putMessage(String notification) {
//        notificationMessages.put(notificationMessages.size(), notification);
//    }
}
