import java.util.Scanner;

public class Ran {
    private static boolean isTerminated = false;

    public static void greet() {
        System.out.println("____________________________________________________________");
        String logo = "     ___           ___           ___\n"
            + "    /\\  \\         /\\  \\         /\\__\\\n"
            + "   /::\\  \\       /::\\  \\       /::|  |\n"
            + "  /:/\\:\\  \\     /:/\\:\\  \\     /:|:|  |\n"
            + " /::\\~\\:\\  \\   /::\\~\\:\\  \\   /:/|:|  |__\n"
            + "/:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\\n"
            + "\\/_|::\\/:/  / \\/__\\:\\/:/  / \\/__|:|/:/  /\n"
            + "   |:|::/  /       \\::/  /      |:/:/  /\n"
            + "   |:|\\/__/        /:/  /       |::/  /\n"
            + "   |:|  |         /:/  /        /:/  /\n"
            + "    \\|__|         \\/__/         \\/__/\n";
        System.out.println(logo + "Hello! I'm Ran.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________"); 
    }

    public static void main(String[] args) {
        greet();

        String input;
        Scanner in = new Scanner(System.in);

        while(!isTerminated) {
            input = in.nextLine();
            if (input.equals("bye")) {
                isTerminated = true;
            }
            else {
                echo(input);
            }
        }

        bidFarewell();
    }
}
