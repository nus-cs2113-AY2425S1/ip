import java.util.Scanner;

public class Anke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
        String line = "";
        Task[] tasks = new Task[100];
        int count = 0;
        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            Task task = null;
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
                System.out.println("");
                continue;
            } else if (line.length() > 5 && line.substring(0, 5).equals("mark ")) {
                int index;
                try {
                    index = Integer.parseInt(line.substring(5));
                    if (index <= count && index > 0) {
                        tasks[index - 1].setDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks[index - 1].toString() + "\n");
                        continue;
                    }
                }
                catch (NumberFormatException e) {
                }
            } else if (line.length() > 7 && line.substring(0, 7).equals("unmark ")) {
                int index;
                try {
                    index = Integer.parseInt(line.substring(7));
                    if (index <= count && index > 0) {
                        tasks[index - 1].setDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks[index - 1].toString() + "\n");
                        continue;
                    }
                }
                catch (NumberFormatException e) {
                }
            } else if (line.length() > 5 && line.substring(0, 5).equals("todo ")) {
                task = new Todo(line.substring(5));
            } else if (line.length() > 9 && line.substring(0, 9).equals("deadline ")) {
                int byIndex = line.indexOf("/by");
                task = new Deadline(line.substring(9, byIndex - 1), line.substring(byIndex + 4));
            } else if (line.length() > 6 && line.substring(0, 6).equals("event ")) {
                int fromIndex = line.indexOf("/from");
                int toIndex = line.indexOf("/to");
                task = new Event(line.substring(6, fromIndex - 1), line.substring(fromIndex + 6, toIndex - 1), line.substring(toIndex + 4));
            }
            tasks[count] = task;
            count++;
            System.out.println("\nGot it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + count + " tasks in the list.\n");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
