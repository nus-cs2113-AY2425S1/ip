import java.util.Scanner;

public class Eva {
    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Eva!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        String line;
        Scanner in = new Scanner(System.in);

        while(true)
        {
            line = in.nextLine();
            if(line.equalsIgnoreCase("Bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            }
            System.out.println(line);
            System.out.println(HORIZONTAL_LINE);
        }
    }

}
