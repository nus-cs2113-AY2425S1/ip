import java.util.Scanner;

public class Jeremy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(100);
        PrintUtils.greeting();
        PrintUtils.logo();

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch (command) {
            case "list":
                taskList.printList();
                break;
            case "mark":
                taskList.markTaskAsDone(Integer.parseInt(argument));
                break;
            case "unmark":
                taskList.markTaskAsNotDone(Integer.parseInt(argument));
                break;
            case "todo":
                taskList.addTask(new Todo(argument));
                break;
            case "deadline":
                taskList.addTask(new Deadline(argument));
                break;
            case "event":
                taskList.addTask(new Event(argument));
                break;
            default:
                taskList.addTask(new Task(userInput));
                break;
            }

            userInput = scanner.nextLine();
        }

        PrintUtils.bye();
    }
}
