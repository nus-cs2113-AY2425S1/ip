import java.util.Scanner;

public class Poirot {

    public static void echo(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I'm POIROT\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
        boolean working = true;
        Scanner scan = new Scanner(System.in);
        while (working) {
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                working = false;
            } else {
                echo(input);
            }
        }
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }
}