package melchizedek;

import PACKAGE_NAME.melchizedek.Parser;
import melchizedek.exceptions.DescriptionNotPresentException;
import melchizedek.exceptions.InvalidTaskNumberException;
import melchizedek.task.TaskList;
import melchizedek.Ui;
import melchizedek.Storage;
import melchizedek.Parser;

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
                taskList.printTaskList();
                break;

            case "mark":
                try {
                    taskList.markTaskAsDone(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOh no! Please specify which task number to mark.");
                    System.out.println("\tExample: mark 3");
                } catch (InvalidTaskNumberException e) {
                    System.out.println("\tUh oh! Please input a valid task number!");
                    taskList.printNumberOfTasks();
                }
                break;

            case "unmark":
                try {
                    taskList.unmarkTaskAsDone(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOh no! Please specify which task number to unmark.");
                    System.out.println("\tExample: unmark 2");
                } catch (InvalidTaskNumberException e) {
                    System.out.println("\tUh oh! Please input a valid task number!");
                    taskList.printNumberOfTasks();
                }
                break;

            case "todo":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.addTodo(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    System.out.println("\tUh oh! I cannot create a todo with no description!");
                    System.out.println("\tExample: todo read lecture notes");
                }
                break;

            case "deadline":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.addDeadline(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    System.out.println("\tUh oh! I cannot create a deadline with no description!");
                    System.out.println("\tExample: deadline coding assignment /by 12pm");
                }
                break;

            case "event":
                try {
                    if (tokens.length < 2) {
                        throw new DescriptionNotPresentException();
                    }
                    taskList.addEvent(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (DescriptionNotPresentException e) {
                    System.out.println("\tUh oh! I cannot create an event with no description!");
                    System.out.println("\tExample: event coding lecture /from 2pm /to 4pm");
                }
                break;

            case "delete":
                try {
                    taskList.deleteTask(Integer.parseInt(tokens[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOh no! Please specify which task number to delete.");
                    System.out.println("\tExample: delete 1");
                } catch (InvalidTaskNumberException e) {
                    System.out.println("\tUh oh! Please input a valid task number!");
                    taskList.printNumberOfTasks();
                }
                break;

            default:
                System.out.println("\tSorry but I don't understand what you mean :(");
                System.out.println("\t\"help\" to get a list of commands.");
                break;
            }

            Ui.printSeparator();
        }
    }
}
