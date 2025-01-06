package com.neuro.coursedb.models;

import java.util.HashMap;
import java.util.Map;

@FilePath(value = "notifications.json")
public class Notification {
    private int userid;
    private Map<Integer, String> notificationMessages;

    public Notification(int userid) {
        this.userid = userid;
        this.notificationMessages = new HashMap<>();
    }

    public void putMessage(String notification) {
        notificationMessages.put(notificationMessages.size(), notification);
    }
}
