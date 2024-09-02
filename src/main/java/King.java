import java.util.ArrayList;
import java.util.Scanner;

public class King {
    public static final String name = "King";
    public static int repeatCount = 0;
    public static int tasksCount = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        String logo =
                """
                        | |/ /|_ _|| \\| | / _` |
                        | ' <  | | | .` || (_| |
                        |_|\\_\\|___||_|\\_| \\__,_|
                        """;

        System.out.println("Hello from\n" + logo);

        System.out.println(" Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        chat();
    }

    public static void exit() {
        System.out.println("____________________________________________________________\n");

         if (repeatCount >= 5) {
            System.out.println(" I am repeating no more!!!\n");
        } else {
            System.out.println(" Bye. Hope to see you again soon!\n");
        }

        System.out.println("____________________________________________________________");
    }

    public static void chat() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equals("bye")) {
            exit();
        } else if (userInput.equals("list")) {
            printList();
            chat();
        } else if (userInput.startsWith("mark")) {
            tasks.get(parseTaskIndex(userInput)).markAsDone();
            chat();
        }  else if (userInput.startsWith("unmark")) {
            tasks.get(parseTaskIndex(userInput)).markAsUndone();
            chat();
        } else {
            addTask(userInput);
            chat();
        }

    }

    public static int parseTaskIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }


    public static void printList() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");

        for (int i = 0; i < tasksCount; i++) {
            int indexNum = i + 1;
            System.out.println(indexNum  + ". " + tasks.get(i));
        }

        System.out.println("____________________________________________________________");
    }


    public static void addTask(String text) {
        tasksCount += 1;
        Task t = new Task(text);
        tasks.add(t);
        System.out.println("____________________________________________________________\n"
                + "added: " + text + "\n"
                + "____________________________________________________________"
        );
    }

//    public static void repeat(String text) {
//        repeatCount += 1;
//        System.out.println("____________________________________________________________\n"
//                + text + "\n"
//                + "____________________________________________________________"
//        );
//    }

    public static void main(String[] args) {
        greet();
    }
}
