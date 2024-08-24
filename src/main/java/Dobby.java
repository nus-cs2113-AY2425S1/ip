import java.util.Scanner;

public class Dobby {
    private static final String DASH_LINE = "____________________________________________________________";
    private static final int MAX_LIST_SIZE = 100;
    private static final String[] list = new String[MAX_LIST_SIZE];
    private static int listSize = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean saidBye = false;

        printWelcomeMessage();

        while (!saidBye){
            String line = in.nextLine();
            if (line.equalsIgnoreCase("bye")){
                saidBye = true;
            } else if (line.equals("list")) {
                printList();
            } else{
                addItem(line);
            }
        }

        printGoodbyeMessage();
    }

    private static void printWelcomeMessage(){
        System.out.println("  " + DASH_LINE);
        System.out.println("    " + "Hello! I'm Dobby!");
        System.out.println("    " + "What can I do for you, wizard?");
        System.out.println("  " + DASH_LINE);
    }

    private static void printList(){
        System.out.println("  " + DASH_LINE);
        for (int i = 1; i <= listSize; i++) {
            System.out.println("    " + i + ". " + list[i - 1]);
        }
        System.out.println("  " + DASH_LINE);
    }

    private static void addItem(String line){
        list[listSize] = line;
        listSize++;
        System.out.println("  " + DASH_LINE);
        System.out.println("    added: " + line);
        System.out.println("  " + DASH_LINE);
    }

    private static void printGoodbyeMessage(){
        System.out.println("  " + DASH_LINE);
        System.out.println("    " + "Hohoho. Hope to see you again soon!");
        System.out.println("  " + DASH_LINE);
    }

}
