package Utils;

import Entity.Message;
import Entity.messageList;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class messageHandler {

    public static void preHandle(messageList list) throws IOException {
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
            } else if (input.contains("delete")) {
                messageHandler.delete(list, input);
            } else {
                messageHandler.addList(list, input);
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
        int type = 0;
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
            type = messages.get(i-1).getType();
            String typeSign = "";
            String startTime = "";
            String endTime = "";
            String By = "";
            String From = "";
            if(type == 1){
                typeSign = "[T]";
            }
            else if(type == 2){
                By = " By: ";
                typeSign = "[D]";
                endTime = messages.get(i-1).getEndTime();
            }
            else if(type == 3){
                typeSign = "[E]";
                By = " By: ";
                From = " From: ";
                startTime = messages.get(i-1).getStartTime();
                endTime = messages.get(i-1).getEndTime();
            }
            System.out.println(i + ". " + typeSign + "[" + doneSign + "] " + messages.get(i-1).getMessage() + From + startTime + By + endTime );
            i++;
        }
        System.out.println("-----------------------------------\n");
    }

    public static void addList(messageList list, String input) throws IOException {
        if(input.contains("todo")) {
            List<Message> messages = list.getMessages();
            Message message = new Message(input);
            String[] strings = input.split(" ");
            String eventName = strings[1];
            message.setMessage(eventName);
            messages.add(message);
            list.setMessages(messages);
            System.out.println("-----------------------------------");
            System.out.println("added:" + message.getMessage());
        }

        else if(input.contains("deadline")) {
            List<Message> messages = list.getMessages();
            String[] strings = input.split("/");
            String endTime = strings[strings.length-1].split(" ")[1];
            String eventName = strings[strings.length-2].split(" ")[1];
            Message message = new Message(eventName, endTime, 2);
            messages.add(message);
            list.setMessages(messages);
            System.out.println("-----------------------------------");
            System.out.println("added:" + message.getMessage());
        }

        else if(input.contains("event")) {
            List<Message> messages = list.getMessages();
            String[] strings = input.split("/");
            String startTime = strings[strings.length-2].split(" ")[1];
            String endTime = strings[strings.length-1].split(" ")[1];
            String eventName = strings[strings.length-3].split(" ")[1];
            Message message = new Message(eventName, startTime, endTime, 3);
            messages.add(message);
            list.setMessages(messages);
            System.out.println("-----------------------------------");
            System.out.println("added:" + message.getMessage());
        }
        saveHandler.writeToFile(list);
        int taskNumber = list.getMessages().size();
        System.out.println("Now you have " + taskNumber + " tasks");
        System.out.println("-----------------------------------\n");
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

    public static void delete(messageList list, String input) throws IOException {
        String[] sentences = input.split(" ");
        int number = Integer.parseInt(sentences[1]);
        List<Message> messages = list.getMessages();
        if(number - 1 > messages.size() || number < 1) {
            System.out.println("You are deleting an event that does not exist");
        }
        messages.remove(number - 1);
        list.setMessages(messages);
        saveHandler.writeToFile(list);
        System.out.println("------------------------------------\n");
        System.out.println("You have successfully deleted this task");
        System.out.println("------------------------------------\n");
    }
}
