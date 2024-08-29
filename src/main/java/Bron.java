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
        String line = input.nextLine();
        String[] tasks = new String[100];
        int taskCount = 0;
        while (line != null) {

            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Catch you on the flip cuh");
                break;
            }
            if (line.equalsIgnoreCase("list")) {
                int listCount = 0;
                while(tasks[listCount] != null) {
                    System.out.println(listCount + 1 + ":" + tasks[listCount++]);
                }
            }
            else {
                System.out.println("added" + ". " + line);
                tasks[taskCount++] = line;
            }

            line = input.nextLine();
        }
    }
}
