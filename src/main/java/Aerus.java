import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Aerus {

    protected static Task[] tasks = new Task[100];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        UI.greetUser();

        try {
            while (true) {
                userInput = scanner.nextLine();
                int result = InputHandler.inputHandler(userInput);
                if (result == -1) {
                    break;
                }
            }
        } catch (InvalidMarkException e) {
            System.out.println("This task does not exist, please check again.");
        }
        UI.exitProgram();
    }

}
