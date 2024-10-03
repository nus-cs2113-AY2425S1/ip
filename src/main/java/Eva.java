/**
 * The main class for Eva chatbot
 * This class initialises the program by creating instances of
 * {@link TaskManager}, {@link Ui}, {@link Parser}, {@link CommandHandler}
 * and manages how the program read the input from the user, parse the input,
 * executes the commands and exits the program
 */
public class Eva {

    private final static TaskManager taskManager = new TaskManager();
    private final static Ui ui = new Ui();
    private final static Parser parser = new Parser();
    private final static CommandHandler commandHandler = new CommandHandler(taskManager);

    public static void main(String[] args) {

        ui.showWelcomeMessage();

        String line;

        while(true) {
            line = ui.readCommand();

            try {
                String command = parser.getCommand(line);
                String[] parts = parser.parseArguments(line, command);

                commandHandler.executeCommand(command, parts);

                if (command.equals("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }
            } catch (EvaException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
