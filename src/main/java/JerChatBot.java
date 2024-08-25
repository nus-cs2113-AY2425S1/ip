import java.util.Scanner;

public class JerChatBot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("~~~~~~~~ Welcome to Jer Chat Bot ~~~~~~~~");
        System.out.println(" What you want?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Finally done, k bye...");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(input);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        in.close();
    }
}
