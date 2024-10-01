package melchizedek;

import melchizedek.exceptions.DescriptionNotPresentException;
import melchizedek.exceptions.InvalidTaskNumberException;
import melchizedek.task.TaskList;

import java.util.Arrays;
import java.util.Scanner;


public class Melchizedek {

    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Ui.sayHelloToUser();
        Storage.loadFile(taskList);
        Scanner in = new Scanner(System.in);
        while (true) {
            String[] tokens = Parser.parseInput(in.nextLine());

            Ui.printSeparator();

            switch (tokens[0].toLowerCase()) {
            case "bye":
                Storage.writeToFile(taskList);
                Ui.sayByeToUser();
                return;

            case "help":
                Ui.listCommands();
                break;

            case "list":
                Ui.printTaskList(taskList);
                break;

            case "mark":
                try {
                    taskList.markTaskAsDone(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    Ui.printUnspecifiedTaskNumber("mark");
                    Ui.printMarkExample();
                } catch (InvalidTaskNumberException e) {
                    Ui.printInvalidTaskNumberException();
                    Ui.printNumberOfTasks(taskList.getTaskCount());
                }
                break;

            case "unmark":
                try {
                    taskList.unmarkTaskAsDone(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    Ui.printUnspecifiedTaskNumber("unmark");
                    Ui.printUnmarkExample();
                } catch (InvalidTaskNumberException e) {
                    Ui.printInvalidTaskNumberException();
                    Ui.printNumberOfTasks(taskList.getTaskCount());
                }
                break;

            case "todo":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.addTodo(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    Ui.printUnableToProcessWithoutDescription("todo");
                    Ui.printTodoExample();
                }
                break;

            case "deadline":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.addDeadline(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    Ui.printUnableToProcessWithoutDescription("deadline");
                    Ui.printDeadlineExample();
                }
                break;

            case "event":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.addEvent(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    Ui.printUnableToProcessWithoutDescription("event");
                    Ui.printEventExample();
                }
                break;

            case "delete":
                try {
                    taskList.deleteTask(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    Ui.printUnspecifiedTaskNumber("delete");
                    Ui.printDeleteExample();
                } catch (InvalidTaskNumberException e) {
                    Ui.printInvalidTaskNumberException();
                    Ui.printNumberOfTasks(taskList.getTaskCount());
                }
                break;

            case "find":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.findKeyword(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    Ui.printUnableToFindWithoutKeyword();
                    Ui.printFindExample();
                }
                break;

            default:
                Ui.printInvalidCommand();
                break;
            }

            Ui.printSeparator();
        }
    }
}
