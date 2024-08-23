import java.util.Scanner;

public class Niwa {
    static String name = "Niwa";
    static String logo = "\t _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";
    boolean isRunning;
    private String[] tasks = new String[100];
    private static int taskCount = 0;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Niwa.name = name;
    }

    public static String getLogo() {
        return logo;
    }

    public static void setLogo(String logo) {
        Niwa.logo = logo;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public Niwa() {
        isRunning = true;
        printGreet(name, logo);
    }
    public void processCommand(String command) {
        printHorizontalLine(40);
        if (command.equals("bye")) {
            printExit();
            isRunning = false;
        } else if (command.equals("list")) {
            list();
        } else {
            add(command);
        }

    }
    private void list () {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
    }
    private void add (String task) {
        if (taskCount >= tasks.length) {
            System.out.println("\tMax command number reached!");
            return;
        }
        tasks[taskCount++] = task.trim();
        System.out.println("\tadded: " + task.trim());
    }
    private void echo (String command) {
        System.out.println("\t" + command);
    }
    public String getCommand() {
        printHorizontalLine(40);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public void printHorizontalLine (int numDash) {
        System.out.print("\t");
        for (int i = 0; i<numDash; i++) {
            System.out.print("_"); //Print "_" numDash times
        }
        System.out.print("\n");
    }

    public void printGreet(String name, String logo) {
        System.out.println(logo);
        printHorizontalLine(40);
        System.out.println("\tHello sweeties! I'm " + name +"!");
        System.out.println("\tWhat can I do for you? Let's chat <3");

    }

    public void printExit() {
        System.out.println("\tBye bae. Hope to see you again soon! Moah~");
        printHorizontalLine(40);
    }
}
