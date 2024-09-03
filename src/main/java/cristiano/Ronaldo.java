package cristiano;

import java.util.ArrayList;

/**
 * This class includes the main methods to handle inputs from the user.
 * The methods include special methods such as "boast" and "exclaim"
 * which represents the chatbot's personality.
 * Each task is represented as a goal, which includes a goal number and completion.
 */
public class Ronaldo {
    private static final ArrayList<Goal> goals = new ArrayList<>();

    public void greet() {
        String indent = "------------------------------------------------------------------------------ \n";
        System.out.print( indent + "Hello! I'm Cristiano Ronaldo! The greatest footballer of all time.");
        exclaim();
        System.out.println("Hehehe what can I do for you?\n"  + indent);
    }

    public void exit() {
        System.out.print("Bye. Hope to see you soon! ");
        exclaim();
        System.exit(0);
    }

    /**
     * Prints a boastful message of Ronaldo comparing himself to his
     * fellow opponent in football, Messi.
     */
    public void boast() {
        System.out.println("Oh please, I'm better than him. *Yawns*");
    }

    /**
     * Prints a popular quote from Ronaldo, which is commonly used in his celebrations.
     * "Siu" is a word in Spanish, which translates to "yes" in english.
     */
    public void exclaim() {
        System.out.println("SIUUUUUUU!");
    }

    /**
     * This method handles the marking and unmarking of a task/goal.
     * The valid format to successfully mark or unmark a goal is: [command] [integer]
     * Where command = mark/unmark, followed by a single space, followed by a valid integer.
     * If an invalid format is used, a NumberFormatException message will be prompted.
     * If the integer provided is out of bounds, an IndexOutOfBoundsException message will be prompted.
     * If the user tries to mark or unmark a goal that has already been marked or unmarked,
     * this method from the chatbot will remind the user its goal completion status.
     * Goals completed will be marked as 'X'.
     *
     * @param input The goal index to be marked or unmarked;
     */
    public void handleGoal(String[] input) {
        try {
            if (input.length <= 1) {
                System.out.println("Invalid format! Please enter the command, followed by a space, then a valid integer.\n");
                return;
            }
            int taskNumber = Integer.parseInt(input[1]) - 1;
            Goal goal = goals.get(taskNumber);
            if (input[0].equals("mark")) {
                if (goal.isDone()) {
                    System.out.println("Goal is already marked!\n" + " ");
                    return;
                }
                System.out.println("SIUUU! Congrats, one step closer to achieving your dreams! This goal is now achieved:");
                goal.markAsDone();
                System.out.println(" " + "[" + goal.getStatusIcon() + "] " + goal.description + "\n");
            } else if (input[0].equals("unmark")) {
                if (!goal.isDone()) {
                    System.out.println("Goal is already unmarked!\n");
                    return;
                }
                System.out.println("Ronaldo is disappointed in you. Work harder! This goal is now yet to achieve:");
                goal.markAsUndone();
                System.out.println(" " + "[" + goal.getStatusIcon() + "] " + goal.description + "\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Please enter the command, followed by a single space, then a valid integer.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Goal number is out of range!\n");
        }
    }

    /**
     * Prints out all the goals added, including its completion status.
     * The goal index starts from '1', instead of the common array index, '0'.
     */
    public void showListOfGoals() {
        System.out.println("Here are the goals to complete in order for you to reach your dreams:\n");
        for (int i = 0; i < goals.size(); i++) {
            System.out.println(i + 1 + ".[" + goals.get(i).getStatusIcon() + "] " + goals.get(i).description);
        }
        System.out.println(); //Indentation
    }

    /**
     * Adds a goal to a list called goals.
     *
     * @param words The description of the goal to be added;
     */
    public void addGoal(String words) {
        System.out.println("Your goal has been added: " + words + "\n");
        Goal t = new Goal(words);
        goals.add(t);
    }

}