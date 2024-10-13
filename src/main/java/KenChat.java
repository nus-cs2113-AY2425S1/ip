package main.java;

import java.util.Scanner;

public class KenChat {
    public static void printLine() {
        String line = "____________________________________" ;
        System.out.println(line);
    }

    public static void startProgramme() {
        String chatBotName = "KenChat";
        printLine();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void endProgramme() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void displayList(Task[] doList){
        printLine();
        for (int i=0; i< doList.length; i++){
            if(doList[i] != null){
                System.out.println((i+1)+". "+doList[i]);
            }
        }
        printLine();
    }

    public static void addList(Task[] doList, String item) {
        printLine();
        String[] parts = item.split(" ", 2); // Split on first space to get command
        String command = parts[0];
        String description = parts.length > 1 ? parts[1] : "";

        if (command.equalsIgnoreCase("todo")) {
            Task doItem = new Task.ToDo(description);
            addTask(doList, doItem);
        } else if (command.equalsIgnoreCase("deadline")) {
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length == 2) {
                Task doItem = new Task.Deadline(deadlineParts[0], deadlineParts[1]);
                addTask(doList, doItem);
            } else {
                System.out.println("Invalid deadline format. Use: deadline <description> /by <date/time>");
            }
        } else if (command.equalsIgnoreCase("event")) {
            String[] eventParts = description.split(" /from | /to ", 3);
            if (eventParts.length == 3) {
                Task doItem = new Task.Event(eventParts[0], eventParts[1], eventParts[2]);
                addTask(doList, doItem);
            } else {
                System.out.println("Invalid event format. Use: event <description> /from <start_time> /to <end_time>");
            }
        } else {
            Task doItem = new Task.ToDo(item); // Default to ToDo if no specific type
            addTask(doList, doItem);
        }
    }

    private static void addTask(Task[] doList, Task doItem) {
        for (int i = 0; i < doList.length; i++) {
            if (doList[i] == null) {
                doList[i] = doItem;
                int taskCount = 0;
                for (Task task : doList) {
                    if (task != null) {
                        taskCount++;
                    }
                }
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + doItem);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                break;
            }
        }
    }

    public static void setTaskStatus(boolean isMark, Task item){
        printLine();
        if(isMark){
            item.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            item.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  "+item);
        printLine();
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        boolean running = true;
        int arraySize = 100;
        Task[] doList = new Task[arraySize];
        startProgramme();
        while (running){
            System.out.println();
            String str= sc.nextLine();
            String[] command = str.split(" ");
            if (command[0].equalsIgnoreCase("bye"))
                running = false;
            else if(command[0].equalsIgnoreCase("list"))
                displayList(doList);
            else if(command[0].equalsIgnoreCase("mark")){
                int itemNumber = Integer.parseInt(command[1]) - 1;
                setTaskStatus(true, doList[itemNumber]);
            }
            else if(command[0].equalsIgnoreCase("unmark")){
                int itemNumber = Integer.parseInt(command[1]) - 1;
                setTaskStatus(false, doList[itemNumber]);
            }
            else
                addList(doList, str);
        }
        endProgramme();
    }
}