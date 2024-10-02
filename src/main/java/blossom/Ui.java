package blossom;

import blossom.task.Task;
import java.util.ArrayList;

/**
 * The <code>Ui</code> class handles all user interactions, displaying messages, tasks, and other
 * information to the console.
 */
public class Ui {
    private final String horizontalLine = "____________________________________________________________";
    private final String logo =
            """
                     _______     .---.       ,-----.       .-'''-.    .-'''-.     ,-----.    ,---.    ,---.\s
                    \\  ____  \\   | ,_|     .'  .-,  '.    / _     \\  / _     \\  .'  .-,  '.  |    \\  /    |\s
                    | |    \\ | ,-./  )    / ,-.|  \\ _ \\  (`' )/`--' (`' )/`--' / ,-.|  \\ _ \\ |  ,  \\/  ,  |\s
                    | |____/ / \\  '_ '`) ;  \\  '_ /  | :(_ o _).   (_ o _).   ;  \\  '_ /  | :|  |\\_   /|  |\s
                    |   _ _ '.  > (_)  ) |  _`,/ \\ _/  | (_,_). '.  (_,_). '. |  _`,/ \\ _/  ||  _( )_/ |  |\s
                    |  ( ' )  \\(  .  .-' : (  '\\_/ \\   ;.---.  \\  :.---.  \\  :: (  '\\_/ \\   ;| (_ o _) |  |\s
                    | (_{;}_) | `-'`-'|___\\ `"/  \\  ) / \\    `-'  |\\    `-'  | \\ `"/  \\  ) / |  (_,_)  |  |\s
                    |  (_,_)  /  |        \\'. \\_/``".'   \\       /  \\       /   '. \\_/``".'  |  |      |  |\s
                    /_______.'   `--------`  '-----'      `-...-'    `-...-'      '-----'    '--'      '--'""";


    /**
     * Prints all tasks in the list.
     *
     * @param listOfTasks the list of tasks to be printed
     */
    public void printItems(ArrayList<Task> listOfTasks) {
        System.out.println(horizontalLine);
        if (listOfTasks.isEmpty()) {
            System.out.println("No tasks added! ( • ᴖ • )");
        } else {
            // Print items in order
            System.out.println("Try hard to get these tasks done~~ ");
            int orderInList = 1;
            for(Task item : listOfTasks) {
                System.out.println(orderInList+ ". " + item.toString());
                orderInList++;
            }
        }
        System.out.println(horizontalLine);
    }

    /**
     * Prints the starting up message to the user.
     */
    public void printIntro() {
        System.out.println(logo + "\n" +"Hello, I'm Blossom! ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("Your wish is my command (シ_ _ )シ");
        System.out.println(horizontalLine);
    }

    /**
     * Prints the total number of tasks currently in the list.
     *
     * @param listOfTasks the list of tasks to count
     */
    public void printNumberOfTasks(ArrayList<Task> listOfTasks) {
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Prints a goodbye message when the user exits the application.
     */
    public void printGoodbye() {
        System.out.println("Bye~~~ Come visit me soon! (๑>◡<๑)");
        System.out.println(horizontalLine);
    }


}
