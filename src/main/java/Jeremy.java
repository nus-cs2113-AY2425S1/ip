import java.util.Scanner;

public class Jeremy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Print.greeting();
        Print.ascii();

        String[] list = new String[100];
        int listSize = 0;

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                Print.line();
                for (int i = 1; i <= listSize; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
                Print.line();
            } else {
                Print.line();
                list[listSize] = userInput;
                listSize++;
                System.out.println("added: " + userInput);
                Print.line();
            }

            userInput = scanner.nextLine();
        }

        Print.bye();
    }
}
