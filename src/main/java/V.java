import java.util.Scanner;

public class V {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private boolean isOnline;
    private Scanner input;
    private String command;


    public void run() {

        this.ui.greet();
        
        this.input = new Scanner(System.in);
        
        while (this.isOnline) {
            this.command = input.nextLine();
            this.isOnline = parser.parse(command, false);
        }
        this.input.close();
        ui.printBlock("See Ya");
    }

    public V(String saveFilePath) {
        this.storage = new Storage(saveFilePath);
        this.storage.loadSave();
        this.taskList = new TaskList(this.storage);
        this.parser = new Parser(this.storage, this.taskList);
        this.ui = new Ui();
        this.isOnline = true;
    }

    public V() {
    }
}
