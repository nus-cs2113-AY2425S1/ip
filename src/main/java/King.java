import java.util.Scanner;

public class King {
    public static final String name = "King";
    public static int repeatCount = 0;

    public static void greet() {
        String logo =
                "| |/ /|_ _|| \\| | / _` |\n" +
                        "| ' <  | | | .` || (_| |\n" +
                        "|_|\\_\\|___||_|\\_| \\__,_|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(" Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        chat();
    }

    public static void exit() {
        System.out.println("____________________________________________________________\n");

         if (repeatCount >= 5) {
            System.out.println(" I am repeating no more!!!\n");
        } else {
            System.out.println(" Bye. Hope to see you again soon!\n");
        }

        System.out.println("____________________________________________________________");
    }

    public static void chat() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equals("bye")) {
            exit();
        } else if (repeatCount >= 5) {
            exit();
        } else {
            repeat(userInput);
            chat();
        }

    }

    public static void repeat(String text) {
        repeatCount += 1;
        System.out.println("____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________"
        );
    }


    public static void main(String[] args) {
        greet();
    }
}
