package melchizedek;

import melchizedek.exceptions.DescriptionNotPresentException;
import melchizedek.exceptions.InvalidTaskNumberException;
import melchizedek.task.TaskList;

import java.util.Arrays;
import java.util.Scanner;


public class Melchizedek {

    public static TaskList taskList = new TaskList();
    public static final String SEPARATOR = "\t____________________________________________________________";


    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void sayHelloToUser() {
        printSeparator();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printSeparator();
    }

    private static void sayByeToUser() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printSeparator();
    }

    private static void listCommands() {
        System.out.println("\tHere is the list of commands:");
        System.out.println("\t  to add a todo: todo DESCRIPTION");
        System.out.println("\t  to add a deadline: deadline DESCRIPTION /by BY");
        System.out.println("\t  to add an event: event DESCRIPTION /from FROM /to TO");
        System.out.println("\t  to delete a task: delete TASK_NUMBER");
        System.out.println("\t  to mark a task as done: mark TASK_NUMBER");
        System.out.println("\t  to unmark a task as done: unmark TASK_NUMBER");
        System.out.println("\t  to display all tasks on the list: list");
        System.out.println("\t  to exit: bye");
    }

    public static void main(String[] args) {
        sayHelloToUser();
        FileHandling.loadFile(taskList);
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            String[] tokens = input.split(" ");

            printSeparator();

            switch (tokens[0].toLowerCase()) {
            case "bye":
                FileHandling.writeToFile(taskList);
                sayByeToUser();
                return;

            case "help":
                listCommands();
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

            printSeparator();
        }
    }
}
