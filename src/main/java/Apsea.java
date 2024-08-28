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
    public static void printResponse(String line) {
        printLine();
        System.out.println("    " + line);
        printLine();
    }
    public static void main(String[] args) {
        printHello();

        String line;
        boolean isExit = false;

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                isExit = true;
                printBye();
            } else {
                printResponse(line);
            }
        } while (!isExit);
    }
}
