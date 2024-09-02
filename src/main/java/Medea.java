import java.util.Scanner;

public class Medea {

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            handleInput(taskList, input);
            input = scanner.nextLine();
        }
        sayGoodbye();
    }

    private static void greet(){
        System.out.println("Hello! I'm Medea.");
        System.out.println("What can I do for you?");
    }

    private static void sayGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleInput(TaskList taskList, String input) {
        String[] inputArguments = input.split(" ", 2); // Split into command and the rest
        String command = inputArguments[0];
        String arguments = inputArguments.length > 1 ? inputArguments[1] : "";

        switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "mark":
                handleMarkCommand(taskList, arguments);
                break;
            case "unmark":
                handleUnmarkCommand(taskList, arguments);
                break;
            case "todo":
                handleTodoCommand(taskList, arguments);
                break;
            case "deadline":
                handleDeadlineCommand(taskList, arguments);
                break;
            case "event":
                handleEventCommand(taskList, arguments);
                break;
            default:
                System.out.println("I don't recognize that command.");
                break;
        }
    }

    private static void handleMarkCommand(TaskList taskList, String arguments) {
        try {
            int markIndex = Integer.parseInt(arguments) - 1;
            taskList.markTask(markIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid mark command. Please provide a valid task number.");
        }
    }

    private static void handleUnmarkCommand(TaskList taskList, String arguments) {
        try {
            int unmarkIndex = Integer.parseInt(arguments) - 1;
            taskList.unmarkTask(unmarkIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid unmark command. Please provide a valid task number.");
        }
    }
    private static void handleTodoCommand(TaskList taskList, String description) {
        if (description.isEmpty()){
            System.out.println("Invalid todo command. Please provide a task description.");
            return;
        }
        taskList.addTodo(description);
    }

    private static void handleDeadlineCommand(TaskList taskList, String arguments) {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length != 2) {
            System.out.println("Invalid deadline command. Please provide a task description and a deadline using '/by'.");
            return;
        }
        String description = parts[0];
        String deadlineDate = parts[1];
        taskList.addDeadline(description, deadlineDate);
    }

    private static void handleEventCommand(TaskList taskList, String arguments) {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length != 3) {
            System.out.println("Invalid event command. Please provide a task, start time, and end time using '/from' and '/to'.");
            return;
        }
        String description = parts[0];
        String eventStart = parts[1];
        String eventEnd = parts[2];
        taskList.addEvent(description, eventStart, eventEnd);
    }
}