package TheThinker.Ui;

import TheThinker.Exceptions.FormattingException;
import TheThinker.File.FileLoader;
import TheThinker.File.NewFile;
import TheThinker.Parser.*;
import TheThinker.Tasks.TaskList;
import java.io.IOException;

public interface CommandLine {

    /**
     * Checks for user input and do corresponding command
     * and write task to file till bye command.
     */
    static void pollForUserInputTillBye(boolean isWriteTask) {
        String userAction;
        NewFile dataFile = FileLoader.inputFile;
        do{
            userAction = getUserActionAndDoTask();
            if(isWriteTask) {
                try {
                    dataFile.writeTaskListToFile();
                } catch (IOException e) {
                    System.out.println("Failed to write task to file");
                }
            }
        }while(!userAction.equalsIgnoreCase("bye"));
    }

    /**
     * Checks for user input and do corresponding command and write task to file.
     *
     * @return the original trimmed user action
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
                int numberToMark = UserInputParser.parseNumberAfterTask("mark");
                TaskList.setAsDone(numberToMark);
                break;

            case "unmark":
                int numberToUnmark = UserInputParser.parseNumberAfterTask("unmark");
                TaskList.setAsNotDone(numberToUnmark);
                break;

            case "delete" :
                int numberToDelete = UserInputParser.parseNumberAfterTask("delete");
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
}
