import java.util.Scanner;

public class Mel {
    public static void main(String[] args) {
        System.out.println("\t________________________________________");
        System.out.println("\tHello! I'm Mel");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t________________________________________");

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            System.out.println("\t________________________________________");


            if (line.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t________________________________________");
                break;
            } else {
                System.out.println("\t" + line);
                System.out.println("\t________________________________________");
            }
        }
    }
}
