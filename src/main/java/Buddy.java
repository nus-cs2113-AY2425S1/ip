import java.util.Scanner;

public class Buddy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hi Daddy, I'm Buddy");
        System.out.println(" What can I do for you? ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true)
        {
            input = scanner.nextLine();
            if (input.equals("bye"))
            {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println("     " + input);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________"); //first line after "bye"
        System.out.println(" Bye Daddy. I will miss you (>ᴗ•) !");
        System.out.println("____________________________________________________________"); //second line after "bye"
    }
}
