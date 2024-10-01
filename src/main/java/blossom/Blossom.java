package blossom;

public class Blossom {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    Blossom(String filePath) {
        ui = new Ui();
        tasks = new TaskList(ui);
        storage = new Storage(filePath, tasks.getTasks());

        ui.printIntro();
        try {
            storage.loadTasks();
        } catch (BlossomException e) {
            tasks = new TaskList(ui);
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.printIntro();
        Parser parser = new Parser(ui, tasks, storage);
        parser.start();
    }
    public static void main(String[] args) {
        new Blossom("./data/blossom.txt").run();
    }
}
