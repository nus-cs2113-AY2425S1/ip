package ellio;

import ellio.command.Command;
import ellio.parser.Parser;
import ellio.task.*;
import ellio.storage.Storage;
import ellio.ui.Ui;

import java.util.Scanner;

import static ellio.command.Command.*;

public class Ellio {

    private static final String SAVED_TASK_FILEPATH = "./data/Ellio.txt";
    public static Storage storage;
    public static TaskList tasks;
    private Ui ui;


    /**
     * Class constructor for program runtime.
     * Prepares the program by creating Ui and Storage Objects
     * Extracts any pre-existing list from storage into the program
     * @param filePath
     */
    public Ellio(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.loadContent());
        } catch (EllioExceptions e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Executes the Program.
     * Handles any user input and displays output as expected.
     * Terminate program if an exit command is given
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit){
            try {
                String inputCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(inputCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (EllioExceptions e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Ellio(SAVED_TASK_FILEPATH).run();
    }
}
