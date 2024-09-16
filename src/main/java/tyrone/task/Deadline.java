package tyrone.task;

import tyrone.constants.Constants;
import tyrone.exceptions.MissingTimeInfoException;
import tyrone.exceptions.TyroneException;
import tyrone.exceptions.WrongDeadlineFormatException;

public class Deadline extends Task {
    protected String doBy;

    public Deadline(String description, String by) {
        super(description);
        this.doBy = by;
    }

    public String getDoBy() {
        return doBy;
    }

    public static void createDeadline(String userInput) throws TyroneException {
        if (!userInput.contains("/by")) {
            throw new WrongDeadlineFormatException();
        }

        String[] parts = userInput.split(" /by ");

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingTimeInfoException();
        }

        // Add the new Deadline to the ArrayList
        Deadline newDeadline = new Deadline(parts[0].substring(9), parts[1]);
        Constants.toDoList.add(newDeadline);

        System.out.println(Constants.LINE);
        System.out.println("    You better finish this deadline:");
        System.out.println("      [D][ ] " + newDeadline.getDescription() + " (by: " + newDeadline.getDoBy() + ")");
        System.out.println("    Now you have " + Constants.toDoList.size() + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
}
