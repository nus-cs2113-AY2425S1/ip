import java.util.Scanner;

public class Niwa {
    static String name = "Niwa";
    static String logo = "\t _   _\n"
            + "\t| \\ | | (_)  _       _  ___\n"
            + "\t|  \\| | | | | | __  // //| |\n"
            + "\t| |\\  | | | | |/  |// //_| |\n"
            + "\t|_| \\_|_|_| |__/|__/ //  |_|";
    boolean isRunning;

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
        if (command.equals("bye")) {
            printExit();
            isRunning = false;
        }
        else {
            printHorizontalLine(40);
            Echo(command);
        }

    }

    public void Echo (String command) {
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
        printHorizontalLine(40);
        System.out.println("\tBye bae. Hope to see you again soon! Moah~");
        printHorizontalLine(40);
    }
}
