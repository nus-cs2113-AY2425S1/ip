package Utils;

import Entity.Message;
import Entity.messageList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class messageHandler {

    public static LocalDate readTime(String time){
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-EEE-dd-MM-yyyy");
        date = LocalDate.parse(time, formatter);
        return date;
    }

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
                endTime = String.valueOf(messages.get(i-1).getEndTime());
            }
            else if(type == 3){
                typeSign = "[E]";
                By = " By: ";
                From = " From: ";
                startTime = String.valueOf(messages.get(i-1).getStartTime());
                endTime = String.valueOf(messages.get(i-1).getEndTime());
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
            String eventName = input.substring(5).trim();
            message.setMessage(eventName);
            messages.add(message);
            list.setMessages(messages);
            System.out.println("-----------------------------------");
            System.out.println("added:" + message.getMessage());
        }

        else if(input.contains("deadline")) {
            List<Message> messages = list.getMessages();
            String[] strings = input.split("/");
            String endTime = strings[strings.length-1].substring(3).trim();
            LocalDate endDate = readTime(endTime);
            String eventName = strings[strings.length-2].substring(8).trim();
            Message message = new Message(eventName, endDate, 2);
            messages.add(message);
            list.setMessages(messages);
            System.out.println("-----------------------------------");
            System.out.println("added:" + message.getMessage());
        }

        else if(input.contains("event")) {
            List<Message> messages = list.getMessages();
            String[] strings = input.split("/");
            String startTime = strings[strings.length-2].substring(5).trim();
            String endTime = strings[strings.length-1].substring(3).trim();
            LocalDate startDate = readTime(startTime);
            LocalDate endDate = readTime(endTime);
            String eventName = strings[strings.length-3].substring(5).trim();
            Message message = new Message(eventName, startDate, endDate, 3);
            messages.add(message);
            list.setMessages(messages);
            System.out.println("-----------------------------------");
            System.out.println("added:" + message.getMessage());
        }
        else {
            System.out.println("Unknown command entered!");
            return;
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
