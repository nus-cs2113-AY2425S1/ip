import java.util.Scanner;

public class Jeremy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List list = new List(100);
        Print.greeting();
        Print.ascii();

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                list.printList();
            } else {
                list.addItem(userInput);
            }

            userInput = scanner.nextLine();
        }

        Print.bye();
    }
}
