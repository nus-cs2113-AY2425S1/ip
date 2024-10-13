import java.util.*;

public class KenChat {
    public static void printline() {
        String line = "____________________________________" ;
        System.out.println(line);
    }

    public static void startPrograme() {
        String chatBotName = "KenChat";
        printline();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        printline();
    }

    public static void endPrograme() {
        printline();
        System.out.println("Bye. Hope to see you again soon!");
        printline();
    }

    public static void displayOutput(String display){
        printline();
        System.out.println(display);
        printline();
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        boolean running = true;
        startPrograme();
        while (running){
            System.out.println();
            String str= sc.nextLine();
            if (str.equalsIgnoreCase("bye")  )
                running = false;
            displayOutput(str);
        }
        endPrograme();
    }
}