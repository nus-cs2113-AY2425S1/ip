import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DianaAssistant {
    public void interact() {
        System.out.println("Hello, I am Diana!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        String input;
        while (true) {
            input = scanner.nextLine();
            if ("bye".equals(input)) {
                break;
            }
            if ("list".equals(input)) {
                for (int i = 0; i < list.size(); i++) {
                    Task t = list.get(i);
                    System.out.println((i + 1) + ". " + t.toString());
                }
            }
            else if (input.startsWith("mark ")) {
                try {
                    int taskNum = Integer.valueOf(input.substring("mark ".length())) - 1;
                    if (taskNum >= 0 && taskNum < list.size()) {
                        Task task = list.get(taskNum);
                        task.MarkAsDone();
                        System.out.println("Nice! I've marked this task as done\n" + task.toString());

                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number.");
                }
            }

            else if (input.startsWith("unmark ")) {
                try {
                    int taskNum = Integer.valueOf(input.substring("unmark ".length())) - 1;

                    if (taskNum >= 0 && taskNum < list.size()) {
                        Task task = list.get(taskNum);
                        task.MarkAsNotDone();
                        System.out.println("Okay, I've makred this task as not done yet\n" + task.toString());
                    }
                    else {
                        System.out.println("Invalid task number.");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid task number.");
                }
            }

            else {
                Task task = new Task(input);
                System.out.println("added: " + input);
                list.add(task);
            }

        }

        System.out.println("Bye! Hope to see you again soon.");
        scanner.close();
    }
}