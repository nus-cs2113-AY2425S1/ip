import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class CheonsaBot {
    public static final int LINE_LENGTH = 60;
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The entry point of the application. Starts bot and listens for user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        getGreeting();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                String userInput = scanner.nextLine();
                running = parseInput(userInput);
            }
        }
    }

    /**
     * Prints a greeting message to the console.
     * Displays the bot's logo and a welcome message.
     */
    private static void getGreeting() {
        String logo = """
                       (\\ -=- /)
                       ( \\( )/ )
                       (       )
                        `>   <'
                        /     \\
                        `-._.-'
                       """;
        System.out.println(getHorizontalLine());
        System.out.println("Hello, I'm 천사봇! (AngelBot)");
        System.out.print(logo);
        System.out.println("How may I assist you today?");
        System.out.println(getHorizontalLine());
    }

    /**
     * Prints a farewell message and exits the program.
     */
    private static void getBye() {
        System.out.println(getHorizontalLine());
        System.out.println("Bye, see you again soon!");
        System.out.println(getHorizontalLine());
    }

    /**
     * Returns a horizontal line of dashes used for formatting console output.
     *
     * @return A string containing the horizontal line.
     */
    private static String getHorizontalLine() {
        return "-".repeat(LINE_LENGTH);
    }

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param userInput The input provided by the user.
     * @return True if the bot should continue running, false if it should exit.
     */
    private static boolean parseInput(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String argument = words.length > 1 ? words[1] : "";

        switch (command) {
            case "mark":
                markTask(argument);
                break;
            case "unmark":
                unmarkTask(argument);
                break;
            case "bye":
                getBye();
                return false;
            case "list":
                printTaskList();
                break;
            case "delete":
                deleteTask(argument);
                break;
            default:
                addTask(command, words);
                break;
        }
        return true;
    }

    /**
     * Marks a task as completed based on the provided task number.
     *
     * @param taskNumber The number of the task to be marked as completed.
     */
    private static void markTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsDone();
                System.out.println(getHorizontalLine());
                System.out.println("Marked task as done: " + tasks.get(index));
                System.out.println(getHorizontalLine());
            } else {
                System.out.println(getHorizontalLine());
                System.out.println("Task number out of range :(");
                System.out.println("Current number of tasks: " + tasks.size());
                System.out.println(getHorizontalLine());
            }
            
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number :(");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Unmarks a task as incomplete based on the provided task number.
     *
     * @param taskNumber The number of the task to be unmarked.
     */
    private static void unmarkTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setAsUndone();
                System.out.println(getHorizontalLine());
                System.out.println("Unmarked task: " + tasks.get(index));
                System.out.println(getHorizontalLine());
            } else {
                System.out.println(getHorizontalLine());
                System.out.println("Task number is out of range :(");
                System.out.println(getHorizontalLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number :(");
            System.out.println(getHorizontalLine());
        }
    }

    /**
     * Adds a new task based on the command and argument provided.
     *
     * @param command The type of task to add (e.g., "todo", "deadline", "event").
     * @param words The details of the task.
     */
    private static void addTask(String command, String[] words) {
        Task task = null;
        
        try {
            switch (command.toLowerCase()) {
                case "todo":
                    if (words.length > 1 && !words[1].trim().isEmpty()) {
                        task = new ToDo(words[1]);
                    } else {
                        throw new IllegalArgumentException("Oh no! The description for 'todo' is missing. :( ");
                    }
                    break;

                case "deadline":
                    if (words.length > 1) {
                        String[] deadlineParts = words[1].split("/by", 2);
                        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                            throw new IllegalArgumentException("Oops! The deadline format is incorrect or missing :( \nUse: deadline <description> /by <date>");
                        }
                        String deadlineDescription = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        task = new Deadline(deadlineDescription, by);
                    } else {
                        throw new IllegalArgumentException("Oops! Missing description for 'deadline' :( ");
                    }
                    break;

                case "event":
                    if (words.length > 1) {
                        String[] eventParts = words[1].split("/from", 2);
                        String eventDescription = eventParts[0].trim();
                        String from = "";
                        String to = "";
                        if (eventParts.length > 1) {
                            String[] timeParts = eventParts[1].split("/to", 2);
                            from = timeParts[0].trim();
                            if (timeParts.length > 1) {
                                to = timeParts[1].trim();
                            }
                        }
                        if (eventDescription.isEmpty() || from.isEmpty() || (to.isEmpty() && eventParts.length == 1)) {
                            throw new IllegalArgumentException("Oops! The event format is incorrect or missing :( \nUse: event <description> /from <start> /to <end>");
                        }
                        task = new Event(eventDescription, from, to);
                    } else {
                        throw new IllegalArgumentException("Oops! Missing description for 'event' :( ");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Oh no! Unknown task type: " + command + " :(\nTry commands todo, deadline, or event with the correct format!");
            }

            if (task != null) {
                tasks.add(task);
                System.out.println(getHorizontalLine());
                System.out.println("Added: " + task);
                System.out.println(getHorizontalLine());
            }

        } catch (IllegalArgumentException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Error: " + e.getMessage());
            System.out.println(getHorizontalLine());
        } catch (Exception e) {
            System.out.println(getHorizontalLine());
            System.out.println("Unexpected error: " + e.getMessage());
            System.out.println(getHorizontalLine());
        }
    }



     /**
     * Prints the list of tasks to the console.
     * Displays each task with its corresponding number.
     */
    private static void printTaskList() {
        System.out.println(getHorizontalLine());
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty, maybe add a task?");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(getHorizontalLine());
    }

    private static void deleteTask(String index) {
        try {
            int taskIndex = Integer.parseInt(index) - 1; // Parse once
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            
            System.out.println(getHorizontalLine());
            System.out.println("Removed: " + tasks.get(taskIndex).getDescription());
            tasks.remove(taskIndex);
            System.out.println("Only " + tasks.size() + " thing(s) left to do!");
            System.out.println(getHorizontalLine());

        } catch (NumberFormatException e) {
            System.out.println(getHorizontalLine());
            System.out.println("Invalid task number :(");
            System.out.println(getHorizontalLine());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(getHorizontalLine());
            System.out.println("That task doesn't exist :( Max is: " + tasks.size());
            System.out.println(getHorizontalLine());
        }
    }
}
