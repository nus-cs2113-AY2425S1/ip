package aegis;

import aegis.task.Task;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Aegis {

    private final static String AEGIS_LOGO = """
                                                                            \s
                        **                                                  \s
                     *****                                  *               \s
                    *  ***                                 ***              \s
                       ***                                  *               \s
                      *  **                                          ****   \s
                      *  **          ***        ****      ***       * **** *\s
                     *    **        * ***      *  ***  *   ***     **  **** \s
                     *    **       *   ***    *    ****     **    ****      \s
                    *      **     **    ***  **     **      **      ***     \s
                    *********     ********   **     **      **        ***   \s
                   *        **    *******    **     **      **          *** \s
                   *        **    **         **     **      **     ****  ** \s
                  *****      **   ****    *  **     **      **    * **** *  \s
                 *   ****    ** *  *******    ********      *** *    ****   \s
                *     **      **    *****       *** ***      ***            \s
                *                                    ***                    \s
                 **                            ****   ***                   \s
                                             *******  **                    \s
                                            *     ****                      \s
                                                                            \s""";

    private final static String SEPARATOR = "--------------------------------------------------";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(SEPARATOR);
        System.out.println("Hello, this is\n" + AEGIS_LOGO);

        System.out.println(SEPARATOR);
        System.out.println("Hello! This is Aegis, an Anti-Shadow Suppression Weapon and a chatbot.");
        System.out.println("What can I do for you?");

        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("---------------BEGIN--OF--PROMPT------------------");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            handleCommand(input, taskList);
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println();
        System.out.println("-------------END--OF--CONVERSATION----------------");

        scanner.close();
    }

    private static void handleCommand(String input, ArrayList<Task> taskList) {
        try {
            String command = input.split(" ")[0].toLowerCase();
            switch (command) {
            case "list":
                displayTaskList(taskList);
                break;
            case "mark":
                markTaskAsDone(taskList, input);
                break;
            case "unmark":
                unmarkTaskAsDone(taskList, input);
                break;
            case "deadline":
                addDeadline(taskList, input);
                break;
            case "event":
                addEvent(taskList, input);
                break;
            case "todo":
                addTodoTask(taskList, input);
                break;
            default:
                throw new AegisException(" Your command has not been authorized ");
            }
        } catch (AegisException e) {
            System.out.printf(" Anomaly detected: %s%n", e.getMessage());
        }
    }

    private static void displayTaskList(ArrayList<Task> taskList) {
        System.out.println(" I could do much more than checking your list, but, if you wish:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("   %d. %s%n", i + 1, taskList.get(i));
        }
    }

    private static void markTaskAsDone(ArrayList<Task> taskList, String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new AegisException("Invalid task number");
            }
            taskList.get(taskIndex).markAsDone();
            System.out.printf(" I've marked this task as done:%n   %s%n", taskList.get(taskIndex));
        } catch (AegisException | NumberFormatException e) {
            System.out.printf(" Anomaly detected: [%s]%n", e.getMessage());
        }
    }

    private static void unmarkTaskAsDone(ArrayList<Task> taskList, String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new AegisException("Invalid task number");
            }
            taskList.get(taskIndex).unmarkAsDone();
            System.out.printf(" I've marked this task as not done yet:%n   %s%n", taskList.get(taskIndex));
        } catch (AegisException | NumberFormatException e) {
            System.out.printf(" Anomaly detected: [%s]%n", e.getMessage());
        }
    }

    private static void addDeadline(ArrayList<Task> taskList, String input) {
        try {
            if (!input.contains(" /by ")) {
                throw new AegisException("Invalid syntax: you need to include [/by] parameter");
            }

            String[] parts = input.split(" /by ", 2);
            String description = parts[0].replaceFirst("deadline ", "").trim();
            String byTime = parts[1].trim();

            Deadline newDeadline = new Deadline(description, byTime);
            taskList.add(newDeadline);
            System.out.printf(" New deadline added: %n   %s%n", newDeadline);
            System.out.printf(" %d tasks needed to be done. Let me assist you!%n", taskList.size());
        } catch (AegisException e) {
            System.out.printf(" Anomaly detected: [%s]%n", e.getMessage());
        }
    }

    private static void addEvent(ArrayList<Task> taskList, String input) {
        try {
            if (!input.matches(".* /from .*/to .*")) {
                throw new AegisException("Invalid syntax: you need to include [/from, /to] parameters");
            }

            String[] parts = input.split(" /from | /to ");
            String description = parts[0].replaceFirst("event ", "").trim();
            String fromTime = parts[1].trim();
            String toTime = parts[2].trim();

            Event newEvent = new Event(description, fromTime, toTime);
            taskList.add(newEvent);
            System.out.printf(" New event added: %n   %s%n", newEvent);
            System.out.printf(" %d tasks needed to be done. Let me assist you!%n", taskList.size());
        } catch (AegisException e) {
            System.out.printf(" Anomaly detected: [%s]%n", e.getMessage());
        }
    }

    private static void addTodoTask(ArrayList<Task> taskList, String input) {
        try {
            String description = input.replaceFirst("todo ", "").trim();
            if (description.isEmpty()) {
                throw new AegisException("The description of a todo cannot be empty");
            }
            Todo newTodo = new Todo(description);
            taskList.add(newTodo);
            System.out.printf(" New todo added: %n   %s%n", newTodo);
            System.out.printf(" %d tasks needed to be done. Let me assist you!%n", taskList.size());
        } catch (AegisException e) {
            System.out.printf(" Anomaly detected: [%s]%n", e.getMessage());
        }
    }
}
