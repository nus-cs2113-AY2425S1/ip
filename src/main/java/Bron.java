import java.util.Scanner;

public class Bron {
    public static void main(String[] args) {
        String logo = "  ____    ____      ____    _     _\n"
                    + " |  _ \\  |   _\\   /  __  \\ | \\   | |\n"
                    + " | |_) | | |_) |  | |  | | |  \\  | | \n"
                    + " |  _ <  | ___/   | |  | | | |\\\\ | |\n"
                    + " | |_) | | | \\ \\  | |__| | | | \\\\| |\n"
                    + " |____/  |_|  \\_\\  \\____/  |_|  \\__|\n";
        Scanner input = new Scanner(System.in);
        System.out.println(logo + "Hello! I'm Bron\n" + "What can I do for you?\n");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String line = input.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Catch you on the flip cuh");
                break;
            }

            if (line.equalsIgnoreCase("list")) {
                int listCount = 0;
                while(tasks[listCount] != null) {
                    System.out.println(listCount + 1 + ". " + tasks[listCount++].printTask());
                }
            } else if (line.startsWith("mark")){
                int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Good shit kid! I've marked this task as done:");
                    System.out.println("  " + tasks[taskIndex].printTask());
                } else {
                    System.out.println("Task not found.");
                }
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("Get yo shit together son, this task aint done yet:");
                    System.out.println("  " + tasks[taskIndex].printTask());
                } else {
                    System.out.println("Task not found.");
                }
            } else {
                System.out.println("added" + ": " + line);
                tasks[taskCount++] = new Task(line);
            }
        }
    }
}
