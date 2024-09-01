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
    public static final int INVALID_TASK_INDEX = -1;
    public static int parseValidTaskIndex(HashMap<String, String> argumentsList, ArrayList<Task> tasks){
        int index = INVALID_TASK_INDEX;
        if (argumentsList.get("main") == null){
            return index;
        }
        try {
            index = Integer.parseInt(argumentsList.get("main")) - 1;
        } catch (NumberFormatException e){
            return index;
        }
        if (index < 0 || index >= tasks.size()){
            return index;
        }
        return index;
    }
    public static void displayIntroText(){
        String name = "Cuboyd";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }
    public static void addTaskWithUI(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    // Main Loop ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        displayIntroText();
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
                    System.out.println("Here are the tasks in your list:");
                    for (int currentItemIndex = 0; currentItemIndex< tasks.size(); currentItemIndex++){
                        System.out.println(String.valueOf(currentItemIndex+1) + "." + tasks.get(currentItemIndex));
                    }
                    break;
                case "todo":
                    if (argumentsList.get("main") == null){
                        System.out.println("No description was given!");
                        break;
                    }
                    currentTask = new ToDo(argumentsList.get("main"));
                    addTaskWithUI(tasks, currentTask);
                    break;
                case "deadline":
                    if (argumentsList.get("main") == null){
                        System.out.println("No description was given!");
                        break;
                    }
                    if (argumentsList.get("/by") == null){
                        System.out.println("/by not given!");
                        break;
                    }
                    currentTask = new Deadline(argumentsList.get("main"), argumentsList.get("/by"));
                    addTaskWithUI(tasks, currentTask);
                    break;
                case "event":
                    if (argumentsList.get("main") == null){
                        System.out.println("No description was given!");
                        break;
                    }
                    if (argumentsList.get("/from") == null){
                        System.out.println("/from not given!");
                        break;
                    }
                    if (argumentsList.get("/to") == null){
                        System.out.println("/to not given!");
                        break;
                    }
                    currentTask = new Event(
                            argumentsList.get("main"),
                            argumentsList.get("/from"),
                            argumentsList.get("/to")
                    );
                    addTaskWithUI(tasks, currentTask);
                    break;
                case "mark":
                    index = parseValidTaskIndex(argumentsList, tasks);
                    if (index == INVALID_TASK_INDEX){
                        System.out.println("That is not a valid index!");
                        break;
                    }
                    currentTask = tasks.get(index);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + currentTask.toString());
                    break;
                case "unmark":
                    index = parseValidTaskIndex(argumentsList, tasks);
                    if (index == INVALID_TASK_INDEX){
                        System.out.println("That is not a valid index!");
                        break;
                    }
                    currentTask = tasks.get(index);
                    currentTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + currentTask);
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