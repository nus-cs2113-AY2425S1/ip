import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Duke {

    //taskList variable for level-2 onwards
    private static TaskList taskListInstance;
    private static Storage storageInstance;
    private static Parser parserInstance;
    private static UI uiInstance;
    //task count
    private static int taskCount = 0;

    private static String folderPath = "./data";
    private static String filePath = folderPath+ "/duke.txt";





    //main function to execute the chatbot
    public void execute() throws DukeException,IOException {
        taskListInstance = new TaskList();
        storageInstance = new Storage(taskListInstance);
        uiInstance = new UI(taskListInstance);
        parserInstance = new Parser(taskListInstance,uiInstance);

        Scanner inputReader = new Scanner(System.in);//scanner for receiving input

        taskListInstance.setUI(uiInstance);
        taskListInstance.setParser(parserInstance);


        System.out.println("Hello I'm Lambo");
        System.out.println("What can I do for you?");

        storageInstance.loadFileData();


        //Super loop used for receiving inputs continuously and response back to user
        SuperLoop:
        while (true) {
            String input = inputReader.nextLine();
            if(parserInstance.processCommand(input) != 0) {
                break SuperLoop;
            }
            storageInstance.updateFileData();
        }
    }
}
