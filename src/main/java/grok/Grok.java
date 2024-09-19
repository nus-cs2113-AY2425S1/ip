package grok;

import grok.tasks.Task;
import grok.tasks.Todo;
import grok.tasks.Deadline;
import grok.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Grok {
    private static final String FILE_PATH = "./data/grok.txt";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println("Hello, I am Grok! Your favourite personal assistant that helps you keep track of tasks :)");
        System.out.println("Here are the list of things Grok can do for you:");
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2pm /to 4pm]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2pm]");
        System.out.println("4. Type either mark or unmark and the task number to indicate completion of task");
        System.out.println("5. Type delete followed by the task number to remove a task from your list");
        System.out.println("6. Type list to view your list of tasks.");
        System.out.println("7. Type bye to exit the programme");
        printLine();

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    printLine();
                    System.out.println("Bye. Hope to see you again soon! Keep Grokking :)");
                    printLine();
                    saveTasksToFile();
                    break;
                } else if (input.equals("list")) {
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    printLine();
                } else if (input.startsWith("mark")) {
                    String taskNumberStr = input.substring(4).trim();
                    if (taskNumberStr.isEmpty()) {
                        throw new GrokException("Oh no, mark must be followed by a task number. Please try again!");
                    }
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    tasks[taskNumber].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskNumber]);
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("unmark")) {
                    String taskNumberStr = input.substring(6).trim();
                    if (taskNumberStr.isEmpty()) {
                        throw new GrokException("Oh no, unmark must be followed by a task number. Please try again!");
                    }
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    tasks[taskNumber].markAsNotDone();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskNumber]);
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("todo")) {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new GrokException("Oh no, todo cannot be empty. Do something!");
                    }
                    tasks[taskCount] = new Todo(description);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("deadline")) {
                    String[] details = input.substring(8).trim().split(" /by ");
                    if (details.length < 2) {
                        throw new GrokException("Invalid deadline command. Please use: deadline <description> /by <date/time>");
                    }
                    tasks[taskCount] = new Deadline(details[0], details[1]);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("event")) {
                    String[] details = input.substring(5).trim().split(" /from | /to ");
                    if (details.length < 3) {
                        throw new GrokException("Invalid event command. Please use: event <description> /from <start> /to <end>");
                    }
                    tasks[taskCount] = new Event(details[0], details[1], details[2]);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    printLine();
                    saveTasksToFile();
                } else if (input.startsWith("delete")) {
                    String taskNumberStr = input.substring(6).trim();
                    if (taskNumberStr.isEmpty()) {
                        throw new GrokException("Oh no, delete must be followed by a task number. Please try again!");
                    }
                    int taskNumber = Integer.parseInt(taskNumberStr) - 1;
                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new GrokException("Invalid task number. Please enter a number within the range.");
                    }
                    printLine();
                    System.out.println("Okay, I've removed this task:");
                    System.out.println(tasks[taskNumber]);
                    // Shift tasks to fill the gap
                    for (int i = taskNumber; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    taskCount--;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    saveTasksToFile();
                } else {
                    throw new GrokException("I'm sorry, I don't grok that command. Please try again :(");
                }
            } catch (GrokException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            } catch (NumberFormatException e) {
                printLine();
                System.out.println("Invalid task number command. Please enter a valid number.");
                printLine();
            }
        }

        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void saveTasksToFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskCount; i++) {
                fileWriter.write(tasks[i].toSaveFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    private static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskLine = fileScanner.nextLine();
                String[] taskDetails = taskLine.split(" \\| ");
                String taskType = taskDetails[0];
                boolean isDone = taskDetails[1].equals("1");

                if (taskType.equals("T")) {
                    tasks[taskCount] = new Todo(taskDetails[2]);
                } else if (taskType.equals("D")) {
                    tasks[taskCount] = new Deadline(taskDetails[2], taskDetails[3]);
                } else if (taskType.equals("E")) {
                    tasks[taskCount] = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                }

                if (isDone) {
                    tasks[taskCount].markAsDone();
                }
                taskCount++;
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        }
    }
}
