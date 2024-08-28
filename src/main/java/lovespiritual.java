import java.util.Scanner;

public class lovespiritual {
    public static void main(String[] args) {
        String line = "________________________________________________________";
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;

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
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                System.out.println(line);
            } else {
                list[count] = input;
                count++;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}
