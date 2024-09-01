import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cuboyd {
    // Helpers /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void displayIntroText(){
        String name = "Cuboyd";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    // Main Loop ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        displayIntroText();

        // Command Entry
        TaskTrackerUI taskTrackerUI = new TaskTrackerUI();
        String line;
        HashMap<String, String> argumentsList;
        Scanner scanner = new Scanner(System.in);
        boolean isAskingInput = true;

        while (isAskingInput){
            System.out.print("> ");
            line = scanner.nextLine();
            argumentsList = CommandParser.parseCommandToArguments(line);

            switch(argumentsList.get(CommandParser.ARGUMENT_COMMAND)){
                case "list":
                    taskTrackerUI.listTasks();
                    break;
                case "todo":
                    taskTrackerUI.addTodo(argumentsList);
                    break;
                case "deadline":
                    taskTrackerUI.addDeadline(argumentsList);
                    break;
                case "event":
                    taskTrackerUI.addEvent(argumentsList);
                    break;
                case "mark":
                    taskTrackerUI.markTask(argumentsList);
                    break;
                case "unmark":
                    taskTrackerUI.unmarkTask(argumentsList);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isAskingInput = false;
                    break;
                default:
                    System.out.println("No valid command given!");
                    break;
            }
        }
    }
}