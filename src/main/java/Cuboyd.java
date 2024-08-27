import java.util.Scanner;

public class Cuboyd {
    public static void main(String[] args) {
        String name = "Cuboyd";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        // Command Entry
        String line;
        Scanner sc = new Scanner(System.in);
        boolean isAskingInput = true;
        while (isAskingInput){
            System.out.print("> ");
            line = sc.nextLine();
            switch(line){
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isAskingInput = false;
                    break;
                default:
                    System.out.println(line);
                    break;
            }
        }
    }
}