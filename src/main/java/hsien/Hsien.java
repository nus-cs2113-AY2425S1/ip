package hsien;

import hsien.task.*;
import hsien.exception.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.regex.*;

public class Hsien {

    public static void printLogo() {
        System.out.println(" _   _         _____        _ _   _ ");
        System.out.println("| | | | ##### |_   _|##### |   \\ | |");
        System.out.println("| |_| |#        | |  #     | |\\ \\| |");
        System.out.println("|  _  | #####   | |  ##### | | \\ \\ |");
        System.out.println("| | | |      # _| |_ #     | |  \\  |");
        System.out.println("|_| |_| ##### |_____|##### |_|   \\_|\n");
    }

    public static void printLine() {
        System.out.println("\n" + "-".repeat(50) + "\n");
    }

    public static void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List is currently empty. Please add a task!");
            return;
        }
        int counter = 1;
        System.out.println("Here are the tasks in your list!");
        for (Task t : tasks) {
            System.out.printf("%s. %s%n", counter, t.getStatusDescription());
            counter += 1;
        }
    }

    public static void printCommands(List<String> validCommands) {
        System.out.println("These are the possible commands:");
        for (int i=1; i<= validCommands.size(); i+=1) {
            System.out.printf("%d. %s\n", i, validCommands.get(i - 1));
        }
    }

    public static void deleteTask(ArrayList<Task> messages, int index) {
        System.out.println("Noted I have removed this task");
        System.out.println(messages.get(index).getStatusDescription());
        messages.remove(index);
        System.out.printf("Now you have %d tasks in the list\n", messages.size());
    }

    public static void readFile(ArrayList<Task> messages) {
        try {
            File f = new File("tasks.txt");
            Scanner line = new Scanner(f);
            while (line.hasNext()) {
                String[] processedLine = processFileLine(line.nextLine().replace("\n", ""));
                addTaskFromFile(processedLine[0], processedLine[1], processedLine[2], messages);
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist
            System.out.println("File not found");
        } catch (HsienException e) {
            System.out.println("Incorrec file input");
        }
    }

    public static void writeFile(ArrayList<Task> messages) {
        try {
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current working directory: " + currentDir);
            FileWriter fw = new FileWriter("tasks.txt");
            for (Task task: messages) {
                fw.write(task.getStatusDescription() + "\n");
                System.out.println(task.getStatusDescription());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }


    public static String[] processCommand(String input) {
        String[] parts = input.split(" ");
        String desc = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        String command = parts[0];
        return new String[]{command, desc};
    }

    public static String[] processFileLine(String input) throws HsienException{
        String[] parts = input.split(" ");
        System.out.println(input);
        String regex = "\\[(.*?)\\] \\[(.*?)\\] (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.trim());

        if (matcher.matches()) {
            String command = matcher.group(1); // Extract command from the first group
            String isMark = matcher.group(2); // Extract mark status from the second group
            String desc = matcher.group(3); // Extract description from the third group
            return new String[]{command, isMark, desc};
        } else {
            throw new HsienException();
        }
    }

    public static void addTaskFromFile(String command, String isMark, String desc, ArrayList<Task> messages) {
        Task newTask = null;
        String tempDesc;

        // Create Task object based on action
        if (command.equals("T")) {
            newTask = new Todo(desc);
        } else if (command.equals("D")) {
            tempDesc = desc.split("\\(by:")[0].trim();
            String byDate = desc.split("\\(by:")[1].trim();
            newTask = new Deadline(tempDesc, byDate);
        } else {
            tempDesc = desc.split("\\(from:")[0].trim();
            String[] dates = desc.split("\\(from:")[1].split("to:");
            String fromDate = dates[0].trim();
            String toDate = dates[1].substring(0, dates[1].length()-1).trim();
            newTask = new Event(tempDesc, fromDate, toDate);
        }

        // Mark task
        if (isMark.equals("X")) {
            newTask.mark();
        }

        messages.add(newTask);
        System.out.println("Added task: " + newTask.getDescription());
        System.out.println(String.format("Now you have [%d] tasks in the list.", messages.size()));
    }

    public static void addTask(String command, String desc, ArrayList<Task> messages) {
        // Empty task
        try {
            if (desc.isEmpty()) {
                throw new HsienException();
            }
        } catch (HsienException e) {
            System.out.println("Description cannot be left empty");
            printLine();
            return;
        }

        Task newTask = null;
        String tempDesc;

        // Create Task object based on action
        if (command.equals("todo")) {
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            tempDesc = desc.split("/by")[0].trim();
            String byDate = desc.split("/by")[1].trim();
            newTask = new Deadline(tempDesc, byDate);
        } else {
            tempDesc = desc.split("/from")[0].trim();
            String[] dates = desc.split("/from")[1].split("/to");
            String fromDate = dates[0].trim();
            String toDate = dates[1].trim();
            newTask = new Event(tempDesc, fromDate, toDate);
        }

        messages.add(newTask);
        System.out.println("Added task: " + newTask.getDescription());
        System.out.println(String.format("Now you have [%d] tasks in the list.", messages.size()));
    }

    public static void main(String[] args) {
        printLine();
        printLogo();
        // Greet
        System.out.println("Hello! I am Hsien, your personal chatbot...");
        printLine();

        List<String> validCommands = Arrays.asList("bye", "list", "mark", "unmark", "delete", "todo", "deadline", "event");
        ArrayList<Task> messages = new ArrayList<>();
        readFile(messages);
        printLine();

        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            printCommands(validCommands);
            System.out.print("Please enter a command/add task (type 'bye' to exit): ");
            String input = in.nextLine();

            String[] processedCommand = processCommand(input);
            // Extract out the exact command
            String command = processedCommand[0];
            String desc = processedCommand[1];

            try {
                // Check if valid commmand
                if (!validCommands.contains(command)) {
                    throw new HsienException();
                }
            } catch (HsienException e) {
                System.out.println("Please enter a valid command from the list! ");
                printLine();
                continue;
            }

            if (command.equals("bye")) {
                // Exit
                System.out.println("Have a good day! Bye!");
                isRunning = false;
                continue;
            } else if (command.equals("list")) {
                printList(messages);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                // Get the task index
                int index = Integer.parseInt(desc);
                boolean isMarking = command.equals("mark");

                // Out of bounds
                if (index == 0 || index > messages.size())  {
                    System.out.println("Index out of bounds");
                    continue;
                }

                // Perform marking or unmarking
                if (isMarking) {
                    messages.get(index - 1).mark();
                    System.out.println("You marked " + index + " as marked");
                } else {
                    messages.get(index - 1).unmark();
                    System.out.println("You marked " + index + " as unmarked");
                }

                // Print status of the task
                System.out.println(messages.get(index - 1).getStatusDescription());
            } else if (command.equals("delete")) {
                try {
                    deleteTask(messages, Integer.parseInt(desc)-1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format");
                }
            } else {
                addTask(command, desc, messages);
            }
            printLine();
        }
        in.close();
        writeFile(messages);
    }
}
