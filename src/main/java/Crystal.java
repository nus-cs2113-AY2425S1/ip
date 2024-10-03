public class Crystal {
    private Ui ui;
    private Parser parser;
    private final TaskList tasks;

    private Crystal() {
        ui = new Ui();
        parser = new Parser();
        tasks = new TaskList();
        Storage.loadTaskList(); // load the task list
    }

    public void callCrystal() {
        Ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            String input = Ui.readInput();
            isExit = Parser.parse(input, tasks);
        }
        Ui.printHorizontalLine();
        Ui.sayBye();

    }

    public static void main(String[] args) {
        new Crystal().callCrystal();
    }

}
