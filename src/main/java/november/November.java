package november;

import november.parser.Parser;
import november.tasks.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static november.ui.Ui.printGreeting;
import static november.storage.Storage.initialiseSaveFile;
import static november.storage.Storage.loadSaveFile;
import static november.ui.Ui.printExitMessage;
import static november.strings.Strings.EXIT_COMMAND;

public class November {

    /**
     * Main method to start the program and handle user input.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the save file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Task> taskList = new ArrayList<>();
        initialiseSaveFile();
        loadSaveFile(taskList);

        printGreeting();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine(); // Read the initial input

        // Until the exit command is entered, execute command then read user input
        while (!input.equals(EXIT_COMMAND)) {
            Parser.parse(input,taskList);
            input = scan.nextLine();
        }

        printExitMessage();
    }
}
