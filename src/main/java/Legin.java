import java.util.Scanner;

public class Legin {
    private static String[] tasks = new String[100];
    private static int currentTaskCount = 0;

    public static void horizontalLine() {
        System.out.println("______________________" +
                "______________________________________");
    }
    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Legin, your best online companion!");
        System.out.println("What can I do for you today my friend :D");
        horizontalLine();
    }
    public static void bye() {
        horizontalLine();
        System.out.println("Bye " +
            Character.toString(0x1F44B) +
            ". Hope to see you again really soon! " +
            Character.toString(0x1F608));
        horizontalLine();
    }

    public static void echo(String input) {
        tasks[currentTaskCount] = input;
        currentTaskCount++;
        horizontalLine();
        System.out.println("added: " + input);
        horizontalLine();
    }

    public static void list() {
        horizontalLine();
        for (int i = 0; i < currentTaskCount; i++ ) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
        horizontalLine();
    }
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        greet();
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                list();
            } else {
                echo(command);
            }
        }
        bye();
    }
}
