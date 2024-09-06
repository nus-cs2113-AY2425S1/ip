import java.util.Scanner;
import java.util.ArrayList;

public class bro {

    private static final ArrayList<Task> storer = new ArrayList<>();
    private static final ArrayList<String> mark_tracker = new ArrayList<>();

    public static void level0() {
        System.out.println("Hello! I'm bro");
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo() {

        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();

            if (line.equals("Bye")) {
                break;
            }
            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void addList() {

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();

            if (line.equals("Bye")) {
                break;

            } else if (line.equals("list")) {


                for (int i = 0; i < storer.size(); i++) {
                    System.out.println((i + 1) + ". " + storer.get(i));
                }

            } else if (line.startsWith("todo")) {
                String description = line.substring(5);
                Todo todo = new Todo(description);
                storer.add(todo);
                System.out.println("Got it. I've added this task\n " + todo);
                System.out.println("Now you have " + storer.size() + " tasks in the list.");

            } else if (line.startsWith("deadline")) {
                String[] infos = line.substring(9).split(" /by ");
                Deadline deadline = new Deadline(infos[0], infos[1]);
                storer.add(deadline);
                System.out.println("Got it. I've added this task\n " + deadline);
                System.out.println("Now you have " + storer.size() + " tasks in the list.");

            } else if (line.startsWith("event")) {
                String[] infos = line.substring(6).split(" /from | /to ");
                Event event = new Event(infos[0], infos[1], infos[2]);
                storer.add(event);
                System.out.println("Got it. I've added this task\n " + event);
                System.out.println("Now you have " + storer.size() + " tasks in the list.");

            } else {
                storer.add(new Task(line));
            }

        }

        System.out.println("Bye. Hope to see you again soon!");


    }

    public static void mark() {

//      track if the current job is marked or unmarked
        for (int i = 0; i < storer.size(); i++) {
//            System.out.println((i + 1) + ". " + storer.get(i));
            mark_tracker.add("[]");
        }

        String line;
        Scanner in = new Scanner(System.in);

        while(true) {

            line = in.nextLine();
            String[] split_line = line.split(" ");

            if (line.equals("list")) {

                for (int i = 0; i < storer.size(); i++) {
                    System.out.println((i + 1) + ". " + mark_tracker.get(i) + " " + storer.get(i));
                }

            } else if (split_line[0].equals("mark")) {

                int task_num = Integer.parseInt(split_line[1]) - 1;
                mark_tracker.set(task_num, "[X]");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + storer.get(task_num));

            } else if (split_line[0].equals("unmark")) {
                int task_num = Integer.parseInt(split_line[1]) - 1;
                mark_tracker.set(task_num, "[]");
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("[] " + storer.get(task_num));
            }
        }


    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

//        level0();
//        echo();
        addList();
        mark();

    }
}
