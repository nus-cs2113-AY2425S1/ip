import java.io.IOException;
import java.util.Scanner;

public class Parser {

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
            } if (command.equals("list")) {
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
            } else {
                Ui.printInvalidCommand();
            }
        }
    }

    private static void markOrUnmark(String command) {
        try {
            String numberString = command.split(" ")[1];
            int index;
            try {
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
            } catch (NumberFormatException e) {
                if (command.startsWith("mark")) {
                    Ui.printInvalidMark();
                } else {
                    Ui.printInvalidUnmark();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (command.startsWith("mark")) {
                Ui.printInvalidMark();
            } else {
                Ui.printInvalidUnmark();
            }
        } catch (NumberFormatException e) {
            Ui.printInvalidCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

