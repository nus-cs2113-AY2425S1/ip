package TheThinker.Command;

import TheThinker.Exceptions.FormattingException;
import TheThinker.NewFile.NewFile;
import TheThinker.Tasks.TaskList;
import TheThinker.Parser.UserInputParser;
import TheThinker.Ui.UiControl;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TheThinker{

    public static final String NAME = "TheThinker";

    public static void main(String[] args) {

        try {
            NewFile data = loadDefaultFileElseInputNewFile();
            UiControl.printGreeting();
            pollForUserInput(data);

        } catch (FileNotFoundException e) {
            System.out.println("Please create file before proceeding");
        }
    }

    private static void pollForUserInput(NewFile data) {
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

    private static String getUserInputAndDoTask() {
        String userInput;
        userInput = UserInputParser.getUserInput();
        String userAction = UserInputParser.parseUserAction();
        doTaskAccordingToUserAction(userAction);
        return userInput;
    }

    private static NewFile loadDefaultFileElseInputNewFile() throws FileNotFoundException {
        UiControl.printLoadingText();
        NewFile data = new NewFile("TaskContents.txt");

        if(!data.isFileExist()) {
            try {
                data = inputFileNameAndLoadFile();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. No data loaded. Please create the file under Data directory");
            }
        }else{
            data.loadFile();
        }
        return data;
    }

    public static NewFile inputFileNameAndLoadFile() throws FileNotFoundException{
        UiControl.printSeparation();
        System.out.println("Input file name you want to extract data from under the Data directory [filename.txt]");
        UiControl.printSeparation();
        String filename = UserInputParser.getUserInput();
        NewFile data = new NewFile(filename);
        data.loadFile();
        return data;
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
