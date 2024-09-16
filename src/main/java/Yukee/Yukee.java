package Yukee;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import Yukee.task.Task;
import Yukee.task.Todo;
import Yukee.task.Deadline;
import Yukee.task.Event;
import Yukee.exception.YukeeException;
import Yukee.exception.EmptyDescriptionException;
import Yukee.exception.InvalidCommandException;

public class Yukee {
    private static final String FILE_PATH = "./data/duke.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = """
                 Y   Y  U   U  K   K  EEEEE  EEEEE
                  Y Y   U   U  K  K   E      E
                   Y    U   U  KKK    EEEE   EEEE
                   Y    U   U  K  K   E      E
                   Y     UUU   K   K  EEEEE  EEEEE
                """;
        System.out.println("Hello! I'm Yukee, your friendly assistant!\n" + logo);

        loadTasksFromFile();

        System.out.println("What can I do for you today?");

        while (true) {
            try {
                System.out.print("Enter command: ");
                String input = scanner.nextLine().trim();
                String[] inputSplit = input.split(" ", 2);
                String command = inputSplit[0].toLowerCase();

                switch (command) {
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        return;

                    case "list":
                        if (tasks.isEmpty()) {
                            System.out.println("Your task list is empty! Try adding some tasks!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + tasks.get(i));
                            }
                        }
                        break;

                    case "mark":
                        try {
                            int index = Integer.parseInt(inputSplit[1]) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                tasks.get(index).markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println("  " + tasks.get(index));
                                saveTasksToFile();
                            } else {
                                System.out.println("Invalid task number. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command. Usage: mark <task_number>");
                        }
                        break;

                    case "unmark":
                        try {
                            int index = Integer.parseInt(inputSplit[1]) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                tasks.get(index).markAsNotDone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println("  " + tasks.get(index));
                                saveTasksToFile();
                            } else {
                                System.out.println("Invalid task number. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command. Usage: unmark <task_number>");
                        }
                        break;

                    case "delete":
                        try {
                            int index = Integer.parseInt(inputSplit[1]) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                Task removedTask = tasks.remove(index);
                                System.out.println("Noted. I've removed this task:");
                                System.out.println("  " + removedTask);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                saveTasksToFile(); // 删除任务后保存到文件
                            } else {
                                System.out.println("Invalid task number. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command. Usage: delete <task_number>");
                        }
                        break;

                    case "todo":
                        if (inputSplit.length < 2 || inputSplit[1].trim().isEmpty()) {
                            throw new EmptyDescriptionException("todo");
                        }
                        tasks.add(new Todo(inputSplit[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        saveTasksToFile();
                        break;

                    case "deadline":
                        if (inputSplit.length < 2 || inputSplit[1].trim().isEmpty()) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        String[] deadlineParts = inputSplit[1].split(" /by ");
                        tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        saveTasksToFile();
                        break;

                    case "event":
                        if (inputSplit.length < 2 || inputSplit[1].trim().isEmpty()) {
                            throw new EmptyDescriptionException("event");
                        }
                        String[] eventParts = inputSplit[1].split(" /from | /to ");
                        tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        saveTasksToFile();
                        break;

                    case "help":
                        System.out.println("Here are the commands you can use:");
                        System.out.println("1. 'list' - List all tasks.");
                        System.out.println("2. 'mark <task_number>' - Mark a task as done.");
                        System.out.println("3. 'unmark <task_number>' - Mark a task as not done.");
                        System.out.println("4. 'todo <task_description>' - Add a todo task.");
                        System.out.println("5. 'deadline <task_description> /by <time>' - Add a deadline task.");
                        System.out.println("6. 'event <task_description> /from <start_time> /to <end_time>' - Add an event task.");
                        System.out.println("7. 'delete <task_number>' - Delete a task from the list.");
                        System.out.println("8. 'hello' or 'hi' - Greet Yukee.");
                        System.out.println("9. 'bye' - Exit the program.");
                        break;

                    case "hello":
                    case "hi":
                        System.out.println("Hello there! I'm Yukee, your personal assistant. I can help you manage tasks. Just let me know what you'd like to do! (✿◠‿◠)");
                        break;

                    default:
                        throw new InvalidCommandException();
                }
            } catch (YukeeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("No existing data found. Starting with an empty task list.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (type) {
                    case "T":
                        Task todo = new Todo(parts[2]);
                        if (isDone) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(parts[2], parts[3]);
                        if (isDone) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(parts[2], parts[3], parts[4]);
                        if (isDone) event.markAsDone();
                        tasks.add(event);
                        break;
                    default:
                        System.out.println("Error loading task from file. Task type not recognized.");
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading data from file. Starting with an empty task list.");
        } catch (Exception e) {
            System.out.println("Data file is corrupted. Starting with an empty task list.");
        }
    }

    private static void saveTasksToFile() {
        File file = new File(FILE_PATH);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : tasks) {
                String taskType = "";
                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                } else if (task instanceof Event) {
                    taskType = "E";
                }

                String isDone = task.isDone() ? "1" : "0";
                String taskLine = taskType + " | " + isDone + " | " + task.toString().split("] ")[1];

                if (task instanceof Deadline) {
                    taskLine += " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    taskLine += " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
                }

                writer.write(taskLine);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}