package com.codeforgeyt.wschatapplication.storage;

import java.util.HashSet;
import java.util.Set;

public class ChatStorage {
    private static ChatStorage instance;
    private Set<String> chats;

    private ChatStorage() {
        chats = new HashSet<>();
    }

    public static synchronized ChatStorage getInstance() {
        if (instance == null) {
            instance = new ChatStorage();
        }
        return instance;
    }

    public Set<String> getChats() {
        return chats;
    }

    public void setChat(String chatName) throws Exception {
        if (chats.contains(chatName)) {
            throw new Exception("User aready exists with chatName: " + chatName);
        }
        chats.add(chatName);
    }
}
