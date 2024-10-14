package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class KenChat {
    public static void printLine() {
        String line = "____________________________________";
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

    public static void displayList(ArrayList<Task> doList) throws KenChatException {
        printLine();
        if (doList.isEmpty()) {
            throw new KenChatException(KenChatException.getTaskNotExistMessage());
        }
        for (int i = 0; i < doList.size(); i++) {
            System.out.println((i + 1) + ". " + doList.get(i));
        }
        printLine();
    }

    public static void addList(ArrayList<Task> doList, String item, Storage storage) throws KenChatException {
        printLine();
        String[] parts = item.split(" ", 2); // Split on first space to get command
        String command = parts[0];
        String description = parts.length > 1 ? parts[1] : "";


        if (description.isEmpty() && (command.equals("todo") || command.equals("deadline") || command.equals("event"))) {
            throw new KenChatException(KenChatException.getEmptyDescriptionMessage(command));
        }

        Task doItem;

        if (command.equalsIgnoreCase("todo")) {
            doItem = new Task.ToDo(description);
            doList.add(doItem);
        } else if (command.equalsIgnoreCase("deadline")) {
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length == 2) {
                doItem = new Task.Deadline(deadlineParts[0], deadlineParts[1]);
                doList.add(doItem);
            } else {
                throw new KenChatException(KenChatException.getInvalidDeadlineFormatMessage());
            }
        } else if (command.equalsIgnoreCase("event")) {
            String[] eventParts = description.split(" /from | /to ", 3);
            if (eventParts.length == 3) {
                doItem = new Task.Event(eventParts[0], eventParts[1], eventParts[2]);
                doList.add(doItem);
            } else {
                throw new KenChatException(KenChatException.getInvalidEventFormatMessage());
            }
        } else {
            throw new KenChatException(KenChatException.getUnknownCommandMessage());
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + doList.get(doList.size() - 1));
        System.out.println("Now you have " + doList.size() + " tasks in the list.");
        printLine();

        // Save the updated list to the file
        storage.save(doList);
    }

    public static void setTaskStatus(boolean isMark, Task item, Storage storage, ArrayList<Task> doList) throws KenChatException {
        printLine();
        if (item == null) {
            throw new KenChatException(KenChatException.getTaskNotExistMessage());
        }
        if (isMark) {
            item.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            item.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + item);
        printLine();

        // Save the updated list to the file
        storage.save(doList);
    }

    public static void showHelp() {
        printLine();
        System.out.println("Valid commands:");
        System.out.println("1. todo <description> - Adds a todo task.");
        System.out.println("2. deadline <description> /by <date> - Adds a deadline task. <date> can be date and/or time.");
        System.out.println("3. event <description> /from <date> /to <date> - Adds an event task.  <date> can be date and/or time.");
        System.out.println("4. list - Displays all tasks in the list.");
        System.out.println("5. mark <task number> - Marks the specified task as done.");
        System.out.println("6. unmark <task number> - Marks the specified task as not done.");
        System.out.println("7. bye - Exits the program.");
        System.out.println("8. help - Shows this help message.");
        printLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        ArrayList<Task> doList = new ArrayList<>();
        Storage storage = new Storage("./data/KenChat.txt");

        try {
            doList = storage.load();
        } catch (KenChatException e) {
            System.out.println(e.getMessage());
        }

        startProgramme();

        while (running) {
            try {
                System.out.println();
                String str = sc.nextLine();
                String[] command = str.split(" ");

                if (str.trim().isEmpty()) {
                    throw new KenChatException(KenChatException.emptyCommand());
                }

                if (command[0].equalsIgnoreCase("bye")) {
                    running = false;
                } else if (command[0].equalsIgnoreCase("help")) {
                    showHelp();
                }else if (command[0].equalsIgnoreCase("list")) {
                    displayList(doList);
                } else if (command[0].equalsIgnoreCase("mark")) {
                    if (doList.isEmpty()) {
                        throw new KenChatException(KenChatException.getTaskNotExistMessage());
                    } else if (command.length < 2) {
                        throw new KenChatException(KenChatException.getEmptyTaskNumberMessage("mark"));
                    }
                    try {
                        int itemNumber = Integer.parseInt(command[1]) - 1;
                        if (itemNumber < 0 || itemNumber >= doList.size()) {
                            throw new KenChatException(KenChatException.getTaskNumberDoesNotExistMessage());
                        } else {
                            setTaskStatus(true, doList.get(itemNumber), storage, doList);
                        }
                    } catch (NumberFormatException e) {
                        throw new KenChatException(KenChatException.getInvalidTaskNumberMessage());
                    }
                } else if (command[0].equalsIgnoreCase("unmark")) {
                    if (doList.isEmpty()) {
                        throw new KenChatException(KenChatException.getTaskNotExistMessage());
                    } if (command.length < 2) {
                        throw new KenChatException(KenChatException.getEmptyTaskNumberMessage("unmark"));
                    }
                    try {
                        int itemNumber = Integer.parseInt(command[1]) - 1;
                        if (itemNumber < 0 || itemNumber >= doList.size()) {
                            throw new KenChatException(KenChatException.getTaskNumberDoesNotExistMessage());
                        } else {
                            setTaskStatus(false, doList.get(itemNumber), storage, doList);
                        }
                    } catch (NumberFormatException e) {
                        throw new KenChatException(KenChatException.getInvalidTaskNumberMessage());
                    }
                } else {
                    addList(doList, str, storage);
                }
            } catch (KenChatException e) {
                System.out.println(e.getMessage());
                printLine();
            }
        }
        endProgramme();
    }
}