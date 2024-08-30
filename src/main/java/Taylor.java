import java.util.Scanner;

public class Taylor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
