package bot;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TobyBot {
    private static final String LINE = "____________________________________________________________";
    private static final String FILE_PATH = "./data/TobyBot.txt";
    private static final ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        loadTasks();

        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.println("Hello! I'm TobyBot");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);

            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("mark ")) {
                    markTask(input);
                } else if (input.startsWith("unmark ")) {
                    unmarkTask(input);
                } else if (input.startsWith("todo ")) {
                    addTodoTask(input.substring(5).trim());
                } else if (input.startsWith("deadline ")) {
                    addDeadlineTask(input.substring(9).trim());
                } else if (input.startsWith("event ")) {
                    addEventTask(input.substring(6).trim());
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else {
                    throw new TobyBotException("I'm sorry. Your input doesn't fit the format of all tasks :-(");
                }
            } catch (TobyBotException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(LINE);

            saveTasks();
            
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        } else {
            try (Scanner s = new Scanner(file)) {
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] parts = line.split(" \\| ");
                    Task task = switch (parts[0]) {
                        case "T" -> new Todo(parts[2]);
                        case "D" -> new Deadline(parts[2], parts[3]);
                        case "E" -> new Event(parts[2], parts[3], parts[4]);
                        default -> null;
                    };

                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

    private static void saveTasks() {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(taskToFileString(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("T");
        } else if (task instanceof Deadline deadline) {
            sb.append("D");
            sb.append(" | ").append(deadline.getBy());
        } else if (task instanceof Event event) {
            sb.append("E");
            sb.append(" | ").append(event.getFrom());
            sb.append(" | ").append(event.getTo());
        }
        sb.insert(1, " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription());
        return sb.toString();
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void addTodoTask(String description) throws TobyBotException {
        if (description.isEmpty()) {
            throw new TobyBotException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String description) throws TobyBotException {
        String[] parts = description.split(" /by ");
        if (parts.length < 2 || parts[0].isEmpty()) {
            throw new TobyBotException("The description or deadline of a task cannot be empty.");
        }
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(String description) throws TobyBotException {
        String[] parts = description.split(" /from | /to ");
        if (parts.length < 3 || parts[0].isEmpty()) {
            throw new TobyBotException("The description or timing of an event cannot be empty.");
        }
        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(String input) throws TobyBotException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new TobyBotException("Invalid task number.");
        }
    }

    private static void markTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.get(taskNumber);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }
}








