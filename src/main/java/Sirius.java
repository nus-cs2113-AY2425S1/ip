import java.util.Scanner;
import java.lang.Integer;
public class Sirius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------" );
        System.out.print("Hello! I'm Sirius!\n" + "What can I do for you?\n" + "-----------------------\n" );
        while (true) {
            String userInput = scanner.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println("-----------------------");
                System.out.println(userInput);
                System.out.println("-----------------------\n");
            }
            else {
                System.out.println("-----------------------");
                System.out.println("Bye! Hope to see you soon.");
                System.out.println("-----------------------");
                break;
            }
        }
        scanner.close();
    }
}

