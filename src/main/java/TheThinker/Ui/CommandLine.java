package TheThinker.Ui;

import TheThinker.Exceptions.FormattingException;
import TheThinker.File.NewFile;
import TheThinker.Parser.*;
import TheThinker.Tasks.TaskList;
import java.io.IOException;

/**
 * CommandLine Class handles all the commandLine related method
 */
public interface CommandLine {

    /**
     * Checks for user input and do corresponding command
     * and write task to file till bye command.
     *
     * @param data File created to accept task input
     */
    static void pollForUserInputTillBye(NewFile data , boolean isWriteTask) {
        String userAction;
        do{
            userAction = getUserActionAndDoTask();
            if(isWriteTask) {
                try {
                    data.writeTaskToFile();
                } catch (IOException e) {
                    System.out.println("Failed to write task to file");
                }
            }
        }while(!userAction.equalsIgnoreCase("bye"));
    }

    /**
     * Checks for user input and do corresponding command and write task to file.
     *
     * @return the original trimmed user input
     */
    private static String getUserActionAndDoTask() {
        String userInput = UserInputParser.getUserInput();
        String userAction = UserInputParser.parseUserAction();
        doTaskAccordingToUserAction(userAction);
        return userAction;
    }

    /**
     * Do corresponding actions depending on the command by the user.
     *
     * @param userAction the first word of the user input.
     */
    private static void doTaskAccordingToUserAction(String userAction){
        UiControl.printSeparation();
        try {
            switch (userAction.toLowerCase()) {

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
                TaskList.addTask(TodoParser.parseTodo());
                break;

            case "event":
                TaskList.addTask(EventParser.parseEvent());
                break;

            case "deadline":
                TaskList.addTask(DeadlineParser.parseDeadline());
                break;

            case "get":
                String date = DateParser.parseDateAfterGet();
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
            System.out.println("The task to " + userAction.toLowerCase() + " is outside of number of list" );

        }catch (NumberFormatException e){
            System.out.println("The task number after " + userAction.toLowerCase() +  " is not a number / not in the correct format");

        }
    }

    static boolean getSaveTaskInput() {
        System.out.println("Do you want to save your tasks to a file? yes / no");
        String userInput = UserInputParser.getUserInput();
        return userInput.equalsIgnoreCase("yes");
    }
}
