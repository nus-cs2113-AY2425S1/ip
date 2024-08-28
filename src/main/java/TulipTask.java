import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TulipTask {
    public static void main(String[] args) {
        String logo = "                                         \n" +
                "--.--     |    o     --.--          |    \n" +
                "  |  .   .|    .,---.  |  ,---.,---.|__/ \n" +
                "  |  |   ||    ||   |  |  ,---|`---.|  \\ \n" +
                "  `  `---'`---'`|---'  `  `---^`---'`   `\n" +
                "                |";

        ArrayList<Task> list = new ArrayList<>();

        System.out.println(logo);

        System.out.println("--------------------------------------------");
        System.out.println("Hello, I'm TulipTask");
        System.out.println("What can I do for you today?");
        System.out.println("--------------------------------------------");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

//            if (Objects.equals(input.toLowerCase(), "bye")) {
//                break;
//            }
//
//            if (Objects.equals(input.toLowerCase(), "list")) {
//                listTasks(list);
//            }
//
//            if (input.toLowerCase().contains("mark")) {
//                String[] splitStr = input.split(" ");
//                System.out.println(splitStr[0]);
//                System.out.println(splitStr[1]);
//            }

            String command;

            if (input.toLowerCase().contains("mark")) {
                String[] splitStr = input.split(" ");
                command = splitStr[0];
            } else {
                command = input.toLowerCase();
            }

            switch(command) {
                case "list":
                    listTasks(list);
                    break;

                case "mark":
                    String[] splitStr = input.split(" ");
                    int index = Integer.parseInt(splitStr[1]) - 1;
                    markAsDone(list.get(index));
                    break;

                case "unmark":
                    String[] split = input.split(" ");
                    int idx = Integer.parseInt(split[1]) - 1;
                    markAsNotDone(list.get(idx));
                    break;

                case "bye":
                    System.out.println("--------------------------------------------");
                    System.out.println("Bye! Hope to see you again soon :)");
                    System.out.println("--------------------------------------------");
                    return;

                default:
                    Task task = new Task(command);
                    list.add(task);
                    echo(task);
                    break;
            }
        }
    }

    public static void echo (Task task) {
        System.out.println("--------------------------------------------");
        System.out.println("added: " + task.description);
        System.out.println("--------------------------------------------");
    }

    public static void listTasks (ArrayList<Task> list) {
        System.out.println("--------------------------------------------");
        System.out.println("Here are your tasks: ");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". " + "[" + task.getStatusIcon() + "] " + task.description);
        }
        System.out.println("--------------------------------------------");
    }

    public static void markAsDone(Task task) {
        task.markAsDone();
        System.out.println("--------------------------------------------");
        System.out.println("Great job! I have marked this task as done: \n" + "  [" + task.getStatusIcon() + "] " + task.description);
        System.out.println("--------------------------------------------");
    }
    public static void markAsNotDone(Task task) {
        task.markAsNotDone();
        System.out.println("--------------------------------------------");
        System.out.println("Okay, I have marked this task as not done: \n" + "  [" + task.getStatusIcon() + "] " + task.description);
        System.out.println("--------------------------------------------");
    }
}

