import java.util.Scanner;
import java.util.Arrays;

public class V {

    private static final String LINE_SEPERATOR = "____________________________________________________________";

    public static void greet() {
        String logo = " _       _ \n"
                + "\\ \\     / / \n"
                + " \\ \\   / / \n"
                + "  \\ \\_/ / \n"
                + "   \\___/ \n";
        System.out.print(logo);
        System.out.println(LINE_SEPERATOR);
        System.out.println("Hello! I'm V");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPERATOR);
    }

    public static void displayList(String[] list, int length) {
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
    }

    public static void main(String[] args) {
        greet();

        boolean isOnline = true;
        String[] list = new String[100];
        int count = 0;
        String line;
        Scanner in = new Scanner(System.in);
        
        while (isOnline) {
            line = in.nextLine();
            if (line.equals("bye")) {
                in.close();
                isOnline = false;
            } else if (line.equals("list")) {
                displayList(list, count);
            } else {
                list[count] = line;
                count++;
                System.out.println(LINE_SEPERATOR);
                System.out.println("added: " + line);
                System.out.println(LINE_SEPERATOR);
            }
        }
        System.out.println(LINE_SEPERATOR + "\nSee ya\n" + LINE_SEPERATOR);
    }
}
