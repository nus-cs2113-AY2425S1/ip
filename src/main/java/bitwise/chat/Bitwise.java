package bitwise.chat;

import java.io.IOException;
import java.util.Scanner;

import bitwise.constants.Commands;
import bitwise.constants.Constants;
import bitwise.constants.Messages;
import bitwise.exceptions.*;
import bitwise.tasks.*;
import bitwise.utils.FileManager;
import bitwise.utils.InputHandler;
import bitwise.utils.OutputManager;
import java.util.ArrayList;

public class Bitwise {

    private static ArrayList<Task> tasksList = new ArrayList<Task>();
    private static int numberOfTasks = 0;

    public static void setNumberOfTasks(int numberOfTasks) {
        Bitwise.numberOfTasks = numberOfTasks;
    }

    public static void main(String[] args) {
        try {
            numberOfTasks = FileManager.getTasks(tasksList);
        } catch (IOException ignored) {
        }
        OutputManager.printWelcomeMessage();
        mainManager();
        OutputManager.printExitMessage();
    }

    public static void mainManager() {
        String userInput;
        Status status = Status.RUNNING;
        while (status != Status.EXIT) {
            userInput = InputHandler.getUserInput();
            try {
                status = InputHandler.handleInput(userInput, tasksList, numberOfTasks);
            } catch (InvalidCommandException e) {
                OutputManager.printMessage("Invalid command: " + userInput);
                OutputManager.printListCommands();
            } catch (InvalidFormatException e) {
                OutputManager.printMessage("Invalid format: " + e);
                OutputManager.printLineBreak();
            }
        }
    }
}
