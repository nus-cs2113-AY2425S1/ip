package TheThinker.Ui;

import TheThinker.Exceptions.FormattingException;
import TheThinker.File.NewFile;
import TheThinker.Parser.UserInputParser;
import TheThinker.Tasks.TaskList;
import java.io.IOException;

/**
 * CommandLine handles all the commandLine related method
 */
public interface CommandLine {

    /**
     * Checks for user input and do corresponding command
     * and write task to file till bye command.
     *
     * @param data File created to accept task input
     */
    static void pollForUserInputTillBye(NewFile data , boolean isWriteTask) {
        String userInput;
        do{
            userInput = getUserInputAndDoTask();
            if(isWriteTask) {
                try {
                    data.writeTaskToFile();
                } catch (IOException e) {
                    System.out.println("Failed to write task to file");
                }
            }
        }while(!userInput.equals("bye"));
    }

    /**
     * Checks for user input and do corresponding command and write task to file.
     *
     * @return the original trimmed user input
     */
    private static String getUserInputAndDoTask() {
        String userInput;
        userInput = UserInputParser.getUserInput();
        String userAction = UserInputParser.parseUserAction();
        doTaskAccordingToUserAction(userAction);
        return userInput;
    }

    /**
     * Do corresponding actions depending on the command by the user.
     *
     * @param userAction the first word of the user input.
     */
    private static void doTaskAccordingToUserAction(String userAction){
        UiControl.printSeparation();
        try {
            switch (userAction) {

            case "mark":
                int numberToMark = UserInputParser.parseNumberAfterTask();
                TaskList.setAsDone(numberToMark);
                break;

            case "unmark":
                int numberToUnmark = UserInputParser.parseNumberAfterTask();
                TaskList.setAsNotDone(numberToUnmark);
                break;

            case "delete" :
                int numberToDelete = UserInputParser.parseNumberAfterTask();
                TaskList.deleteTask(numberToDelete);
                break;

            case "todo":
                TaskList.addTask(UserInputParser.parseTodo());
                break;

            case "event":
                TaskList.addTask(UserInputParser.parseEvent());
                break;

            case "deadline":
                TaskList.addTask(UserInputParser.parseDeadline());
                break;

            case "get":
                String date = UserInputParser.parseDateAfterGet();
                TaskList.listTasksOfDate(date);
                break;

            case "find":
                String keyword = UserInputParser.parseKeywordAfterFind();
                TaskList.listTasksOfKeyword(keyword);
                break;

            case "bye":
                UiControl.printBye();
                break;

            case "list":
                TaskList.listTasks();
                break;

            case "help":
                UiControl.printHelp();
                break;

            default:
                UiControl.printCommands();
                break;
            }
            UiControl.printSeparation();

        }catch(FormattingException e){
            e.printErrorMessage();

        }catch(IndexOutOfBoundsException e){
            System.out.println("try again with the correct format");

        }catch (NumberFormatException e){
            System.out.println("The task number after [mark] is not a number / not in the correct format");

        }
    }

    public static boolean getWriteTaskInput() {
        System.out.println("Do you want to save your tasks to a file? yes / no");
        String userInput = UserInputParser.getUserInput();
        if(userInput.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }
}
