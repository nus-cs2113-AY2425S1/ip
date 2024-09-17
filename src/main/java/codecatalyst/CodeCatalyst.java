package codecatalyst;

import codecatalyst.task.Deadline;
import codecatalyst.task.Event;
import codecatalyst.task.Task;
import codecatalyst.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;


public class CodeCatalyst {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data\\CodeCatalystData";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadTasksFromFile();
        printGreeting();

        while (true) {
            String input = scanner.nextLine();
            printDivider();
            processInput(input, scanner);
            printDivider();
        }
    }


    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("          No previous tasks found. Starting afresh.");
            return;
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] parts = s.nextLine().split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                switch (taskType) {
                case "T":
                    Todo todoTask = new Todo(description);
                    if (isDone) {
                        todoTask.markAsDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    Deadline deadlineTask = new Deadline(description, parts[3]);
                    if (isDone) {
                        deadlineTask.markAsDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    String[] timeParts = parts[3].split("-");
                    String startTime = timeParts[0];
                    String endTime = timeParts[1];

                    Event eventTask = new Event(description, startTime, endTime);
                    if (isDone) {
                        eventTask.markAsDone();
                    }
                    tasks.add(eventTask);
                    break;
                default:
                    System.out.println("Invalid task type: " + taskType);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void saveTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            String CompletionNumber = task.isDone() ? "1" : "0";

            if (task instanceof Todo){
                fw.write("T | " + CompletionNumber + " | " + task.getDescription() + "\n");
            } else if (task instanceof Deadline){
                fw.write("D | " + CompletionNumber + " | " + task.getDescription() + " | " +
                        ((Deadline) task).getBy() + "\n");
            } else if (task instanceof Event){
                fw.write("E | " + CompletionNumber + " | " + task.getDescription() + " | " +
                        ((Event) task).getFrom() + "-" + ((Event) task).getTo() + "\n");
            }
        }
        fw.close();
    }

    /**
     * Process the input and handle the corresponding command.
     *
     * @param input The user input string.
     * @param scanner The scanner object for reading input.
     */
    private static void processInput(String input, Scanner scanner) {
        try {
            String command = getCommand(input);
            switch (command) {
            case "bye":
                printGoodbye();
                break;
            case "list":
                printTaskList();
                break;
            case "mark":
                handleTaskStatusChange(input, true);
                break;
            case "unmark":
                handleTaskStatusChange(input, false);
                break;
            case "todo":
                validateTodoInput(input);
                addTask(new Todo(input.substring(5)));
                break;
            case "deadline":
                validateDeadlineInput(input);
                addDeadlineTask(input);
                break;
            case "event":
                validateEventInput(input);
                addEventTask(input);
                break;
            default:
                throw new CodeCatalystException("Invalid input! Please enter a valid command.");
            }
        } catch (CodeCatalystException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void validateTodoInput(String input) throws CodeCatalystException {
        if (input.trim().length() <= 5) {
            throw new CodeCatalystException("The task description cannot be empty");
        }
    }

    private static void validateDeadlineInput(String input) throws CodeCatalystException {
        String[] deadlineParts = input.substring(9).split(" /by ");
        boolean isTaskDescriptionEmpty = deadlineParts[0].trim().isEmpty();
        boolean isDateDescriptionEmpty = deadlineParts[1].trim().isEmpty();
        if (deadlineParts.length < 2 || isTaskDescriptionEmpty || isDateDescriptionEmpty) {
            throw new CodeCatalystException("The task description or due date of Deadline task cannot be empty.");
        }
    }

    private static void validateEventInput(String input) throws CodeCatalystException {
        String[] eventParts = input.substring(6).split(" /from | /to ");
        boolean isTaskDescriptionEmpty = eventParts[0].trim().isEmpty();
        boolean isStartDateDescriptionEmpty = eventParts[1].trim().isEmpty();
        boolean isEndDateDescriptionEmpty = eventParts[2].trim().isEmpty();
        if (eventParts.length < 3 || isTaskDescriptionEmpty
                || isStartDateDescriptionEmpty || isEndDateDescriptionEmpty) {
            throw new CodeCatalystException("The task description, start date, or end date cannot be empty.");
        }
    }

    private static String getCommand(String input) {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.startsWith("mark")) {
            return "mark";
        } else if (input.startsWith("unmark")) {
            return "unmark";
        } else if (input.startsWith("todo ")) {
            return "todo";
        } else if (input.startsWith("deadline ")) {
            return "deadline";
        } else if (input.startsWith("event")) {
            return "event";
        } else {
            return "invalid";
        }
    }

    private static void printDivider() {
        System.out.println("        ________________________________________________________\n");
    }

    private static void printGreeting() {
        printDivider();
        System.out.println("         Hello, I'm CodeCatalyst.");
        System.out.println("         What can I do for you?");
        printDivider();
    }

    private static void printGoodbye() {
        System.out.println("         Bye. Hope to see you again soon!");
    }

    private static void printTaskList() {
        System.out.println("         Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("         " + (i + 1) + ". " + tasks.get(i));
        }
    }

    private static void handleTaskStatusChange(String input, boolean isMark) {
        int taskNumber = extractTaskNumber(input, isMark);
        if (taskNumber == -1) {
            return;  // Invalid task number, already handled in extractTaskNumber.
        }
        changeTaskStatus(taskNumber, isMark);
        try {
            saveTasksToFile();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");;
        }
    }

    /**
     * Extracts the task number from the input command.
     *
     * @param input The user input command.
     * @param isMark True if the command is to mark a task, false for unmark.
     * @return The valid task number, or -1 if invalid.
     */
    private static int extractTaskNumber(String input, boolean isMark) {
        int prefixLength = isMark ? 5 : 7; // length of "mark " or "unmark "
        try {
            return Integer.parseInt(input.substring(prefixLength));
        } catch (NumberFormatException invalidNumberFormat) {
            System.out.println("         " + input.substring(prefixLength) + " is not a number");
            return -1;
        }
    }

    /**
     * Change the status of the task to done or not done.
     *
     * @param taskNumber The task number to change status.
     * @param isMark True to mark the task as done, false to unmark.
     */
    private static void changeTaskStatus(int taskNumber, boolean isMark) {
        int taskIndex = taskNumber - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            if (isMark) {
                task.markAsDone();
                System.out.println("         Nice! I've marked this task as done:");
            } else {
                task.markAsNotDone();
                System.out.println("         Ok, I've marked this task as not done yet:");
            }
            System.out.println("         " + task);
        } else {
            System.out.println("         Invalid task number.");
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("         Got it. I've added this task:");
        System.out.println("         " + task);
        System.out.println("         Now you have " + tasks.size() + " tasks in the list.");

        try {
            saveTasksToFile();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");;
        }
    }

    private static void addDeadlineTask(String input) {
        String[] parts = input.substring(9).split(" /by ");
        addTask(new Deadline(parts[0], parts[1]));
    }

    private static void addEventTask(String input) {
        String[] parts = input.substring(6).split(" /from | /to ");
        addTask(new Event(parts[0], parts[1], parts[2]));
    }
}
