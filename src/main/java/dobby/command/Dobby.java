package dobby.command;
import dobby.tasks.Deadline;
import dobby.tasks.Event;
import dobby.tasks.Task;
import dobby.tasks.Todo;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;

public class Dobby {

    private static final String DASH_LINE = "____________________________________________________________";

    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static final String FILE_PATH = "./data/dobby.txt";
    private static final String DIRECTORY_PATH = "./data";


    public static void main(String[] args) {

        loadTasks();

        try (Scanner in = new Scanner(System.in)) {
            printWelcomeMessage();
            String line;
            while (true) {
                line = in.nextLine().trim();
                if (line.equalsIgnoreCase("bye")){
                    break;
                }
                try {
                    processCommand(line);
                } catch (Exception e) {
                    handleExceptions(e);
                }
            }

            saveTasks();
            printGoodbyeMessage();
        }

    }

    private static void loadTasks() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseTaskFromFile(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task t: taskList) {
                writer.println(formatTaskForFile(t));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks.");
        }
    }

    private static String formatTaskForFile(Task task) {
        String type = task.getClass().getSimpleName();
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return type + "|" + status + "|" + deadline.getDescription() + "|" + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return type + "|" + status + "|" + event.getDescription() + "|" + event.getFromTime() + "|" + event.getToTime();
        } else if (task instanceof Todo) {
            return type + "|" + status + "|" + task.getDescription();
        } else {
            return type + "|" + status + "|" + task.getDescription();
        }
    }

    private static Task parseTaskFromFile(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
        case "Todo":
            Todo todo = new Todo(parts[2]);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "Deadline":
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "Event":
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            return null;
        }
    }

    private static void handleExceptions(Exception e) {
        printSeparator();
        if (e instanceof MissingDescriptionException) {
            System.out.println("    Dobby thinks master should add a description here!");
        } else if (e instanceof IllegalInputException) {
            System.out.println("    Dobby doesn't understand master's command!");
        } else if (e instanceof TaskAlreadyMarkedException) {
            System.out.println("    Dobby says master's task is already marked!");
        } else if (e instanceof TaskAlreadyUnmarkedException) {
            System.out.println("    Dobby says master's task is already unmarked!");
        } else if (e instanceof EmptyListException) {
            System.out.println("    Dobby says master's list is empty!");
        } else if (e instanceof IndexOutOfBoundsException) {
            System.out.println("    Dobby says master's list is full!");
        }
        printSeparator();
    }

    private static void processCommand (String line)
            throws MissingDescriptionException, IllegalInputException, TaskAlreadyMarkedException,
                TaskAlreadyUnmarkedException, EmptyListException {

        if (line.equals("list")) {
            printList();
        } else if (line.startsWith("mark ")) {
            markTaskAsDone(line);
        } else if (line.startsWith("unmark ")) {
            unmarkTaskAsDone(line);
        } else if (isValidAddTaskCommand(line)) {
            addTask(line);
        } else if (line.startsWith("delete ")) {
            deleteTask(line);
        } else {
            throw new IllegalInputException();
        }

    }

    private static void deleteTask(String line) {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task t = taskList.get(taskNumber - 1);
            taskList.remove(t);
            printSeparator();
            System.out.println("    Dobby is removing this task:");
            System.out.println("        " + t);
            System.out.println("    Dobby says master has " + taskList.size() + " remaining tasks!");
            printSeparator();
            saveTasks();
        } else {
            printSeparator();
            System.out.println("    Dobby says that task number does not exist!");
            printSeparator();
        }

    }

    private static boolean isValidAddTaskCommand(String line) {
        return line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event");
    }

    private static void printWelcomeMessage() {
        printSeparator();
        System.out.println("    " + "Hello! Dobby is Dobby!");
        System.out.println("    " + "What can Dobby do for master?");
        printSeparator();
    }

    private static void printList() throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }

        printSeparator();
        System.out.println("    Here are the tasks in master's list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i-1);
            System.out.println("    " + i + "." + t);
        }
        printSeparator();
    }

    private static void addTask (String line) throws MissingDescriptionException {
        Task task = createTask(line);
        if (task != null) {
            taskList.add(task);
            printTaskAddedMessage();
            saveTasks();
        }
    }

    private static Task createTask(String line) throws MissingDescriptionException {
        if (line.startsWith("todo")) {
            if (line.length() <= "todo".length()) {
                throw new MissingDescriptionException();
            }
            return new Todo(line.substring("todo ".length()));
        } else if (line.startsWith("deadline")) {
            return createDeadlineTask(line);
        } else if (line.startsWith("event")){
            return createEventTask(line);
        }
        return new Task(line);
    }

    private static Deadline createDeadlineTask(String line) throws MissingDescriptionException {
        String[] lineParts = line.split(" /by ");

        if (lineParts.length < 2 || lineParts[0].length() <= "deadline ".length()) {
            throw new MissingDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("deadline ", "");
        String byWhen = lineParts[1];
        return new Deadline(taskDescription, byWhen);
    }

    private static Event createEventTask(String line) throws MissingDescriptionException {
        String[] lineParts = line.split(" /from | /to ");

        if (lineParts.length < 3 || lineParts[0].length() <= "event ".length()) {
            throw new MissingDescriptionException();
        }

        String taskDescription = lineParts[0].replaceFirst("event ", "");
        String fromTime = lineParts[1];
        String toTime = lineParts[2];
        return new Event(taskDescription, fromTime, toTime);
    }

    private static void printTaskAddedMessage() {
        printSeparator();
        System.out.println("    Dobby has added this task:");
        System.out.println("      " + taskList.get(taskList.size()-1));
        System.out.println("    Dobby says master has " + taskList.size() + " tasks in the list!");
        printSeparator();
    }

    private static void printGoodbyeMessage() {
        printSeparator();
        System.out.println("    " + "Thank you master, Dobby is free!!!");
        printSeparator();
    }

    private static void markTaskAsDone(String line) throws TaskAlreadyMarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)){
            Task task = taskList.get(taskNumber-1);
            if (task.isDone()){
                throw new TaskAlreadyMarkedException();
            }
            task.markAsDone();
            printTaskStatus("done", taskNumber);
            saveTasks();
        }
    }

    private static void unmarkTaskAsDone(String line) throws TaskAlreadyUnmarkedException {
        int taskNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
        if (isValidTaskNumber(taskNumber)) {
            Task task = taskList.get(taskNumber-1);
            if (!task.isDone()){
                throw new TaskAlreadyUnmarkedException();
            }
            task.unmarkAsDone();
            printTaskStatus("incomplete", taskNumber);
            saveTasks();
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskList.size();
    }

    private static void printTaskStatus(String status, int taskNumber) {
        printSeparator();
        if ("done".equals(status)) {
            System.out.println("    Dobby has magically marked this task as done:");
        } else if ("incomplete".equals(status)) {
            System.out.println("    Dobby has marked this task as incomplete:");
        }
        System.out.println("      " + taskList.get(taskNumber-1));
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("  " + DASH_LINE);
    }
}
