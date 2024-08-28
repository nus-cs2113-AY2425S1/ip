import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String logo = "  ____        _     \n"
                + " | |_) \\ ___ | |___  \n"
                + " |  _ //  _  \\   _ \\ \n"
                + " | |_)\\\\ (_) /  |_) |\n"
                + " |____/ \\___/|_|___/ \n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                String[] inputsInString = input.split(" ");
                int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                String[] inputsInString = input.split(" ");
                int taskIndex = Integer.parseInt(inputsInString[1]) - 1;
                tasks[taskIndex].unmark();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}