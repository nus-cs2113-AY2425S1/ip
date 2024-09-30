import Commands.ByeCommand;
import Commands.Command;
import Commands.CommandResult;

import java.util.ArrayList;

import Tasks.Task;
import TasksList.TasksList;
import Ui.Ui;
import utils.*;

public class Cubone {


    // list to store user input
    static TasksList tasksList = new TasksList();

    static boolean isWorking = true;

    static ArrayList<Task> inputed_tasks = new ArrayList<Task>();
    static Boolean LogFileRead = false;
    // read the tasks from the file
    static {
        try {
            // inputed_tasks = Storage.readLogFile();
            tasksList.setTasksList(Storage.readLogFile());
            if (tasksList.size() > 0) 
                LogFileRead = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasksList);
            CommandResult result = command.execute();
            // storage.save(addressBook);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Ui ui;
    /**
     * The main method serves as the entry point for the Cubone application.
     * It initializes the UI, displays the welcome message, and enters a loop
     * to process user commands until the exit command is received.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Cubone cuboneInstance = new Cubone();
        cuboneInstance.ui = new Ui();
        cuboneInstance.ui.showWelcomeMsg(LogFileRead, tasksList.getTasksList());
        // Scanner sc = new Scanner(System.in);
        Command command;
        do {
            String userCommandText = cuboneInstance.ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = cuboneInstance.executeCommand(command);
            cuboneInstance.ui.showResultToUser(result);
            cuboneInstance.ui.showLine();
            // log the tasks to the file
            Storage.updateLogFile(tasksList.getTasksList());
        }while(!ByeCommand.isExit(command));
    }
}
