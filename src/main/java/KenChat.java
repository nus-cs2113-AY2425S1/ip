package main.java;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

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
        if (doList.isEmpty()) {
            throw new KenChatException(KenChatException.getTaskNotExistMessage());
        } else {
            printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < doList.size(); i++) {
                System.out.println((i + 1) + ". " + doList.get(i));
            }
        }
        printLine();
    }

    public static void addList(ArrayList<Task> doList, String item, Storage storage) throws KenChatException {
        String[] parts = item.split(" ", 2); // Split on first space to get command
        String command = parts[0];
        String description = parts.length > 1 ? parts[1] : "";


        if (description.isEmpty() && (command.equals("todo") || command.equals("deadline") || command.equals("event"))) {
            throw new KenChatException(KenChatException.getEmptyDescriptionMessage(command));
        }

        Task doItem;

        switch (command.toLowerCase()) {
        case "todo":
            doItem = new Task.ToDo(description);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length == 2) {
                doItem = new Task.Deadline(deadlineParts[0], deadlineParts[1]);
            } else {
                throw new KenChatException(KenChatException.getInvalidDeadlineFormatMessage());
            }
            break;
        case "event":
            String[] eventParts = description.split(" /from | /to ", 3);
            if (eventParts.length == 3) {
                doItem = new Task.Event(eventParts[0], eventParts[1], eventParts[2]);
            } else {
                throw new KenChatException(KenChatException.getInvalidEventFormatMessage());
            }
            break;
        default:
            throw new KenChatException(KenChatException.getUnknownCommandMessage());
        }

        doList.add(doItem);  // Add the task to the list
        storage.save(doList);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + doItem);
        System.out.println("Now you have " + doList.size() + " tasks in the list.");
        printLine();
    }

    public static void deleteTask(ArrayList<Task> doList, int index, Storage storage) throws KenChatException {
        printLine();
        Task removedTask = doList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + doList.size() + " tasks in the list.");
        printLine();

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
        System.out.println("7. delete <task number> - Deletes the specified task from the list.");
        System.out.println("8. bye - Exits the program.");
        System.out.println("9. help - Shows this help message.");
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
                String str = sc.nextLine().trim();
                if (str.contains("  ")) { // Check if there are multiple spaces
                    throw new KenChatException(KenChatException.multipleSpaces());
                }
                String[] command = str.split(" ", 2);

                if (str.trim().isEmpty()) {
                    throw new KenChatException(KenChatException.emptyCommand());
                }

                switch (command[0].toLowerCase()) {
                case "bye":
                    running = false;
                    break;
                case "help":
                    showHelp();
                    break;
                case "list":
                    displayList(doList);
                    break;
                case "mark":
                    if (doList.isEmpty()) {
                        throw new KenChatException(KenChatException.getTaskNotExistMessage());
                    }
                    if (command.length < 2) {
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
                    break;
                case "unmark":
                    if (doList.isEmpty()) {
                        throw new KenChatException(KenChatException.getTaskNotExistMessage());
                    }
                    if (command.length < 2) {
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
                    break;
                case "delete":
                    if (doList.isEmpty()) {
                        throw new KenChatException(KenChatException.getTaskNotExistMessage());
                    }
                    if (command.length < 2) {
                        throw new KenChatException(KenChatException.getEmptyTaskNumberMessage("delete"));
                    }
                    try {
                        int itemNumber = Integer.parseInt(command[1]) - 1;
                        if (itemNumber < 0 || itemNumber >= doList.size()) {
                            throw new KenChatException(KenChatException.getTaskNumberDoesNotExistMessage());
                        } else {
                            deleteTask(doList, itemNumber, storage);
                        }
                    } catch (NumberFormatException e) {
                        throw new KenChatException(KenChatException.getInvalidTaskNumberMessage());
                    }
                    break;
                default:
                    addList(doList, str, storage);
                    break;
                }
            } catch (KenChatException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
                }
            }
            endProgramme();
        }
    }
