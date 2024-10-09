import taskpackage.TaskList;

public class Juan {


    private final static String dataFilePath = "data.text";
    private final UI ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

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
