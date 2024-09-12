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
        System.out.println("Hello! I'm Grok");
        System.out.println("What can I do for you?");
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
                } else if (input.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks[taskNumber].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskNumber]);
                    printLine();
                } else if (input.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks[taskNumber].markAsNotDone();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskNumber]);
                    printLine();
                } else if (input.startsWith("todo ")) {
                    tasks[taskCount] = new Todo(input.substring(5)); // remove "todo " prefix
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                } else if (input.startsWith("deadline ")) {
                    String[] details = input.substring(9).split(" /by ");
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
                } else if (input.startsWith("event ")) {
                    String[] details = input.substring(6).split(" /from | /to ");
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
                } else {
                    throw new GrokException("I'm sorry, I don't understand that command. Please try again");
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
