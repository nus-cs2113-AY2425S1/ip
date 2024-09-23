package joe;

public class Joe {

    private TaskList toDoItemArrayList;
    private Storage storer;
    private ChatParser chatParser;

    public Joe() {
        this.toDoItemArrayList = new TaskList();
        this.storer = new Storage(toDoItemArrayList).load();
        this.chatParser = new ChatParser(toDoItemArrayList);
    }

    public void run() {
        UI.printGreeting();
        this.chatParser.start();
        UI.printFarewell();
        this.storer.writeAndClose();
    }

    public static void main(String[] args) {
        Joe joeBot = new Joe();
        joeBot.run();
    }

}