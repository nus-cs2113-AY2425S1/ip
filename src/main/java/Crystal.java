import java.util.Scanner;
import java.util.ArrayList;

public class Crystal {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("____________________________________________________________\n"
                + "Hola! I'm Crystal.\n"
                + "What can I do for you today?\n"
                + "____________________________________________________________\n");
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("todo")){
                String description = line.substring(5).trim();
                Task t =  new Todo(description);
                tasks.add(t);
                System.out.println("____________________________________________________________\n"
                        + "Got it! I have added this task:\n"
                        + t + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.\n"
                        + "____________________________________________________________");

            } else if (line.startsWith("deadline")) {
                String[] twoParts = line.substring(9).trim().split(" /by ");
                String description = twoParts[0];
                String by = twoParts[1];
                Task t = new Deadline(description, by);
                tasks.add(t);
                System.out.println("____________________________________________________________\n"
                        + "OK, I have added this task:\n"
                        + t + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.\n"
                        + "____________________________________________________________");
            } else if(line.startsWith("event")) {
                String[] threeParts = line.substring(6).trim().split(" /from | /to ");
                String description = threeParts[0];
                String from = threeParts[1];
                String to = threeParts[2];
                Task t = new Event(description, from, to);
                tasks.add(t);
                System.out.println("____________________________________________________________\n"
                        + "Alright, I have added this task:\n"
                        + t + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.\n"
                        + "____________________________________________________________");
            } else if (line.startsWith("mark")) {
                int taskNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task t = tasks.get(taskNumber);
                t.markAsDone();
                System.out.println("____________________________________________________________\n"
                        + "YAY!! This task is now marked done:\n  "
                        + t + "\n"
                        + "____________________________________________________________");
            } else if (line.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task t = tasks.get(taskNumber);
                t.unmark();
                System.out.println("____________________________________________________________\n"
                        + "OK, I've marked this task as not done yet:\n  "
                        + t + "\n"
                        + "____________________________________________________________");
            }
            line = in.nextLine();

        }
        System.out.println("____________________________________________________________\n"
                           + "Adios, hasta luego!\n"
                           + "____________________________________________________________");

    }
}
