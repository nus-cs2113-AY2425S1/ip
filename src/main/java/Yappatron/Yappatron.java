package Yappatron;

import Commands.Parser;
import Commands.Taskmanager;
import Filemanager.FileManager;
import Tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class of program, execution starting here.
 */
public class Yappatron {
    private Ui ui;
    private FileManager fileManager;
    private Taskmanager taskManager;

    public Yappatron(){
        ui = new Ui();
        fileManager = new FileManager();
        try{
            taskManager = new Taskmanager(fileManager.loadFile());
        }catch (FileNotFoundException e){
            ui.showFileLoadingError();
            fileManager.createFile();
            taskManager = new Taskmanager();
        }
    }

    /**
     * Runs the program until user inputs bye, executing commands by parsing user input into the Parser class to
     * determine which command to execute in Taskmanager.
     */
    public void run(){
        int exitStatus = 0;
        String input;
        ui.printWelcomeMessage();
        do{
            input = ui.getInput();
            String commandToExecute = Parser.parseFirstWord(input);
            switch (commandToExecute){
            case "event":
                taskManager.addEvent(input);
                break;
            case "deadline":
                taskManager.addDeadline(input);
                break;
            case "todo":
                taskManager.addTodo(input);
                break;
            case "list":
                taskManager.list();
                break;
            case "delete":
                taskManager.deleteTask(input);
                break;
            case "mark":
                taskManager.mark(input);
                break;
            case "unmark":
                taskManager.unmark(input);
                break;
            case "find":
                taskManager.findTask(input);
                break;
            case "bye":
                exitStatus = 1;
                break;
            default:
                ui.invalidCommand();
            }
        }while (exitStatus == 0);

        try {
            ArrayList<Task> taskArray = taskManager.getTaskArray();
            fileManager.writeFile(taskArray);
        }catch (IOException e){
            ui.printWriteFileError();
        }

        ui.printByeMessage();
    }
    public static void main(String[] args) {
        Yappatron yapper = new Yappatron();
        yapper.run();
    }
}
