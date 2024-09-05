import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;

public class DBot {
    private static boolean isOn;
    private static List<Task> taskList;

    public static void main(String[] args) {
        isOn = true;
        taskList = new ArrayList<>();

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
            } else {
                add(line);
            }

            System.out.println(BREAK_LINE);
        }
    }

    private static void add(String line) {
        taskList.add(new Task(line));
        System.out.print("added: ");
        System.out.println(line);
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
            System.out.println("Invalid input");
        }
    }

    private static void unmark(String line) {
        try {
            int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            System.out.println("OK, I've marked this task as not done yet:");
            taskList.get(option - 1).unmark();
            System.out.println(taskList.get(option - 1).toString());
        } catch (Exception e) {
            System.out.println("Invalid input");
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
        Hashtable<String, String> arguments = Utilities.getCommandArgument(deadlinePrompt);
        String deadline = deadlinePrompt.substring(0, deadlinePrompt.indexOf("/")).trim();
        Deadline task = new Deadline(deadline, arguments.get("by"));
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void event(String line) {
        String eventPrompt = line.substring(line.indexOf(" ") + 1).trim();
        Hashtable<String, String> argument = Utilities.getCommandArgument(eventPrompt);
        String event = eventPrompt.substring(0, eventPrompt.indexOf("/")).trim();
        Event task = new Event(event, argument.get("from"), argument.get("to"));
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
