import CassHelpers.exceptions.*;
import CassHelpers.types.Task;
import CassHelpers.util.FileUtil;
import CassHelpers.util.TodoList;
import java.util.ArrayList;
import java.util.Scanner;

import static CassHelpers.util.Messages.*;

public class Cassandra {

    private static boolean ifExit = false;

    private static void exit() {
        ifExit = true;
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static void executeCommand(String input, String[] commandArgs,TodoList todoList) {
        if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
            System.out.println("Sorry, you haven't entered any command.");
            return;
        }

        try {
            switch (commandArgs[0].toLowerCase()) {
                case "mark":
                    if (commandArgs.length < 2) {
                        throw new NoTaskIndexFoundException("Please provide a task index to mark.");
                    } else {
                        todoList.markTask(Integer.parseInt(commandArgs[1]));
                    }
                    break;
                case "unmark":
                    if (commandArgs.length < 2) {
                        throw new NoTaskIndexFoundException("Please provide a task index to unmark.");
                    } else {
                        todoList.unmarkTask(Integer.parseInt(commandArgs[1]));
                    }
                    break;
                case "delete":
                    if (commandArgs.length < 2) {
                        throw new NoTaskIndexFoundException("Please provide a task index to unmark.");
                    } else {
                        todoList.deleteTask(Integer.parseInt(commandArgs[1]));
                    }
                    break;
                case "list":
                    todoList.printList();
                    break;
                case "bye":
                    exit();
                    break;
                case "todo":
                    todoList.addTodo(input);
                    break;
                case "deadline":
                    todoList.addDeadline(input);
                    break;
                case "event":
                    todoList.addEvent(input);
                    break;
                case "help":
                    displayHelpInstructions();
                    break;
                default:
                    throw new InvalidCommandException("Sorry, unknown command.");
            }
        } catch (TaskNotFoundException | NoTaskIndexFoundException | TaskAlreadyMarkedException |
                 TaskAlreadyUnmarkedException | InvalidEventFormatException | InvalidDeadlineFormatException | InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task index.");
        }
    }

    private static void readUserCommand(TodoList todoList) {
        String input = new Scanner(System.in).nextLine().trim();
        String[] commandArgs = input.split(" ");
        drawLine();
        executeCommand(input,commandArgs,todoList);
        drawLine();
    }

    public static void main(String[] args) {
        displayIntroduction();
        FileUtil fileUtil = new FileUtil("./data","tasks.txt");
        ArrayList<Task> savedTasks = fileUtil.readTaskFromFile();
        TodoList todoList = new TodoList(savedTasks,fileUtil);
        while(!ifExit) {
            readUserCommand(todoList);
        }
    }
}
