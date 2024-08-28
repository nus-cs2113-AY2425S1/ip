import java.util.Scanner;

public class Mel {
    public static void main(String[] args) {
        System.out.println("________________________________________");
        System.out.println("Hello! I'm Mel");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            System.out.println("________________________________________");


            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                break;
            } else {
                System.out.println(line);
                System.out.println("________________________________________");
            }
        }
    }
}
