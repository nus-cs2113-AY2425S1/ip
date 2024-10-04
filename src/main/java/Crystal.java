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

    /**
     * Called to run the bot, Crystal. Meant to exist in a loop where it reads
     * the input everytime after executing the previous one.
     * Method will terminate if isExit is true, which will be corrected after
     * parsing.
     */
    public void callCrystal() {
        Ui.sayHello();
        Ui.askHelp();
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
