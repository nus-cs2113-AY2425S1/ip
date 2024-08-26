import java.util.Scanner;

public class TheThinker {

    public static String name = "TheThinker";

    public static void main(String[] args) {
        separation();
        greet();
        separation();
        Scanner in = new Scanner(System.in);
        String userInput;
        List toDoList = new List();

        do{
            userInput = in.nextLine();
            separation();
            switch(userInput){
            case "bye":
                bye();
                break;

            case "list" :
                toDoList.listItems();
                break;

            default:
                toDoList.addItem(userInput);
                System.out.println("added: " + userInput);
                break;
            }
            separation();

        }while(!userInput.equals("bye"));
    }

    public static void greet(){
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void separation(){
        System.out.println("____________________________________________________________");
    }

}
