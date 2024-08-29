import java.util.Scanner;

public class Aerus {
    public static void main(String[] args) {
        String dividerLine = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println(dividerLine);
        System.out.println("Hello! I'm Aerus!\nWhat can I do for you?");
        System.out.println(dividerLine);

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(dividerLine);
            System.out.println(userInput);
            System.out.println(dividerLine);
        }

        System.out.println(dividerLine);
        System.out.println("See you next time!");
        System.out.println(dividerLine);
    }
}
