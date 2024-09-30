import java.util.ArrayList;
import exception.InvalidMarkException;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Aerus {

    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        DataManager dataManager = new DataManager("./data/data.txt");
        tasks = dataManager.loadData();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
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

        dataManager.saveData();
        UI.exitProgram();
    }

}
