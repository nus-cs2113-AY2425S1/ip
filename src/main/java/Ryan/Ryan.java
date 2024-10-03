package Ryan;

import java.util.Scanner;

import Ryan.exceptions.RyanException;
import Ryan.utility.Parser;
import Ryan.utility.Storage;
import Ryan.utility.Ui;
import Ryan.utility.TaskList;

import Ryan.commands.*;

public class Ryan {
    public static void main(String[] args) {
        boolean isExiting = false;
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(Storage.loadTasks()); // Using TaskList
        Parser parser = new Parser();

        Ui.printGreeting();

        while (!isExiting) {
            try {
                String userInput = scanner.nextLine();
                Command command = parser.parse(userInput);
                command.execute(taskList, new Ui()); // Pass TaskList instead
                isExiting = command.isExit();
                Storage.saveTasks(taskList.getTasks()); // Save tasks from TaskList
            } catch (RyanException | NumberFormatException e) {
                Ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }
}

