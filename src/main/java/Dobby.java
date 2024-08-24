import java.util.Scanner;

public class Dobby {
    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________";
        Scanner in = new Scanner(System.in);
        String line;

        System.out.println(lineBreak);
        System.out.println("Hello! I'm Dobby!");
        System.out.println("What can I do for you?");
        System.out.println(lineBreak);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")){
            System.out.println("  " + lineBreak);
            System.out.println("    " + line);
            System.out.println("  " + lineBreak);
            line = in.nextLine();
        }

        System.out.println("  " + lineBreak);
        System.out.println("    " + "Bye. Hope to see you again soon!");
        System.out.println("  " + lineBreak);

    }
}
