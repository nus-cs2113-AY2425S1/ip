import java.util.Scanner;
import java.util.ArrayList;

public class Crystal {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Crystal.
                What can I do for you today?
                ____________________________________________________________
                """);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("mark")){
                int taskNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task t = tasks.get(taskNumber);
                t.markAsDone();
                System.out.println("____________________________________________________________\n"
                        + "Great! This is now done:\n"
                        + t + "\n"
                        + "____________________________________________________________");

            } else if (line.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task t = tasks.get(taskNumber);
                t.unmark();
                System.out.println("____________________________________________________________\n"
                        + "OK, I have marked this task as not done yet:\n"
                        + t + "\n"
                        + "____________________________________________________________");
            } else {
                Task t = new Task(line);
                tasks.add(t);
                System.out.println("____________________________________________________________\n"
                        + "added: " + line + "\n"
                        + "____________________________________________________________");
            }
            line = in.nextLine();

        }
        System.out.println("____________________________________________________________\n"
                + "Bye, hope to see you again soon!\n"
                + "____________________________________________________________");

    }
}
