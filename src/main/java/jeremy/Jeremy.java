package jeremy;

import jeremy.command.Command;
import jeremy.exception.*;
import jeremy.task.Deadline;
import jeremy.task.Event;
import jeremy.task.Todo;
import jeremy.util.PrintUtils;
import jeremy.util.TaskList;
import jeremy.Storage;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Jeremy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList;
        try {
            taskList = Storage.readData();
        } catch (FileNotFoundException e) {
            PrintUtils.println("Storage file couldn't be created");
            return;
        }
        PrintUtils.greeting();
        PrintUtils.logo();

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] parts = userInput.split(" ", 2);
            String commandStr = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            try {
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
                case DELETE:
                    taskList.deleteTask(argument);
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
                }
            } catch (IllegalCommandException e) {
                PrintUtils.lineBreak();
                PrintUtils.println("Lol, " + commandStr + " is not a valid command.");
                PrintUtils.lineBreak();
            } catch (EmptyArgumentException e) {
                PrintUtils.lineBreak();
                PrintUtils.println("Tasks must have a description");
                PrintUtils.lineBreak();
            } catch (InvalidCommandFormatException e) {
                PrintUtils.lineBreak();
                PrintUtils.print("Invalid command format, ");
                PrintUtils.println(e.getMessage());
                PrintUtils.lineBreak();
            } catch (InvalidTaskNumberException e) {
                PrintUtils.lineBreak();
                PrintUtils.println("Wow, " + e.getMessage() + " is not even a number.");
                PrintUtils.lineBreak();
            } catch (TaskNotFoundException e) {
                PrintUtils.lineBreak();
                PrintUtils.println(e.getMessage());
                PrintUtils.lineBreak();
            }

            userInput = scanner.nextLine();
        }

        Storage.saveData(taskList);
        PrintUtils.bye();
    }
}
