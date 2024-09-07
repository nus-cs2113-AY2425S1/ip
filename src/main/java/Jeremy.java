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
            String commandStr = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";
            Command command = Command.fromString(commandStr);

            switch (command) {
            case LIST:
                taskList.printList();
                break;
            case MARK:
                taskList.markTaskAsDone(argument);
                break;
            case UNMARK:
                taskList.markTaskAsNotDone(argument);
                break;
            case TODO:
                taskList.addTask(new Todo(argument));
                break;
            case DEADLINE:
                taskList.addTask(new Deadline(argument));
                break;
            case EVENT:
                taskList.addTask(new Event(argument));
                break;
            case UNKNOWN_COMMAND:
                PrintUtils.lineBreak();
                PrintUtils.println("Lol, " + commandStr + " is not a valid command.");
                PrintUtils.lineBreak();
                break;
            }

            userInput = scanner.nextLine();
        }

        PrintUtils.bye();
    }
}
