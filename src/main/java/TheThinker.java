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
        switch(userAction){

        case "mark" :
            int numberToMark = UserInputParser.parseMarkAndUnmarkTask();
            Task.setAsDone(numberToMark);
            break;

        case "unmark" :
            int numberToUnmark = UserInputParser.parseMarkAndUnmarkTask();
            Task.setAsNotDone(numberToUnmark);
            break;

        case "todo" :
            Task.addTask(UserInputParser.parseTask());
            break;

        case "event" :
            Task.addTask(UserInputParser.parseEvent());
            break;

        case "deadline" :
            Task.addTask(UserInputParser.parseDeadline());
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
    }

}
