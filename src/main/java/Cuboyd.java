import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cuboyd {

    // General Parsing /////////////////////////////////////////////////////////////////////////////////////////////////
    public static HashMap<String, String> parseCommandToArguments(String line) {
        HashMap<String, String> argumentsList = new HashMap<>();
        String[] lineArgs = line.split(" ");

        // Command
        if (lineArgs.length <= 0) {
            argumentsList.put("command","");
            return argumentsList;
        }
        argumentsList.put("command",lineArgs[0]);

        // Arguments
        String currArgumentName = "main";
        StringBuilder currArgument = new StringBuilder();

        for (int i=1; i<lineArgs.length; i++) {
            if (lineArgs[i].isEmpty()) { // Should be redundant but just in case
                continue;
            }
            if (lineArgs[i].charAt(0) == '/') {
                // New argument
                if (!currArgument.toString().isEmpty()){
                    argumentsList.put(currArgumentName, currArgument.toString().strip());
                }
                currArgumentName = lineArgs[i];
                currArgument.setLength(0);
            } else {
                // Add on to existing argument
                currArgument.append(" ").append(lineArgs[i]);
            }
        }
        // Add last command
        if (!currArgument.toString().isEmpty()) {
            argumentsList.put(currArgumentName, currArgument.toString().strip());
        }
        return argumentsList;
    }

    // Helpers /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void displayIntroText(){
        String name = "Cuboyd";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    // Main Loop ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        displayIntroText();
        TaskTrackerUI taskTrackerUI = new TaskTrackerUI();
        ArrayList<Task> tasks = new ArrayList<>();
        Task currentTask;
        int index;

        // Command Entry
        String line;
        HashMap<String, String> argumentsList;
        Scanner scanner = new Scanner(System.in);
        boolean isAskingInput = true;
        while (isAskingInput){
            System.out.print("> ");
            line = scanner.nextLine();
            argumentsList = parseCommandToArguments(line);
            switch(argumentsList.get("command")){
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