import parser.Parser;
import tasklist.TaskList;
import ui.EchoUI;

public class Echo {
    public static void main(String[] args) {
        runEcho();
    }

    private static void runEcho() {
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        EchoUI echoUI = new EchoUI();

        echoUI.displayGreeting();
        echoUI.runChat(parser, taskList);
    }
}
