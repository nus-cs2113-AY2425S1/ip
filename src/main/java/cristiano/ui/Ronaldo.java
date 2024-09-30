package cristiano.ui;

import cristiano.goals.Deadline;
import cristiano.goals.Event;
import cristiano.goals.Goal;
import cristiano.goals.Todo;
import cristiano.goals.GoalList;
import cristiano.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * This class is considered as the user interface of the application.
 * which includes the main methods to handle inputs from the user.
 * The methods include special methods such as "boast" and "exclaim"
 * which represents the chatbot's personality.
 * Each task is represented as a goal, which includes a goal number and completion status.
 */
public class Ronaldo {
    private final GoalList goalList;
    private final Storage storage;

    /**
     * Goals will be loaded from a data file before being added into the program's goal list.
     * If no previous data is found, the program will create a new file,
     * named "data", and will be started afresh.
     *
     * @param storage The data file in the computer, if it exists.
     */
    public Ronaldo(Storage storage) {
        this.storage = storage;
        this.goalList = new GoalList();
        try {
            goalList.getAllGoals().addAll(storage.loadGoals());
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    public void saveGoals() {
        try {
            storage.saveGoals(goalList.getAllGoals());
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

    /**
     * Provides full list of commands and formats that the chatbot is able to recognise.
     */
    public void help() {
        System.out.println("""
                    Please enter either of these commands in this format without '<>':\s
                    <bye>\s
                    <list>\s
                    <delete> <Goal Number>\s
                    <mark> <Goal Number>\s
                    <unmark> <Goal Number>\s
                    <find> <Goal Number>\s
                    <event> <Description> /from <Time> /to <Time>\s
                    <todo> <Description>\s
                    <deadline> <Description> /by <Time>\s
                    """);
    }

    public void reject(String words) {
        switch (words) {
        case "Format":
            System.out.println("Invalid format! Please enter 'help' for full list of commands and formats.\n");
            break;
        case "Empty":
            System.out.println("Woops, your input is empty, just like Spurs' trophy cabinet.\n");
            break;
        case "Range":
            System.out.println("Goal number is out of range!\n");
            break;
        case "Mark/Unmark":
            System.out.println("Invalid mark format! Please use: <mark>/<unmark> <Goal number>\n");
            break;
        case "Event":
            System.out.println("Invalid event format! Please use: <event> <Description> /from <Time> /to <Time>\n");
            break;
        case "Deadline":
            System.out.println("Invalid deadline format! Please use: <deadline> <Description> /by <Time>\n");
            break;
        case "Todo":
            System.out.println("Invalid todo format! Please use: <todo> <Description>\n");
            break;
        case "Delete":
            System.out.println("Invalid delete format! Please use: <delete> <Goal number>\n");
            break;
        default:
            break;
        }
    }

    /**
     * This method handles the marking and unmarking of a task/cristiano.goals.
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
            Goal goal = goalList.getGoalNumber(goalNumber);
            String command = input[0];
            if (command.equals("mark")) {
                goal.markAsDone();
                saveGoals();
            } else if (command.equals("unmark")) {
                goal.markAsUndone();
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
        if (goalList.getSize() == 0) {
            reject("Empty");
            return;
        }
        goalList.printGoals();
    }

    /**
     * Adds a type of goal called Event, into the goal list.
     * Deadlines follow the format: <event> <Description> /from <Time> /to <Time>
     *
     * @param words contains event to be added.
     */
    public void addEvent(String words) {
        String[] input = words.split("event | /from | /to ", 3);
        try {
            String description = input[0];
            String from = input[1];
            String to  = input[2];
            if (description.trim().isEmpty() || from.trim().isEmpty() || to.trim().isEmpty()) {
                reject("Event");
                return;
            }
            Event event = new Event(description, from, to);
            goalList.addGoal(event);
            System.out.println("GOALLL! Your event has been added: \n" + event + "\n");
            saveGoals();
        } catch (IndexOutOfBoundsException e) {
            reject("Event");
        }
    }

    /**
     * Adds a type of goal called Todo, into the goal list.
     * Deadlines follow the format: <todo> <Description>
     *
     * @param words contains todo to be added.
     */
    public void addTodo(String words) {
        String[] input = words.split("todo", 2);
        try {
            String description = input[0];
            if (description.trim().isEmpty()) {
                reject("Todo");
                return;
            }
            Todo todo = new Todo(input[0]);
            goalList.addGoal(todo);
            System.out.println("GOALLL! Your todo has been added: \n" + todo + "\n");
            saveGoals();
        } catch (IndexOutOfBoundsException e) {
            reject("Todo");
        }
    }

    /**
     * Adds a type of goal called Deadline, into the goal list.
     * Deadlines follow the format: <deadline> <Description> /by <Time>
     *
     * @param words contains deadline to be added.
     */
    public void addDeadline(String words) {
        String[] input = words.split("deadline | /by", 2);
        try {
            String description = input[0];
            String by = input[1];
            if (description.trim().isEmpty() || by.trim().isEmpty()) {
                reject("Deadline");
                return;
            }
            Deadline deadline = new Deadline(description,by);
            goalList.addGoal(deadline);
            System.out.println("GOALLL! Your deadline has been added: \n" + deadline + "\n");
            saveGoals();
        } catch (IndexOutOfBoundsException e) {
            reject("Deadline");
        }
    }

    /**
     * Deletes a goal in the list.
     *
     * @param words contains goal index of the goal to be deleted.
     */
    public void delete(String words) {
        try {
            String[] input = words.split("delete", 2);
            int goalNumber = Integer.parseInt(input[1]) - 1;
            Goal goal = goalList.getGoalNumber(goalNumber);
            System.out.println("VAR disallowed your goal: \n" + goal.toString());
            goalList.deleteGoal(goalNumber);
            saveGoals();
        } catch (NumberFormatException e) {
            reject("Delete");
        } catch (IndexOutOfBoundsException e) {
            reject("Range");
        }
    }

    /**
     * Works hand in hand with GoalList's method findMatchingGoals().
     * However, keyword cannot be completely empty (I.e, Not even spaces are included for keyword).
     *
     * @param words contains keywords that are to be matched.
     * returns list of matching goals. A prompt will be given if list is empty.
     */
    public void find(String words) {
        String[] input = words.split("find", 2);
        try {
            String keyword = input[1];
        } catch (IndexOutOfBoundsException e) {
            reject("Empty");
            return;
        }
        String keyword = input[1];
        List<Goal> matchingGoals = goalList.findMatchingGoals(keyword);
        if (matchingGoals.isEmpty()) {
            System.out.println("Woops, no matching goals found.\n");
        } else {
            System.out.println("Here are the matching goals found in the list:\n");
            for (Goal goal : matchingGoals) {
                System.out.println(goal);
            }
            System.out.println("\n");
        }
    }

}
