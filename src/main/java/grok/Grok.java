package grok;

import grok.tasks.Task;
import grok.tasks.Todo;
import grok.tasks.Deadline;
import grok.tasks.Event;

import java.util.Scanner;

public class Grok {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

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
                } else if (input.startsWith("todo")) {
                    String description = input.substring(4).trim(); // Adjusted for any missing spaces
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
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
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
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks[taskNumber]);
                    // Shift tasks to fill the gap
                    for (int i = taskNumber; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    taskCount--;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
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
}
