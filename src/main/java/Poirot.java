import java.io.*;
import java.util.Scanner;

public class Poirot {
    public static final String LINE = "____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static final String FILE_PATH = "./data/duke.txt";
    private static int last_index = 0;

    public static void echo(String msg) {
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
    }

    public static void print(Task[] tasks) {
        if (last_index == 0) {
            System.out.println(LINE);
            System.out.println("No actions available");
        } else {
            System.out.println(LINE);
            for (int i = 0; i < last_index; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
        System.out.println(LINE);
    }

    public static void add(Task task) {
        tasks[last_index] = task;
        last_index++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + last_index + " tasks in the list.");
        System.out.println(LINE);
        saveTasksToFile();
    }

    public static void saveTasksToFile() {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < last_index; i++) {
                writer.write(tasks[i].toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return; // No file to load from
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                    case "T":
                        add(new Todo(description));
                        break;
                    case "D":
                        String by = parts[3];
                        add(new Deadline(description, by));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        add(new Event(description, from, to));
                        break;
                    default:
                        System.out.println("Corrupted data: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        }
    }

    public static void markTask(int index, boolean isDone) {
        tasks[index].setDone(isDone);
        saveTasksToFile();
    }

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello! I'm POIROT\n");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        loadTasksFromFile();

        boolean working = true;
        Scanner scan = new Scanner(System.in);
        while (working) {
            String input = scan.nextLine();
            String[] list_input = input.split(" ");
            try {
                switch (list_input[0]) {
                    case "list":
                        print(tasks);
                        break;
                    case "bye":
                        working = false;
                        break;
                    case "mark":
                        validateTaskNumber(list_input);
                        int x = Integer.parseInt(list_input[1]) - 1;
                        tasks[x].setDone(true);
                        System.out.println(LINE);
                        System.out.println("Nice! I've marked this task as done:\n");
                        System.out.print("[" + tasks[x].getStatusIcon() + "] ");
                        System.out.println(tasks[x].getDescription());
                        System.out.println(LINE);
                        saveTasksToFile(); // Save the task after marking it
                        break;
                    case "unmark":
                        validateTaskNumber(list_input);
                        int y = Integer.parseInt(list_input[1]) - 1;
                        tasks[y].setDone(false);
                        System.out.println(LINE);
                        System.out.println("OK, I've marked this task as not done yet:\n");
                        System.out.print("[" + tasks[y].getStatusIcon() + "] ");
                        System.out.println(tasks[y].getDescription());
                        System.out.println(LINE);
                        saveTasksToFile();
                        break;
                    case "todo":
                        if (input.trim().length() <= 5) {
                            throw new PoirotException("Hey, I don't know what you need to do bro!");
                        }
                        String todoDescription = input.substring(5).trim();
                        add(new Todo(todoDescription));
                        break;
                    case "deadline":
                        if (!input.contains("/by")) {
                            throw new PoirotException("Hey, you haven't set your deadline bro!");
                        }
                        String[] deadlineParts = input.substring(9).split(" /by ");
                        add(new Deadline(deadlineParts[0], deadlineParts[1]));
                        break;
                    case "event":
                        if (!input.contains("/from") || !input.contains("/to")) {
                            throw new PoirotException("Hey, what the hell this event's duration bro?");
                        }
                        String[] eventParts = input.substring(6).split(" /from ");
                        String[] timeParts = eventParts[1].split(" /to ");
                        add(new Event(eventParts[0], timeParts[0], timeParts[1]));
                        break;
                    default:
                        throw new PoirotException("What the hell you wanna say?");
                }
            } catch (PoirotException e) {
                echo(e.getMessage());
            } catch (Exception e) {
                echo("Something went wrong");
            }
        }
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void validateTaskNumber(String[] list_input) throws PoirotException {
        if (list_input.length < 2) {
            throw new PoirotException("Task number is missing. Please provide a valid task number.");
        }
        int index = Integer.parseInt(list_input[1]) - 1;
        if (index < 0 || index >= last_index) {
            throw new PoirotException("Task number is out of range.");
        }
    }
}
