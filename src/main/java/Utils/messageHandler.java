package Utils;

import Entity.Message;
import Entity.messageList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class messageHandler {

    private static int todo = 1;
    private static int deadline = 2;
    private static int event = 3;

    /**
     * Converts time to LocalDateTime format
     *
     * <p>This method converts the input time to LocalDateTime format</p>
     * @param input The input of time to be converted.
     */

    public static LocalDateTime convertToDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-dd-MM-yyyy");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Prehandles the user input and dispatch task to different methods
     *
     * <p>Prehandles the user input and dispatch task to different
     * methods by recognizing key words in user input</p>
     * @param list The message list to execute user command on.
     */

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

            }
            else if (input.equals("list")) {
                messageHandler.listShow(list);
            }
            else if (input.contains("mark") || input.contains("unmark")) {
                messageHandler.mark(list, input);
            }
            else if (input.contains("delete")) {
                messageHandler.delete(list, input);
            }
            else if (input.contains("add")) {
                messageHandler.addList(list, input);
            }
            else if (input.contains("find")) {
                messageHandler.find(list, input);
            }
        }
    }

    /**
     * Print an existing item
     *
     * <p>This method is to print an input message to the terminal</p>
     * @param index The index of the item.
     * @param message The message to be printed.
     */

    public static void print(Message message, int index) {
        boolean isDone = message.isDone();
        String doneSign = "";
        if(isDone) {
            doneSign = "X";
        }
        else {
            doneSign = "";
        }
        int type = message.getType();
        String typeSign = "";
        String startTime = "";
        String endTime = "";
        String By = "";
        String From = "";
        String task = message.getMessage();
        String number = "";
        if(index > 0) {
            number = String.valueOf(index);
        }

        String output = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-dd-MM-yyyy");

        if(type == todo){
            typeSign = "[T]";
            output = number + ". " + typeSign + "[" + doneSign + "] " + task;
        }
        else if(type == deadline){
            By = " By: ";
            typeSign = "[D]";
            endTime = message.getEndTime().format(formatter);
            output = number + ". " + typeSign + "[" + doneSign + "] " + task + By + endTime;
        }
        else if(type == event){
            typeSign = "[E]";
            By = " By: ";
            From = " From: ";
            startTime = message.getStartTime().format(formatter);
            endTime = message.getEndTime().format(formatter);
            output = number + ". " + typeSign + "[" + doneSign + "] " + task + From + startTime + By + endTime;
        }
        System.out.println(output);
    }

    /**
     * Show list
     *
     * <p>This method is to show or to list down the message list</p>
     * @param list The message list to show.
     */

    public static void listShow(messageList list) {
        System.out.println("-----------------------------------\n");
        int i = 1;
        boolean isDone = false;
        int type = 0;
        List<Message> messages = list.getMessages();
        while(i <= messages.size()) {
            Message message = messages.get(i - 1);
            print(message, i);
            i++;
        }
        if(messages.size() == 0) {
            System.out.println("No task found");
        }
        System.out.println("-----------------------------------\n");
    }

    /**
     * Add new item to message list
     *
     * <p>This method is to extract information
     * from the user input and add them to the message list</p>
     * @param input The input to be added.
     * @param list The message list to add to.
     */

    public static void addList(messageList list, String input) throws IOException {
        if(input.contains("todo")) {
            List<Message> messages = list.getMessages();
            Message message = new Message(input);

            String eventName = input.substring(8).trim();
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
            String eventName = strings[strings.length-2].split(" ")[2];
            Message message = new Message(eventName, convertToDate(endTime), 2);
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
            String eventName = strings[strings.length-3].split(" ")[2];
            Message message = new Message(eventName, convertToDate(startTime), convertToDate(endTime), 3);
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

    /**
     * Mark existing item in the list
     *
     * <p>This method is to mark existing items
     * in the list by analysing the input</p>
     * @param input The input to recognize.
     * @param list The message list to use.
     */

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

    /**
     * Delete an existing item to message list
     *
     * <p>This method is to extract information
     * from the user input and delete corresponding
     * item from the message list</p>
     * @param input The input to be deleted.
     * @param list The message list to delete from.
     * @throws IOException If an error occurs.
     */

    public static void delete(messageList list, String input) throws IOException {
        String[] sentences = input.split(" ");
        List<Message> messages = list.getMessages();
        int i = 0;
        for(i = 0; i < messages.size(); i++) {
            if(messages.get(i).getMessage().equals(sentences[1])) {
                messages.remove(i);
                break;
            }
        }
        if(messages.size() == 0 || i > messages.size() - 1) {
            System.out.println("Sorry, you are deleting an event that has not been added");
        }
        else {
            System.out.println("------------------------------------\n");
            System.out.println("You have successfully deleted this task");
            System.out.println("------------------------------------\n");
        }
        list.setMessages(messages);
        saveHandler.writeToFile(list);
    }

    /**
     * Find existing items in message list
     *
     * <p>This method is to extract information
     * from the user input and find items
     * containing the input from the message list</p>
     * @param input The input to be searched.
     * @param list The message list to search from.
     */

    public static void find(messageList list, String input) {
        List<Message> messages = list.getMessages();
        String target = input.substring(4).trim();
        int number = 0;
        for(int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            String task = message.getMessage();
            if(task.contains(target)) {
                number++;
                print(message, number);
            }
        }
        if(number == 0) {
            System.out.println("Sorry, no task is found");
        }
    }
}
