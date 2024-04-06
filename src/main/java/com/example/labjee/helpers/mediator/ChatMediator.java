package com.example.labjee.helpers.mediator;

import java.util.List;
// Tydzień 5 - Mediator - klasa mediatora
public class ChatMediator {
    List<ChatUser> users;

    public void sendMessage(String message, ChatUser sender) {
        for (ChatUser user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }

    public void add(ChatUser user) {
        this.users.add(user);
    }
}
