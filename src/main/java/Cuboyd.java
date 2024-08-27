import java.util.ArrayList;
import java.util.Scanner;

public class Cuboyd {
    public static void main(String[] args) {
        String name = "Cuboyd";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        // Initialise List
        ArrayList<String> items = new ArrayList<>();

        // Command Entry
        String line;
        Scanner sc = new Scanner(System.in);
        boolean isAskingInput = true;
        while (isAskingInput){
            System.out.print("> ");
            line = sc.nextLine();
            switch(line){
                case "list":
                    for (int currentItemIndex=0; currentItemIndex<items.size(); currentItemIndex++){
                        // String.format("%d. %s", currentItemIndex+1, items.get(currentItemIndex)));
                        System.out.println(String.valueOf(currentItemIndex+1) + ". " + items.get(currentItemIndex));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isAskingInput = false;
                    break;
                default:
                    items.add(line);
                    System.out.println("added: " + line);
                    break;
            }
        }
    }
}