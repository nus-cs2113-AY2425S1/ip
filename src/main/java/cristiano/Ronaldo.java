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

    public void reject(String words) {
        switch (words) {
        case "Format":
            System.out.println("Invalid format! Please enter the command, followed by a space, then a valid integer.\n");
            break;
        case "Range":
            System.out.println("Goal number is out of range!\n");
            break;
        case "Marked":
            System.out.println("Goal is already marked!\n" + " ");
            break;
        case "Event":
            System.out.println("Invalid event format! Please use: <event> /from <time> /to <time>\n");
            break;
        case "Deadline":
            System.out.println("Invalid deadline format! Please use: <deadline> /by <time>\n");
            break;
        default:
            break;
        }
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
                System.out.println(goal + "\n");
            } else if (input[0].equals("unmark")) {
                if (!goal.isDone()) {
                    System.out.println("Goal is already unmarked!\n");
                    return;
                }
                System.out.println("Ronaldo is disappointed in you. Work harder! This goal is now yet to achieve:");
                goal.markAsUndone();
                System.out.println(goal + "\n");
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
            Goal goal = goals.get(i);
            System.out.println(i + 1 + "." + goal);
        }
        System.out.println(); //Indentation
    }

    public void printGoalCount() {
        if (goals.size() == 1) {
            System.out.println("Now you have " + goals.size() + " goal in the list.\n");
        } else {
            System.out.println("Now you have " + goals.size() + " goals in the list.\n");
        }
    }

    /**
     * Adds a goal to a list called goals.
     *
     * @param input The description of the goal to be added;
     */
    public void addGoal(String input) {
        System.out.println("Your goal has been added: " + input + "\n");
        Goal t = new Goal(input);
        goals.add(t);
    }

    public void addEvent(String input) {
        String[] parts = input.split("/from | /to ", 3);
        try {
            Event event = new Event(parts[0], parts[1], parts[2]);
            goals.add(event);
            System.out.println("GOALLL! Your event has been added: \n" + event + "\n");
            printGoalCount();
        } catch (IndexOutOfBoundsException e) {
            reject("Event");
        }
    }

    public void addTodo(String input) {
        Todo todo = new Todo(input);
        goals.add(todo);
        System.out.println("GOALLL! Your todo has been added: \n" + todo + "\n");
        printGoalCount();
    }

    public void addDeadline(String input) {
        try {
            String[] parts = input.split("/by", 2);
            Deadline deadline = new Deadline(parts[0],parts[1]);
            goals.add(deadline);
            System.out.println("GOALLL! Your deadline has been added: \n" + deadline + "\n");
            printGoalCount();
        } catch (IndexOutOfBoundsException e) {
            reject("Deadline");
        }

    }


}