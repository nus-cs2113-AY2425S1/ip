import java.util.Scanner;

public class Yukee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String logo = " Y   Y  U   U  K   K  EEEEE  EEEEE\n"
                + "  Y Y   U   U  K  K   E      E    \n"
                + "   Y    U   U  KKK    EEEE   EEEE \n"
                + "   Y    U   U  K  K   E      E    \n"
                + "   Y     UUU   K   K  EEEEE  EEEEE\n";
        System.out.println("Hello! I'm Yukee, your friendly assistant!\n" + logo);
        System.out.println("(✿◠‿◠) What can I do for you today? (◕‿◕)");

        while (true) {
            String input = scanner.nextLine().trim();
            String[] inputSplit = input.split(" ", 2);

            switch (inputSplit[0].toLowerCase()) {
                case "bye":
                    System.out.println("☆*:.｡. o(≧▽≦)o .｡.:*☆ Bye! Hope to see you again soon!");
                    return;

                case "list":
                    if (taskCount == 0) {
                        System.out.println("Your task list is empty! Try adding some tasks! ✍(◔◡◔)");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
                        }
                    }
                    break;

                case "mark":
                    try {
                        int index = Integer.parseInt(inputSplit[1]) - 1;
                        if (index >= 0 && index < taskCount) {
                            tasks[index].markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks[index]);
                        } else {
                            System.out.println("Invalid task number. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid command. Usage: mark <task_number>");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(inputSplit[1]) - 1;
                        if (index >= 0 && index < taskCount) {
                            tasks[index].markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks[index]);
                        } else {
                            System.out.println("Invalid task number. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid command. Usage: unmark <task_number>");
                    }
                    break;

                case "help":
                    System.out.println("Here are the commands you can use:");
                    System.out.println("1. 'list' - List all tasks.");
                    System.out.println("2. 'mark <task_number>' - Mark a task as done.");
                    System.out.println("3. 'unmark <task_number>' - Mark a task as not done.");
                    System.out.println("4. 'hello' or 'hi' - Greet Yukee.");
                    System.out.println("5. 'bye' - Exit the program.");
                    break;

                case "hello":
                case "hi":
                    System.out.println("Hello there! I'm Yukee, your personal assistant. I can help you manage tasks. Just let me know what you'd like to do! (✿◠‿◠)");
                    break;

                default:
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println("added: " + input);
                    System.out.println("Keep going! You're doing great! ✨");
                    break;
            }
        }
    }
}