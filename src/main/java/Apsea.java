import java.util.Scanner;

public class Apsea {
    public static void printLine() {
        System.out.println("    ________________________________________________________");
    }
    public static void printHello() {
        printLine();
        System.out.println("    Hello! I'm Apsea!\n"
                + "    What can I do for you?");
        printLine();
    }
    public static void printBye() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon");
        printLine();
    }
    public static void addTask(String line, String[] tasks, int count) {
        tasks[count] = line;

        printLine();
        System.out.println("    added task: " + line);
        printLine();
    }
    public static void listTasks(String[] tasks, int count) {
        printLine();
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i+1) + ". " + tasks[i]);
        }
        printLine();
    }

    public static void main(String[] args) {
        printHello();

        String line;
        boolean isExit = false;
        String[] tasks = new String[100];
        int count = 0;

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            switch (line) {
            case "bye":
                isExit = true;
                printBye();
                break;
            case "list":
                listTasks(tasks, count);
                break;
            default:
                addTask(line, tasks, count);
                count++;
                break;
            }
        } while (!isExit);
    }
}
