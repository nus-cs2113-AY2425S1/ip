import java.util.Scanner;

public class Grok {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Grok");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon! Keep Grokking :)");
                break;
            } else {
                System.out.println(input);
            }
        }
        scanner.close();
    }
}
