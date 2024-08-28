import java.util.Scanner;

public class lovespiritual {
    public static void main(String[] args) {
        String line = "________________________________________________________";
        Scanner in = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! I'm lovespiritual");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }
    }
}
