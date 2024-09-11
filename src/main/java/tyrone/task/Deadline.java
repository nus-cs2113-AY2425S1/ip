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

        Constants.toDoList[Task.listCount] = new Deadline(parts[0].substring(9), parts[1]);
        Task.listCount++;
        System.out.println(Constants.LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      [D][ ] " + Constants.toDoList[Task.listCount - 1].getDescription() + " (by: " + ((Deadline) Constants.toDoList[Task.listCount - 1]).getDoBy() + ")");
        System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
}