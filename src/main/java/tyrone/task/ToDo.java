package tyrone.task;

import tyrone.constants.Constants;

/**
 * Todo specific functions.
 */

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static void createToDo(String userInput) {
        // Add the new ToDo to the ArrayList
        ToDo newToDo = new ToDo(userInput.substring(5));
        Constants.toDoList.add(newToDo);

        System.out.println(Constants.LINE);
        System.out.println("    yeye. I've added this task:");
        System.out.println("      [T][ ] " + newToDo.getDescription());
        System.out.println("    Now you have " + Constants.toDoList.size() + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
}
