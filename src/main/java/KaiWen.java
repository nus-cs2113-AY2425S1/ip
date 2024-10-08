import java.io.IOException;

/**
 * The main class for the KaiWen chatbot that manages tasks.
 * It integrates with Storage, TaskList, and Ui classes to handle user commands and task management.
 */
public class KaiWen {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    /**
     * Initializes the KaiWen chatbot with the specified file path.
     * Loads tasks from the file if available, or creates an empty TaskList if not.
     */
    public KaiWen(String var1) {
        this.storage = new Storage(var1);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (IOException var3) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    /**
     * Starts the main loop of the chatbot, handling user input and commands.
     * Continues to run until the user enters the "bye" command.
     */
    public void run() {
        this.ui.showWelcome();
        boolean var1 = false;

        while(!var1) {
            try {
                String var2 = this.ui.readCommand();
                this.ui.showLine();
                String[] var3 = Parser.parseCommand(var2);
                switch (var3[0]) {
                    case "bye":
                        this.ui.showGoodbye();
                        var1 = true;
                        break;
                    case "list":
                        this.tasks.printTasks();
                        break;
                    default:
                        CommandHandler.handleCommand(var3, this.tasks);
                }

                this.storage.save(this.tasks.getTasks());
            } catch (KaiException var10) {
                this.ui.showError(var10.getMessage());
            } catch (IOException var11) {
                this.ui.showError("Error saving tasks: " + var11.getMessage());
            } finally {
                this.ui.showLine();
            }
        }

    }

    /**
     * The entry point for the KaiWen application. Creates a new instance and runs the chatbot.
     */
    public static void main(String[] var0) {
        (new KaiWen("data/tasks.txt")).run();
    }
}