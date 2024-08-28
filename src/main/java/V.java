import java.util.Scanner;

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
    public static void main(String[] args) {
        greet();

        boolean isOnline = true;
        String line;
        Scanner in = new Scanner(System.in);
        
        while (isOnline) {
            line = in.nextLine();
            if (line.equals("bye")) {
                in.close();
                isOnline = false;
            } else {
                System.out.println(LINE_SEPERATOR);
                System.out.println(line);
                System.out.println(LINE_SEPERATOR);
            }
        }
        System.out.println(LINE_SEPERATOR + "\nSee ya");
    }
}
