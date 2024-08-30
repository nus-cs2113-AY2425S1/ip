package Utils;

import Entity.Message;
import Entity.messageList;

import java.util.List;
import java.util.Objects;

public class messageHandler {
    public static void echo(String message) {

        if(Objects.equals(message, "bye")) {
            System.out.println("Bye. Hope to see you soon!");
            return;
        }

        System.out.println("-----------------------------------\n");
        System.out.println(message);
        System.out.println("-----------------------------------\n");
    }

    public static void listShow(messageList list) {
        System.out.println("-----------------------------------\n");
        int i = 1;
        List<Message> messages = list.getMessages();
        while(i <= messages.size()) {
            System.out.println(i + ". " + messages.get(i-1).getMessage());
            i++;
        }
        System.out.println("-----------------------------------\n");
    }

    public static void addList(messageList list, Message message){
        List<Message> messages = list.getMessages();
        messages.add(message);
        list.setMessages(messages);
        System.out.println("-----------------------------------");
        System.out.println("added:" + message.getMessage());
        System.out.println("-----------------------------------");
    }
}
