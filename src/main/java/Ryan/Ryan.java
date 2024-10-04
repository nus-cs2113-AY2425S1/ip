package Ryan;

import java.util.Scanner;

import Ryan.exceptions.RyanException;
import Ryan.utility.Parser;
import Ryan.utility.Storage;
import Ryan.utility.Ui;
import Ryan.utility.TaskList;

import Ryan.commands.Command;

public class Ryan {
    public static void main(String[] args) {
        boolean isExiting = false;
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(Storage.loadTasks());
        Parser parser = new Parser();

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

