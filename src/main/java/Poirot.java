public class Poirot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Poirot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        Task[] loadedTasks = storage.loadTasksFromFile();
        for (Task task : loadedTasks) {
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (PoirotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Poirot("./data/duke.txt").run();
    }
}
