package Utils;

import Entity.Message;
import Entity.messageList;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class messageHandler {

    public static void preHandle(messageList list) {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while(true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("-----------------------------------\n");
                System.out.println("Bye! Hope to see you soon!\n");
                System.out.println("-----------------------------------\n");
                break;

            } else if (input.equals("list")) {
                messageHandler.listShow(list);
            } else if (input.contains("mark") || input.contains("unmark")) {
                messageHandler.mark(list, input);
            } else {
                Message message = new Message(input);
                messageHandler.addList(list, message);
            }
        }
    }

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
        boolean isDone = false;
        List<Message> messages = list.getMessages();
        while(i <= messages.size()) {
            isDone = messages.get(i-1).isDone();
            String doneSign = "";
            if(isDone) {
                doneSign = "X";
            }
            else {
                doneSign = "";
            }
            System.out.println(i + ". " + "[" + doneSign + "] " + messages.get(i-1).getMessage());
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

    public static void mark(messageList list, String input) {
        List<Message> messages = list.getMessages();
        String[] sentences = input.split(" ");
        int number = Integer.parseInt(sentences[1]);
        if(number > messages.size()) {
            System.out.println("Sorry, you are marking an event that has not been added");
            return;
        }

        if(input.contains("unmark")){
            messages.get(number-1).setDone(false);
            System.out.println("-----------------------------------\n");
            System.out.println("I have marked this task as not done!\n" + "[X] " + messages.get(number-1).getMessage());
            System.out.println("-----------------------------------\n");
        }
        else {
            messages.get(number - 1 ).setDone(true);
            System.out.println("-----------------------------------\n");
            System.out.println("I have marked this task as done!\n" + "[X] " + messages.get(number-1).getMessage());
            System.out.println("-----------------------------------\n");
        }
    }
}
