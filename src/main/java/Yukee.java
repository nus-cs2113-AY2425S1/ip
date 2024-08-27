import java.util.Scanner;

public class Yukee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];  // 创建一个任务数组来存储任务
        int taskCount = 0;  // 任务计数器

        String logo = " Y   Y  U   U  K   K  EEEEE  EEEEE\n"
                + "  Y Y   U   U  K  K   E      E    \n"
                + "   Y    U   U  KKK    EEEE   EEEE \n"
                + "   Y    U   U  K  K   E      E    \n"
                + "   Y     UUU   K   K  EEEEE  EEEEE\n";
        System.out.println("Hello! I'm Yukee, your friendly assistant!\n" + logo);
        System.out.println("(✿◠‿◠) What can I do for you today? (◕‿◕)");

        while (true) {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);  // 分割命令和内容

            if (inputSplit[0].equalsIgnoreCase("bye")) {
                System.out.println("☆*:.｡. o(≧▽≦)o .｡.:*☆ Bye! Hope to see you again soon!");
                break;
            } else if (inputSplit[0].equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (inputSplit[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(inputSplit[1]) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (inputSplit[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(inputSplit[1]) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                // 将用户输入的任务存储到数组中
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }
}
