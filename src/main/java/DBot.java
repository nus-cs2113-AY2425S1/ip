import java.util.*;

public class DBot {
    private static boolean isOn;
    private static List<Task> datas;

    public static void main(String[] args) {
        isOn = true;
        datas = new ArrayList<>();

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm DBot\nWhat can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);

        while (isOn) {
            System.out.print("Command: ");
            String line = in.nextLine().strip();
            System.out.println("____________________________________________________________");

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

            System.out.println("____________________________________________________________");
        }
    }

    private static void add(String line) {
        datas.add(new Task(line));
        System.out.print("added: ");
        System.out.println(line);
    }

    private static void list() {
        for (int i = 0; i < datas.size(); i++) {
            System.out.printf("%d.", i + 1);
            System.out.println(datas.get(i).toString());
        }
    }

    private static void mark(String line) {
        try {
            int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            System.out.println("Nice! I've marked this task as done:");
            datas.get(option - 1).mark();
            System.out.println(datas.get(option - 1).toString());
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void unmark(String line) {
        try {
            int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
            System.out.println("OK, I've marked this task as not done yet:");
            datas.get(option - 1).unmark();
            System.out.println(datas.get(option - 1).toString());
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void todo(String line) {
        String todo = line.substring(line.indexOf(" ") + 1).trim();
        Todo task =new Todo(todo);
        datas.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + datas.size() + " tasks in the list.");
    }

    private static void deadline(String line) {
        String deadlinePrompt = line.substring(line.indexOf(" ") + 1).trim();
        Hashtable<String, String> arguments = Utilities.getCommandArgument(deadlinePrompt);
        String deadline = deadlinePrompt.substring(0, deadlinePrompt.indexOf("/")).trim();
        Deadline task = new Deadline(deadline, arguments.get("by"));
        datas.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + datas.size() + " tasks in the list.");
    }

    private static void event(String line) {
        String eventPrompt = line.substring(line.indexOf(" ") + 1).trim();
        Hashtable<String, String> argument = Utilities.getCommandArgument(eventPrompt);
        String event = eventPrompt.substring(0, eventPrompt.indexOf("/")).trim();
        Event task = new Event(event, argument.get("from"), argument.get("to"));
        datas.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + datas.size() + " tasks in the list.");
    }
}
