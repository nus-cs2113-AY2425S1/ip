import java.util.Scanner;
import java.util.Arrays;

public class Iris {
    public static String[] list = new String[100];
    public static int numInList = 0;

    public static void printDivider() {
        System.out.println("---------------------------------------------");
    }

    public static boolean printText(String text) {
        printDivider();
        boolean isEnded = false;
        if (text.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            isEnded = true;
        } else if (text.equalsIgnoreCase("list")) {
            String[] listWithoutNull = Arrays.copyOf(list, numInList);
            for (int i = 0; i < listWithoutNull.length; i++) {
                System.out.println(i + ": " + listWithoutNull[i]);
            }
        } else {
            list[numInList] = text;
            System.out.println("Added: " + text);
            numInList++;
        }
        printDivider();
        return isEnded;
    }
    public static void main(String[] args) {
        // Greets
        System.out.println("Hello! I'm Iris!\n" + "What can I do for you?");
        printDivider();
        boolean isEnded = false;
        while (!isEnded) {
            Scanner in = new Scanner(System.in); // Initialisation
            String line = in.nextLine();
            isEnded = printText(line);
        }
    }
}
