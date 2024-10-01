package akshan;

import akshan.command.CommandType;
import akshan.handler.Parser;
import akshan.handler.Storage;
import akshan.task.TaskList;
import akshan.util.Ui;
import java.io.IOException;

public final class Akshan {
    private static final Ui ui = new Ui();
    private static final TaskList taskList = new TaskList();
    private static Storage storageHandler;


    public Akshan() throws IOException{
        storageHandler = new Storage(taskList);
    }

    /**
     * Runs the main loop of the Akshan chatbot.
     */
    public void run() throws IOException {
        ui.printInitMessage();
        String line = ui.getInput();
        while (!line.equals(CommandType.BYE.getCommand())) {
            try {
                Parser.processCommand(line, taskList);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            storageHandler.saveData(taskList);
            line = ui.getInput();
        }
        ui.printByeMessage();
    }

    /**
     * Main method to run the Akshan bot.
     */
    public static void main(String[] args) throws IOException {
        new Akshan().run();
    }
}