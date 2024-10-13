import java.util.Scanner;

public class KenChat {
    public static void printLine() {
        String line = "____________________________________" ;
        System.out.println(line);
    }

    public static void startProgramme() {
        String chatBotName = "KenChat";
        printLine();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void endProgramme() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void displayOutput(String display){
        printLine();
        System.out.println(display);
        printLine();
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        boolean running = true;
        startProgramme();
        while (running){
            System.out.println();
            String str= sc.nextLine();
            if (str.equalsIgnoreCase("bye")  )
                running = false;
            displayOutput(str);
        }
        endProgramme();
    }
}
