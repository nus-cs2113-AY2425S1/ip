import java.util.Scanner;


public class Terri {
    public static void main(String[] args) {
        String logo = "\n" +
                "$$$$$$$$\\                               $$\\ \n" +
                "\\__$$  __|                              \\__|\n" +
                "   $$ |    $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\ \n" +
                "   $$ |   $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |\n" +
                "   $$ |   $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |\n" +
                "   $$ |   $$   ____|$$ |      $$ |      $$ |\n" +
                "   $$ |   \\$$$$$$$\\ $$ |      $$ |      $$ |\n" +
                "   \\__|    \\_______|\\__|      \\__|      \\__|\n" +
                "                                            \n";

        System.out.println("Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println("Heya! I'm Terri.");
        System.out.println("What can I help you with today?\n" + divider);

        // Initialise a scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Continually check for user input
        while (true) {
            String input = scanner.nextLine();

            // Short-circuit check for exit input
            if (input.equals("bye")) {
                System.out.println(divider);
                System.out.println("Bye then! See ya soon!\n"+divider);
                return;
            }

            // Otherwise, echo back user input and await further input
            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);
        }

    }
}
