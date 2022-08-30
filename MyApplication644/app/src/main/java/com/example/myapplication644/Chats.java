package com.example.myapplication644;

import java.util.ArrayList;

public class Chats {
    private Chat chat = new Chat();
    public String name, id;

    public static class Chat{
        public String name_user, msg;

        public Chat() {
        }

        public Chat(String name_user, String msg) {
            this.name_user = name_user;
            this.msg = msg;
        }

        public String getName_user() {
            return name_user;
        }

        public void setName_user(String name_user) {
            this.name_user = name_user;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public Chats() {

    }

    public Chats(String s) {
    }

    public Chats(String name, String id, Chat chat) {
        this.name = name;
        this.id = id;
        this.chat = chat;
        //this.chat = chat;
    }

    //public ArrayList<String> getChat() {
//        return chat;
//    }

//    public void setChat(ArrayList<String> chat) {
//        this.chat = chat;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public static class Chat{
//        //private final String name = Chats.nam;
//        private String message, userName;
//        private ArrayList<String> messages = new ArrayList<>();
//
//        public Chat(String userName, ArrayList<String> messages) {
//            this.userName = userName;
//            this.messages = messages;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public ArrayList<String> getMessages() {
//            return messages;
//        }
//
//        public void setMessages(ArrayList<String> messages) {
//            this.messages = messages;
//        }
//    }
}
