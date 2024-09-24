package joe;

/**
 * The Main class of this project.
 * Initializes all relevant objects and facilitates the execution of the program
 */
public class Joe {

    private TaskList toDoItemArrayList;
    private Storage storer;
    private ChatParser chatParser;

    /**
     * Initializes the corner stone objects of the program
     */
    public Joe() {
        this.toDoItemArrayList = new TaskList();
        this.storer = new Storage(toDoItemArrayList).load();
        this.chatParser = new ChatParser(toDoItemArrayList);
    }

    /**
     * Orchestrates the execution of the program
     */
    public void run() {
        UI.printGreeting();
        this.chatParser.start();
        UI.printFarewell();
        this.storer.writeAndClose();
    }

    /**
     * Executes the program
     * @param args None intended
     */
    public static void main(String[] args) {
        Joe joeBot = new Joe();
        joeBot.run();
    }

}