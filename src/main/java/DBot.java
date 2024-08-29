import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isOn = false;
            } else if (line.equals("list")) {
                for (int i = 0; i < datas.size(); i++) {
                    System.out.printf("%d.", i + 1);
                    System.out.println(datas.get(i).toString());
                }
            } else if (line.startsWith("mark ")) {
                int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                System.out.println("Nice! I've marked this task as done:");
                datas.get(option - 1).mark();
                System.out.println(datas.get(option - 1).toString());
            } else if (line.startsWith("unmark ")) {
                int option = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                System.out.println("OK, I've marked this task as not done yet:");
                datas.get(option - 1).unmark();
                System.out.println(datas.get(option - 1).toString());
            } else {
                datas.add(new Task(line));
                System.out.print("added: ");
                System.out.println(line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
