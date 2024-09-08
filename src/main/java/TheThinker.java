import java.sql.SQLOutput;
import java.text.ParseException;

public class TheThinker {

    public static final String NAME = "TheThinker";

    public static void main(String[] args) {

        printGreeting();
        String userInput;

        do{
            userInput = UserInputParser.getUserInput();
            String userAction = UserInputParser.parseUserAction();
            doTaskAccordingToUserAction(userAction);

        }while(!userInput.equals("bye"));
    }

    public static void printGreeting(){
        printSeparation();
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printSeparation();
    }

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printSeparation(){
        System.out.println("____________________________________________________________");
    }

    public static void doTaskAccordingToUserAction(String userAction){
        printSeparation();
        try {
            switch (userAction) {

            case "mark":
                int numberToMark = UserInputParser.parseMarkAndUnmarkTask();
                Task.setAsDone(numberToMark);
                break;

            case "unmark":
                int numberToUnmark = UserInputParser.parseMarkAndUnmarkTask();
                Task.setAsNotDone(numberToUnmark);
                break;

            case "todo":
                Task.addTask(UserInputParser.parseTodo());
                break;

            case "event":
                Task.addTask(UserInputParser.parseEvent());
                break;

            case "deadline":
                Task.addTask(UserInputParser.parseDeadline());
                break;

            case "bye":
                printBye();
                break;

            case "list":
                Task.listTasks();
                break;

            case "help":
                System.out.println("Formats for the commands are : ");
                System.out.println("mark : mark [number]");
                System.out.println("unmark : unmark [number]");
                System.out.println("todo : todo [task]");
                System.out.println("event : event [task] /from [start time] /by [end time]");
                System.out.println("deadline : deadline [task] /by [time]");
                break;

            default:
                System.out.println("Command entered is not valid. Available commands are");
                String[] commands = {"mark" , "unmark" , "todo" , "event" , "deadline" , "list" , "bye" , "help (get format)"};
                for(String command : commands){
                    System.out.println("- " + command);
                }
                break;
            }
            printSeparation();
        }catch(FormattingError e){
            e.printErrorMessage();
        }catch(IndexOutOfBoundsException e){
            System.out.println("try again with the correct format");
        }catch (NumberFormatException e){
            System.out.println("The task number after [mark] is not a number / not in the correct format");
        }
    }

}
