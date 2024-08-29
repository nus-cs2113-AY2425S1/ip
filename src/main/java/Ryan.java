import java.util.Scanner;
import java.util.ArrayList;

public class Ryan {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        Utils.horizontalLine();
        System.out.println("Hello! I'm Ryan\nWhat can I do for you?");
        Utils.horizontalLine();

        while (!exit) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                exit = true;
                Utils.horizontalLine();
            } else if (command.equals("tasks")) {
                Utils.horizontalLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                Utils.horizontalLine();
            } else if (command.startsWith("mark")){
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                if (index >= 0 && index <= tasks.size()){
                    tasks.get(index).mark();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index));
                } else {
                    System.out.println("Invalid task number.");
                }
                Utils.horizontalLine();
            } else {
                Utils.horizontalLine();
                Task task = new Task(command);
                tasks.add(task);
                System.out.println("added: " + command);
                Utils.horizontalLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        Utils.horizontalLine();
    }
}
