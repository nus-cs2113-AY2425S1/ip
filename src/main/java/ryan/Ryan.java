package ryan;

import java.util.Scanner;

import ryan.utility.Parser;
import ryan.utility.Storage;
import ryan.utility.Ui;
import ryan.utility.TaskList;

import ryan.exceptions.RyanException;

import ryan.commands.Command;

public class Ryan {
    public static void main(String[] args) {
        boolean isExiting = false;
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        TaskList taskList;
        try {
            taskList = new TaskList(Storage.loadTasks());
        } catch (RyanException e) {
            Ui.showError(e.getMessage());
            taskList = new TaskList();
        }

        Ui.printGreeting();

        while (!isExiting) {
            try {
                String userInput = scanner.nextLine();
                Command command = parser.parse(userInput);
                command.execute(taskList, new Ui());
                isExiting = command.isExit();
                Storage.saveTasks(taskList.getTasks());
            } catch (RyanException | NumberFormatException e) {
                Ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }
}

