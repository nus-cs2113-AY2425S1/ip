import java.io.IOException;

public class KaiWen {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();

    public KaiWen(String var1) {
        this.storage = new Storage(var1);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (IOException var3) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

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

    public static void main(String[] var0) {
        (new KaiWen("data/tasks.txt")).run();
    }
}