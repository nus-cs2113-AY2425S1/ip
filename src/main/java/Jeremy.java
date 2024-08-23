import java.util.Scanner;

public class Jeremy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Print.greeting();
        Print.ascii();

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            // Echo the input back to the user
            Print.line();
            System.out.println(userInput);
            Print.line();

            userInput = scanner.nextLine();
        }

        Print.bye();
    }
}
