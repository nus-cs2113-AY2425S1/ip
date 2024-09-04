import java.util.Scanner;

public class Poirot {
    public static final String LINE = "____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
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
    }

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello! I'm POIROT\n");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
        boolean working = true;
        Scanner scan = new Scanner(System.in);
        while (working) {
            String input = scan.nextLine();
            String[] list_input = input.split(" ");
            switch (list_input[0]) {
                case "list":
                    print(tasks);
                    break;
                case "bye":
                    working = false;
                    break;
                case "mark":
                    int x = Integer.parseInt(list_input[1]) - 1;
                    tasks[x].setDone(true);
                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.print("[" + tasks[x].getStatusIcon() + "] ");
                    System.out.println(tasks[x].getDescription());
                    System.out.println(LINE);
                    break;
                case "unmark":
                    int y = Integer.parseInt(list_input[1]) - 1;
                    tasks[y].setDone(false);
                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet:\n");
                    System.out.print("[" + tasks[y].getStatusIcon() + "] ");
                    System.out.println(tasks[y].getDescription());
                    System.out.println(LINE);
                    break;
                case "todo":
                    String todoDescription = input.substring(5).trim();
                    add(new Todo(todoDescription));
                    break;
                case "deadline":
                    String[] deadlineParts = input.substring(9).split(" /by ");
                    add(new Deadline(deadlineParts[0], deadlineParts[1]));
                    break;
                case "event":
                    String[] eventParts = input.substring(6).split(" /from ");
                    String[] timeParts = eventParts[1].split(" /to ");
                    add(new Event(eventParts[0], timeParts[0], timeParts[1]));
                    break;
                default:
                    echo("Invalid command");
                    break;
            }
        }
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}