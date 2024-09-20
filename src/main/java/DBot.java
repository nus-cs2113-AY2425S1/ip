import jdk.jshell.execution.Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;

public class DBot {
    private static boolean isOn;
    private static List<Task> taskList;

    public static void main(String[] args) {
        isOn = true;
        taskList = Utilities.loadData();

        final String BREAK_LINE = "____________________________________________________________";
        final String GREETING_LINE = BREAK_LINE + "\nHello! I'm DBot\nWhat can I do for you?\n" + BREAK_LINE;
        System.out.println(GREETING_LINE);

        Scanner in = new Scanner(System.in);

        while (isOn) {
            System.out.print("Command: ");
            String line = in.nextLine().strip();
            System.out.println(BREAK_LINE);

            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isOn = false;
            } else if (line.equals("list")) {
                list();
            } else if (line.startsWith("mark ")) {
                mark(line);
            } else if (line.startsWith("unmark ")) {
                unmark(line);
            } else if (line.startsWith("todo ")) {
                todo(line);
            } else if (line.startsWith("event ")) {
                event(line);
            } else if (line.startsWith("deadline ")) {
                deadline(line);
            } else if (line.startsWith("delete ")) {
                delete(line);
            } else {
                System.out.println("Unknown command: " + line);
            }

            boolean saveSuccess = Utilities.saveData(taskList);
            if (!saveSuccess) {
                System.out.println("Critical error: Cannot save data");
            }
            System.out.println(BREAK_LINE);
        }
    }

    private static void list() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.", i + 1);
            System.out.println(taskList.get(i).toString());
        }
    }

    private static void mark(String line) {
        try {
            int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(option - 1).mark();
            System.out.println(taskList.get(option - 1).toString());
        } catch (Exception e) {
            System.out.println("Invalid input, input must be a positive integer and must exist");
        }
    }

    private static void unmark(String line) {
        try {
            int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            System.out.println("OK, I've marked this task as not done yet:");
            taskList.get(option - 1).unmark();
            System.out.println(taskList.get(option - 1).toString());
        } catch (Exception e) {
            System.out.println("Invalid input, input must be a positive integer and must exist");
        }
    }

    private static void todo(String line) {
        String todo = line.substring(line.indexOf(" ") + 1).trim();
        Todo task = new Todo(todo);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void deadline(String line) {
        String deadlinePrompt = line.substring(line.indexOf(" ") + 1).trim();
        Hashtable<String, String> arguments;
        try {
            arguments = Utilities.getCommandArgument(deadlinePrompt);
        } catch (Exception e) {
            System.out.println("Invalid input. Please do not use '/' outside of providing special arguments as it's a special character");
            return;
        }
        String deadline = deadlinePrompt.substring(0, deadlinePrompt.indexOf("/")).trim();
        Deadline task;
        try {
            task = new Deadline(deadline, arguments.get("by"));
        } catch (Exception e) {
            System.out.println("Invalid input, deadline missing argument '/by'");
            return;
        }

        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void event(String line) {
        String eventPrompt = line.substring(line.indexOf(" ") + 1).trim();
        Hashtable<String, String> argument;
        try {
            argument = Utilities.getCommandArgument(eventPrompt);
        } catch (Exception e) {
            System.out.println("Invalid input. Please do not use '/' outside of providing special arguments as it's a special character");
            return;
        }
        String event = eventPrompt.substring(0, eventPrompt.indexOf("/")).trim();
        Event task;
        try {
            task = new Event(event, argument.get("from"), argument.get("to"));
        } catch (Exception e) {
            System.out.println("Invalid input, event missing argument(s) '/from' or '/to'");
            return;
        }
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void delete(String line) {
        try {
            int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            System.out.println("Nice! I've marked this task as done:");
            Task removed = taskList.remove(option - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Invalid input, input must be a positive integer and must exist");
        }
    }
}
