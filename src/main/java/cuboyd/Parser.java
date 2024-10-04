package cuboyd;

import cuboyd.tasks.Task;
import cuboyd.tasks.TaskList;

import java.util.HashMap;

/**
 * Parses user input into a command and executes them.
 */
public class Parser{
    public static final String ARGUMENT_COMMAND = "command";
    public static final String ARGUMENT_MAIN = "main";

    /**
     * Gets command from words.
     * @param words String list of arguments
     * @return
     */
    private String getCommandFromWords(String[] words){
        // Command
        if (words.length == 0) {
            return "";
        }
        return(words[0]);
    }

    /**
     * Pack command from words into an existing argument map.
     * @param argumentsMap Arguments Mapping
     * @param words String list of arguments
     */
    private void packCommandToExistingArgumentsMap(HashMap<String, String> argumentsMap, String[] words) {
        argumentsMap.put(Parser.ARGUMENT_COMMAND,getCommandFromWords(words));
    }

    /**
     * Pack following arguments from words into an existing argument map.
     * @param argumentsMap Arguments Mapping
     * @param words String list of arguments
     */
    private void packFollowingArgumentsToExistingArgumentsMap(HashMap<String, String> argumentsMap, String[] words) {
        // Arguments
        String currArgumentName = Parser.ARGUMENT_MAIN;
        StringBuilder currArgument = new StringBuilder();
        for (int i = 1; i< words.length; i++) {
            if (words[i].isEmpty()) { // Should be redundant but just in case
                continue;
            }
            if (words[i].charAt(0) == '/') {
                // New argument
                if (!currArgument.toString().isEmpty()){
                    argumentsMap.put(currArgumentName, currArgument.toString().strip());
                }
                currArgumentName = words[i];
                currArgument.setLength(0);
            } else {
                // Add on to existing argument
                currArgument.append(" ").append(words[i]);
            }
        }

        // Add last command
        if (!currArgument.toString().isEmpty()) {
            argumentsMap.put(currArgumentName, currArgument.toString().strip());
        }
    }

    /**
     * Pack words into a new argument map.
     * @param words String list of arguments/words
     */
    private HashMap<String, String> packWordsToArgumentsMap(String[] words) {
        HashMap<String, String> argumentsList = new HashMap<>();
        packCommandToExistingArgumentsMap(argumentsList, words);
        packFollowingArgumentsToExistingArgumentsMap(argumentsList, words);
        return argumentsList;
    }

    /**
     * Parses the given user input into command arguments.
     * @param line Line that a user inputs
     * @return HashMap of Arguments, mapping the argument to its value given
     */
    public HashMap<String, String> parseCommandToArgumentsMap(String line) {
        String[] words = line.split(" ");
        return packWordsToArgumentsMap(words);
    }

    /**
     * Matches the argument list to a related command and runs said command.
     * @param argumentsList List of arguments
     * @return Whether to continue running the program
     * @throws CuboydException If command fails to run
     */
    public boolean commandMatching(Ui ui, TaskList taskList, Storage storage,
                                          HashMap<String, String> argumentsList) throws CuboydException {
        switch(argumentsList.get(Parser.ARGUMENT_COMMAND)){
        case "list":
            ui.displayAllTasks(taskList);
            break;
        case "find":
            ui.displayFoundTasks(taskList, argumentsList.get(Parser.ARGUMENT_MAIN));
            break;
        case "todo":
            ui.displayAddedTask(taskList, taskList.addTodo(argumentsList.get(Parser.ARGUMENT_MAIN)));
            storage.save(taskList);
            break;
        case "deadline":
            ui.displayAddedTask(taskList,
                    taskList.addDeadline(argumentsList.get(Parser.ARGUMENT_MAIN), argumentsList.get("/by")));
            storage.save(taskList);
            break;
        case "event":
            ui.displayAddedTask(taskList, taskList.addEvent(argumentsList.get(Parser.ARGUMENT_MAIN),
                    argumentsList.get("/from"), argumentsList.get("/to")));
            storage.save(taskList);
            break;
        case "mark":
            ui.displayModifiedTask(taskList.markTask(argumentsList.get(Parser.ARGUMENT_MAIN)), "marked");
            storage.save(taskList);
            break;
        case "unmark":
            ui.displayModifiedTask(taskList.unmarkTask(argumentsList.get(Parser.ARGUMENT_MAIN)), "unmarked");
            storage.save(taskList);
            break;
        case "delete":
            ui.displayRemovedTask(taskList, taskList.deleteTask(argumentsList.get(Parser.ARGUMENT_MAIN)));
            storage.save(taskList);
            break;
        case "bye":
            ui.displayByeMessage();
            return false;
        default:
            ui.displayInvalidCommandMessage();
            break;
        }
        return true;
    }
}