import java.io.IOException;
import java.util.Scanner;

public class Legin {
    private TaskList taskList;
    private Storage storage;
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    public Legin() {
        storage = new Storage();
        taskList = storage.getloadedTaskList();
    }

    public void runBot() throws IOException {
        boolean saidBye = false;
        ui.greet();
        while (!saidBye) {
            String input = ui.readInput();
            saidBye = parser.parseInput(taskList, input);
        }
        storage.updateTextFile(taskList);
        ui.bye();
    }

    public static void main(String[] args) throws IOException {
        Legin legin = new Legin();
        legin.runBot();
    }
}