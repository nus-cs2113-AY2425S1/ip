import java.util.Scanner;
import java.lang.Integer;
public class Sirius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------" );
        System.out.print("""
                Hello! I'm Sirius!
                What can I do for you?
                -----------------------
                """);
        String[] list = new String[100];
        int taskCounter = 0;
        while (true) {
            String userInput = scanner.nextLine();
            if (!userInput.equals("bye")) {
                if (userInput.equals("list")){
                    System.out.println("-----------------------");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.print(i + 1 + ". ");
                        System.out.println(list[i]);
                    }
                    System.out.println("-----------------------\n");
                }
                else {
                    System.out.println("-----------------------");
                    list[taskCounter++] = userInput;
                    System.out.println("added: " + userInput);
                    System.out.println("-----------------------\n");
                }
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

