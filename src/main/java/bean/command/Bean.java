package bean.command;

import bean.exceptions.EmptyListException;
import bean.exceptions.InsufficientSpaceException;
import bean.exceptions.InvalidInputException;
import bean.exceptions.InvalidTaskNumException;
import bean.task.Deadline;
import bean.task.Event;
import bean.task.Task;
import bean.task.Todo;

import java.util.Scanner;

public class Bean {
    // Constants
    private final static String SEPARATOR_LINE = "_".repeat(60) + "\n";
    private final static String INDENT = "  ";
    private final static int MAX_LIST_COUNT = 100;
    private final static String LOGO = "  ┏━┓\n" +
            "  ┃ ┃\n" +
            "  ┃ ┗━━┳━━━┳━━━━┳━━━┓\n" +
            "  ┃  ┏┓┃ ┃━┫ ┏┓ ┃ ┏┓ ┓ ┏━━━━━━┓\n" +
            "  ┃  ┗┛┃ ┃━┫ ┏┓ ┃ ┃┃ ┃ ┃• ᴗ • ┫\n" +
            "  ┗━━ ━┻━━━┻━┛┗━┻━┛┗━┛ ┗━━━━━━┛\n";

    private static Task[] toDoList = new Task[MAX_LIST_COUNT];

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    // Print logo with greeting message
    public static void greet() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Howdy! :) I'm bean, your personal assistant.\n" +
                INDENT + "Let me help you keep track of your tasks!\n" +
                SEPARATOR_LINE
        );
    }

    // Print logo with exit message
    public static void exit() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Bye bye, glad I could help! See you soon? :'(\n" +
                SEPARATOR_LINE);
    }

    // Return duke.command (taken as first word) from given user input
    public static String extractCommand(String userInput) {
        // Take first word of input as duke.command
        return userInput.split(" ")[0];
    }

    // Print (single line) message with separator line above and below message
    public static void printFormattedReply(String reply) {
        System.out.println(SEPARATOR_LINE +
                reply + "\n" +
                SEPARATOR_LINE);
    }

    public static void printToDoList() throws EmptyListException {
        if (toDoList[0] == null) {
            throw new EmptyListException();
        }

        System.out.println(SEPARATOR_LINE +
                INDENT + "TO DO LIST:\n");

        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] == null) {
                break;
            }
            System.out.println(INDENT + (i + 1) + ". " + toDoList[i].toString());
        }
        System.out.println(SEPARATOR_LINE);
    }

    // Extract duke.task number as int from user input for mark and unmark commands
    public static int obtainTaskNum(String userInput) throws InvalidTaskNumException {
        // Obtain duke.task number by taking second word of input and trim any spaces, then parse as int
        String[] words = userInput.split(" ");
        int taskNum = Integer.parseInt(words[1].trim());
        if (taskNum < 0 || taskNum > Task.getNumberOfTasks()) {
            throw new InvalidTaskNumException();
        }
        return taskNum;
    }

    public static void markTaskAsDone(int taskNum) {

        int taskIndex = taskNum - 1;
        toDoList[taskIndex].setStatus(true);
        // Confirmation message
        printFormattedReply(INDENT + "duke.task.Task " + taskNum + " has been marked as DONE:\n" +
                INDENT + INDENT + toDoList[taskIndex].toString());
    }

    public static void unmarkTaskAsDone(int taskNum) {
        int taskIndex = taskNum - 1;
        toDoList[taskIndex].setStatus(false);
        // Confirmation message
        printFormattedReply(INDENT + "duke.task.Task " + taskNum + " has been marked as UNDONE:\n" +
                INDENT + INDENT + toDoList[taskIndex].toString());
    }

    public static void addTask(String userInput, TaskType taskType) throws InsufficientSpaceException {
        if (Task.getNumberOfTasks() >= MAX_LIST_COUNT) {
            throw new InsufficientSpaceException();
        }
        if (taskType == TaskType.TODO) {
            // Extract description
            String description = userInput.split("todo ")[1].trim();

            toDoList[Task.getNumberOfTasks()] = new Todo(description);
        } else if (taskType == TaskType.DEADLINE) {
            // Extract description and by
            String[] parts = userInput.split("/by ");
            // parts: [0] = "deadline {description} ", [1] = " {by}"
            String description = parts[0].substring("deadline ".length()).trim();
            String by = parts[1].trim();

            toDoList[Task.getNumberOfTasks()] = new Deadline(description, by);
        } else {
            // taskType == TaskType.EVENT
            // Extract description, from and to
            String[] splitDescription = userInput.split("/from");
            // splitDescription: [0] = "event {description} ", [1] = "{from} /to {to}"
            String description = splitDescription[0].substring("events".length()).trim();
            String[] splitFromTo = splitDescription[1].split("/to");
            // splitFromTo: [0] = "{from} ", [1] = "{to}"
            String from = splitFromTo[0].trim();
            String to = splitFromTo[1].trim();

            toDoList[Task.getNumberOfTasks()] = new Event(description, from, to);
        }
    }

    public static void printInvalidInputMessage() {
        printFormattedReply(INDENT + "Sorry, I am not equipped to respond to that yet... :(\n" +
                INDENT + "These are the commands I understand:\n" +
                INDENT + "1. To add a new duke.task:\n" +
                INDENT + INDENT + "a. todo [description]\n" +
                INDENT + INDENT + "b. deadline [description] /by [by]\n" +
                INDENT + INDENT + "c. event [description] /from [from] /to [to]\n" +
                INDENT + INDENT + INDENT + "example: event dinner /from 6pm /to 8pm\n" +
                INDENT + "2. To view your to do list: list\n" +
                INDENT + "3. To mark a duke.task as done: mark [duke.task number]\n" +
                INDENT + "4. To mark a duke.task as undone: unmark [duke.task number]");
    }

    public static void processUserInput() throws InvalidInputException {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (Task.getNumberOfTasks() < MAX_LIST_COUNT + 1) {
            userInput = in.nextLine();
            String userCommand = extractCommand(userInput);

            try {
                switch (userCommand) {
                case "bye":
                    // To exit
                    return;

                case "list":
                    printToDoList();
                    break;

                case "mark":
                    markTaskAsDone(obtainTaskNum(userInput));
                    break;

                case "unmark":
                    unmarkTaskAsDone(obtainTaskNum(userInput));
                    break;

                case "todo":
                    addTask(userInput, TaskType.TODO);
                    break;

                case "deadline":
                    addTask(userInput, TaskType.DEADLINE);
                    break;

                case "event":
                    addTask(userInput, TaskType.EVENT);
                    break;

                default:
                    throw new InvalidInputException();

                }
            } catch (InvalidInputException e) {
                printInvalidInputMessage();

            } catch (EmptyListException e) {
                printFormattedReply(INDENT + "Nothing in your to do list yet!");

            } catch (InvalidTaskNumException e) {
                printFormattedReply(INDENT + "Please enter a valid duke.task number!\n" +
                        INDENT + "You currently have " + Task.getNumberOfTasks() + " tasks.");

            } catch (InsufficientSpaceException e) {
                printFormattedReply(INDENT + "Sorry, you have reached the maximum list size of " + MAX_LIST_COUNT);

            }
        }
    }

    public static void main(String[] args) throws InvalidInputException {
        greet();
        processUserInput();
        exit();
    }
}