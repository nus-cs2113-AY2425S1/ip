import java.util.Scanner;

public class Legin {
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
        horizontalLine();
        System.out.println(input);
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
            }
            echo(command);
        }
        bye();
    }
}
