import java.util.Scanner;
import java.util.ArrayList;

public class bro {

    private static final ArrayList<Task> storer = new ArrayList<>();

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

                if (line.trim().length() <= 4) {
                    System.out.println("Description of a todo cannot be empty. Please provide a task description.");
                } else {
                    String description = line.substring(5);
                    Todo todo = new Todo(description);
                    storer.add(todo);
                    System.out.println("Got it. I've added this task\n " + todo);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                }

            } else if (line.startsWith("deadline")) {
                try {
                    String[] infos = line.substring(9).split(" /by ");
                    if (infos.length < 2) {
                        throw new Exception("Deadline description / date missing.");
                    }
                    Deadline deadline = new Deadline(infos[0], infos[1]);
                    storer.add(deadline);
                    System.out.println("Got it. I've added this task\n " + deadline);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            } else if (line.startsWith("event")) {
                try {
                    String[] infos = line.substring(6).split(" /from | /to ");
                    if (infos.length < 3) {
                        throw new Exception("Event description / date missing.");
                    }
                    Event event = new Event(infos[0], infos[1], infos[2]);
                    storer.add(event);
                    System.out.println("Got it. I've added this task\n " + event);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            } else if (line.startsWith("mark")) {

                int task_num = Integer.parseInt(line.split(" ")[1]) - 1;
                Task task  = storer.get(task_num);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(storer.get(task_num));

            } else if (line.startsWith("unmark")) {
                int task_num = Integer.parseInt(line.split(" ")[1]) - 1;
                Task task  = storer.get(task_num);
                task.markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(storer.get(task_num));
            }  else {
                storer.add(new Task(line));
            }

        }

        System.out.println("Bye. Hope to see you again soon!");


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
//        mark();

    }
}
