package cristiano;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class includes the main methods to handle inputs from the user.
 * The methods include special methods such as "boast" and "exclaim"
 * which represents the chatbot's personality.
 * Each task is represented as a goal, which includes a goal number and completion.
 */
public class Ronaldo {
    private static final ArrayList<Goal> goals = new ArrayList<>();
    private final Storage storage;

    public Ronaldo(Storage storage) {
        this.storage = storage;
        try {
            goals.addAll(storage.loadGoals());
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    public void saveGoals() {
        try {
            storage.saveGoals(goals);
        } catch (IOException e) {
            System.out.println("Failed to save goals.");
        }
    }

    public void greet() {
        String indent = "------------------------------------------------------------------------------ \n";
        System.out.print(indent + "Hello! I'm Cristiano Ronaldo! The greatest footballer of all time.");
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
            System.out.println("""
                    Invalid format! Please enter either of these commands in this format without '<>':\s
                    <bye>\s
                    <list>\s
                    <mark> <goal number>\s
                    <unmark> <goal number>\s
                    <event> <description> /from <time> /to <time>\s
                    <todo> <description>\s
                    <deadline> <description> /by <time>\s
                    """);
            break;
        case "Empty":
            System.out.println("Woops, your input is empty, just like Spurs' trophy cabinet.\n");
            break;
        case "Range":
            System.out.println("Goal number is out of range!\n");
            break;
        case "Mark/Unmark":
            System.out.println("Invalid mark format! Please use: <mark>/<unmark> <goal number>\n");
            break;
        case "Marked":
            System.out.println("Goal is already marked!\n" + " ");
            break;
        case "Unmarked":
            System.out.println("Goal is already unmarked!\n" + " ");
            break;
        case "Event":
            System.out.println("Invalid event format! Please use: <event> <description> /from <time> /to <time>\n");
            break;
        case "Deadline":
            System.out.println("Invalid deadline format! Please use: <deadline> <description> /by <time>\n");
            break;
        case "Todo":
            System.out.println("Invalid todo format! Please use: <todo> <description>\n");
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
                reject("Mark/Unmark");
                return;
            }
            int goalNumber = Integer.parseInt(input[1]) - 1;
            Goal goal = goals.get(goalNumber);
            if (input[0].equals("mark")) {
                goal.markAsDone(this);
                saveGoals();
            } else if (input[0].equals("unmark")) {
                goal.markAsUndone(this);
                saveGoals();
            }
        } catch (NumberFormatException e) {
            reject("Mark/Unmark");
        } catch (IndexOutOfBoundsException e) {
            reject("Range");
        }
    }

    /**
     * Prints out all the goals added, including its type and completion status.
     * The goal index starts from '1', instead of the common array index, '0'.
     */
    public void showListOfGoals() {
        System.out.println("Here are the goals to complete in order for you to reach your dreams:");
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

    public void addEvent(String input) {
        String[] parts = input.split("/from | /to ", 3);
        try {
            String description = parts[0];
            String from = parts[1];
            String to  = parts[2];
            if (description.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
                reject("Event");
                return;
            }
            Event event = new Event(description, from, to);
            goals.add(event);
            System.out.println("GOALLL! Your event has been added: \n" + event + "\n");
            printGoalCount();
            saveGoals();
        } catch (IndexOutOfBoundsException e) {
            reject("Event");
        }
    }

    public void addTodo(String input) {
        String[] parts = input.split("todo", 1);
        try {
            String description = parts[0];
            if (description.trim().isEmpty()) {
                reject("Todo");
                return;
            }
            Todo todo = new Todo(parts[0]);
            goals.add(todo);
            System.out.println("GOALLL! Your todo has been added: \n" + todo + "\n");
            printGoalCount();
            saveGoals();
        } catch (IndexOutOfBoundsException e) {
            reject("Todo");
        }
    }

    public void addDeadline(String input) {
        String[] parts = input.split("deadline | /by", 2);
        try {
            String description = parts[0];
            String by = parts[1];
            if (description.trim().isEmpty() || by.trim().isEmpty()) {
                reject("Deadline");
                return;
            }
            Deadline deadline = new Deadline(description,by);
            goals.add(deadline);
            System.out.println("GOALLL! Your deadline has been added: \n" + deadline + "\n");
            printGoalCount();
            saveGoals();
        } catch (IndexOutOfBoundsException e) {
            reject("Deadline");
        }
    }

    public void delete(String input) {
        try {
            int goalNumber = Integer.parseInt(input) - 1;
            System.out.println("VAR disallowed your goal: \n" + goals.get(goalNumber).toString());
            goals.remove(goalNumber);
            printGoalCount();
            saveGoals();
        } catch (NumberFormatException e) {
            reject("Mark/Unmark");
        } catch (IndexOutOfBoundsException e) {
            reject("Range");
        }
    }


}