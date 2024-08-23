import java.util.Scanner;

public class Jeremy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List list = new List(100);
        PrintUtils.greeting();
        PrintUtils.ascii();

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            // Split the input into command and argument
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
            case "list":
                list.printList();
                break;
            case "mark":
                list.markTaskAsDone(Integer.parseInt(argument));
                break;
            case "unmark":
                list.markTaskAsNotDone(Integer.parseInt(argument));
                break;
            default:
                list.addTask(userInput);
                break;
            }

            userInput = scanner.nextLine();
        }

        PrintUtils.bye();
    }
}
