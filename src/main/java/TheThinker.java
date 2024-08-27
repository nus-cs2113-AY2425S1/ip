import java.util.Scanner;

public class TheThinker {

    public static String name = "TheThinker";

    public static void main(String[] args) {
        printSeparation();
        printGreeting();
        printSeparation();
        Scanner in = new Scanner(System.in);
        String userInput;
        List toDoList = new List();

        do{
            userInput = in.nextLine();
            printSeparation();

            if(hasMarkOrUnmark(userInput)){
                String[] actionAndItemNumber = userInput.split(" ");
                if(actionAndItemNumber[0].trim().equalsIgnoreCase("mark")){
                    toDoList.setAsDone(Integer.parseInt(actionAndItemNumber[1].trim()));
                    continue;
                }else if(actionAndItemNumber[0].trim().equalsIgnoreCase("unmark")){
                    toDoList.setAsNotDone(Integer.parseInt(actionAndItemNumber[1].trim()));
                    continue;
                }
            }

            switch(userInput){
            case "bye":
                printBye();
                break;

            case "list" :
                toDoList.listItems();
                break;

            default:
                toDoList.addItem(userInput);
                System.out.println("added: " + userInput);
                break;
            }
            printSeparation();

        }while(!userInput.equals("bye"));
    }

    public static void printGreeting(){
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printSeparation(){
        System.out.println("____________________________________________________________");
    }

    public static boolean hasMarkOrUnmark(String userInput){
        return(userInput.contains("mark") || userInput.contains("unmark"));
    }

}
