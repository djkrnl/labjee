package com.example.labjee.helpers.mediator;

// Tydzień 5 - Mediator - klasa koleżeńska
public class ChatUser {

    ChatMediator chat;

    String email;

    public ChatUser(ChatMediator chat, String email) {
        this.chat = chat;
        this.email = email;
    }

    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        EmailSender sender = new EmailSender();
        sender.sendSimpleMessage(this.email, "New message", message);
    }
}
