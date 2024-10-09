import customexceptions.*; // Import custom exception classes
import taskpackage.*; // Import task-related classes like ToDo, Deadline, and Event

import java.io.IOException;


public class Juan {


    private final static String dataFilePath = "data.text";
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    // Constructor
    public Juan(String dataFilePath) {

        ui = new UI();
        storage = new Storage(dataFilePath, ui);
        try {
            tasks = storage.readData();
        } catch (Exception e) {
            ui.porFavorMessage(e.getMessage());
        }
        parser = new Parser(ui, tasks);
    }

    // Main running function
    public void run() {

        // Display initial line and welcome message
        ui.helloMessage();

        // Continue chatting as long as user doesn't exit
        boolean continueChatting = true;
        while (continueChatting) {
            // Chat feature to handle user input
            continueChatting = parser.chatFeature(ui.readUserInput());
        }

        // Add Function to Write Data
        storage.writeDate(parser.getTasks());
        ui.lineMessage();
        // Display goodbye message when the chat ends
        ui.byeMessage();
    }

    public static void main(String[] args) {
        new Juan(dataFilePath).run();
    }

}
