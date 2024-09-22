package TheThinker.Ui;

import TheThinker.Exceptions.FormattingException;
import TheThinker.File.NewFile;
import TheThinker.Parser.UserInputParser;
import TheThinker.Tasks.TaskList;
import java.io.IOException;

public class CommandLine {

    public static void pollForUserInput(NewFile data) {
        String userInput;
        do{
            userInput = getUserInputAndDoTask();
            try {
                data.writeTaskToFile();
            } catch (IOException e) {
                System.out.println("Failed to write task to file");
            }
        }while(!userInput.equals("bye"));
    }

    public static String getUserInputAndDoTask() {
        String userInput;
        userInput = UserInputParser.getUserInput();
        String userAction = UserInputParser.parseUserAction();
        doTaskAccordingToUserAction(userAction);
        return userInput;
    }

    public static void doTaskAccordingToUserAction(String userAction){
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
}
