package main.java;

public class KenChat {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public KenChat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KenChatException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.start();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isRunning = command.isRunning();
            } catch (KenChatException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.end();
    }

    public static void main(String[] args) throws KenChatException {
        new KenChat("data/DukeBot.txt").run();
    }
}
