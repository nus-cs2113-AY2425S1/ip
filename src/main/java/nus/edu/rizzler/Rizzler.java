package nus.edu.rizzler;

import nus.edu.rizzler.command.Command;
import nus.edu.rizzler.exception.RizzlerException;
import nus.edu.rizzler.manager.Parser;
import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class Rizzler {
    UserInterface userInterface = new UserInterface();
    Parser parser = new Parser();
    Storage storage = new Storage();
    TaskList taskList = loadTasks();

    private TaskList loadTasks() {
        try {
            String csvString = storage.readFromFile();
            if (!csvString.isEmpty()) {
                return new TaskList(csvString);
            }
        } catch (RizzlerException | IOException exception) {
            userInterface.displayError(exception);
            userInterface.displayMessage("New RizzlerData.java file generated for storing your tasks.\n");
        }
        return new TaskList();
    }

    public void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String fullCommand;

        while (true) {
            try {
                fullCommand = scanner.nextLine();
                Command command = parser.parse(fullCommand);
                command.execute(taskList, userInterface, storage);
                if (command.isExit()) {
                    userInterface.sayGoodbye();
                    scanner.close();
                    return;
                }
            } catch (RizzlerException exception) {
                userInterface.displayError(exception);
            }
            finally {
                userInterface.drawLine();
            }
        }
    }

    public void runRizzler() {
        userInterface.sayHello();
        listenCommand();
    }

    public static void main(String[] args) {
        Rizzler rizzler = new Rizzler();
        rizzler.runRizzler();
    }
}
