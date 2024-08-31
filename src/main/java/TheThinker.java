import java.util.Scanner;

public class TheThinker {

    public static final String NAME = "TheThinker";

    public static void main(String[] args) {

        printGreeting();
        Scanner in = new Scanner(System.in);
        String userInput;

        do{
            userInput = in.nextLine();
            printSeparation();

            String userAction = getUserActionFromSentence(userInput);
            String[] actionAndItemNumber;
            UserInputParser userInputParser = new UserInputParser(userInput);

            switch(userAction){

            case "mark" :
                actionAndItemNumber = userInput.split(" ");
                Task.setAsDone(Integer.parseInt(actionAndItemNumber[1].trim()));
                break;

            case "unmark" :
                actionAndItemNumber = userInput.split(" ");
                Task.setAsNotDone(Integer.parseInt(actionAndItemNumber[1].trim()));
                break;

            case "todo" :
                Task.addTask(userInputParser.parseTask());
                break;

            case "event" :
                Task.addTask(userInputParser.parseEvent());
                break;

            case "deadline" :
                Task.addTask(userInputParser.parseDeadline());
                break;

            case "bye":
                printBye();
                break;

            case "list":
                Task.listTasks();
                break;

            default:
                System.out.println("try again with the correct format");
                break;
            }
            printSeparation();

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

    public static String getUserActionFromSentence(String userInput){
        String[] wordsInUserInput = userInput.split(" ");
        return wordsInUserInput[0];
    }

}
