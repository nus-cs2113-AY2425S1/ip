import java.util.Scanner;

public class Tyrone {
    public static void getUserInput(String userInput) {
        if (userInput.startsWith("mark ")) {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            if (index >= 0 && index < Task.listCount) {
                Constants.markAsDone(index);
            } else {
                System.out.println(Constants.LINE);
                System.out.println("    Invalid task number bro.");
                System.out.println(Constants.LINE);
            }
            return;
        } else if (userInput.startsWith("unmark ")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            if (index >= 0 && index < Task.listCount) {
                Constants.unmarkAsUndone(index);
            } else {
                System.out.println(Constants.LINE);
                System.out.println("    Invalid task number bro.");
                System.out.println(Constants.LINE);
            }
            return;
        } else if (userInput.startsWith("todo ")) {
            ToDo.createToDo(userInput);
        } else if (userInput.startsWith("deadline ")) {
            Deadline.createDeadline(userInput);
        } else if (userInput.startsWith("event ")) {
            Event.createEvent(userInput);
        } else if (userInput.equals("list")) {
            Constants.getList();
        } else {
            System.out.println(Constants.LINE);
            System.out.println("    Invalid command my brother.");
            System.out.println(Constants.LINE);
        }
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println(Constants.LINE);
            System.out.println("    Hello from\n" + Constants.logo + "\n");
            System.out.println("    What can I do for you cuh?\n");
            System.out.println(Constants.LINE);
            String input = in.nextLine();
            while (!input.equals("bye")) {
                getUserInput(input);
                input = in.nextLine();
            }
        }
        Constants.goodbye();
    }
}