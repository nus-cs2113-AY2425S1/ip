import java.io.IOException;
import java.util.Scanner;

/**
 * The Parser class handles user input and processes various commands
 * such as adding tasks, marking/unmarking tasks, and exiting the application.
 */
public class Parser {

    /**
     * Takes input from the user and processes commands in a continuous loop.
     * The loop runs until the user enters the "bye" command, which will
     * clear the task list, save the data, and exit the program.
     *
     * @throws IOException if there is an issue with input/output operations during saving.
     */
    public static void takeInput() throws IOException {
        String command;
        while (true) {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
            if (command.equals("bye")) {
                Storage.clear();
                Storage.save();
                Ui.printExit();
                break;
            }
            if (command.equals("list")) {
                TaskList.list();
            } else if (command.equals("help")) {
                Ui.printHelp();
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                markOrUnmark(command);
            } else if (command.startsWith("deadline")) {
                TaskList.addDeadline(command);
            } else if (command.startsWith("todo")) {
                TaskList.addTodo(command);
            } else if (command.startsWith("event")) {
                TaskList.addEvent(command);
            } else if (command.startsWith("delete")) {
                TaskList.deleteTask(command);
            } else if (command.startsWith("find")) {
                TaskList.find(command);
            } else {
                Ui.printInvalidCommand();
            }
        }
    }

    /**
     * Processes the "mark" and "unmark" commands to update the status of tasks
     * in the task list. It handles both valid and invalid task indices.
     *
     * @param command the user input containing either "mark" or "unmark" followed by a task index.
     */
    private static void markOrUnmark(String command) {
        try {
            String numberString = command.split(" ")[1];
            int index;
            index = Integer.parseInt(numberString) - 1;
            if (TaskList.listCount == 0) {
                Ui.printEmptyList();
            } else if (index >= TaskList.listCount) {
                Ui.printNonExistentTask();
            } else if (command.startsWith("mark ")) {
                TaskList.mark(index);
            } else {
                TaskList.unmark(index);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IOException e) {
            if (command.startsWith("mark")) {
                Ui.printInvalidMark();
            } else {
                Ui.printInvalidUnmark();
            }
        }
    }

}
