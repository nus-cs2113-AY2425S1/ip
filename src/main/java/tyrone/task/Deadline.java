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
    
        // Check if the description part exists and is long enough
        if (parts[0].length() < 9) {
            throw new WrongDeadlineFormatException();
        }
    
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingTimeInfoException();
        }
    
        String description = parts[0].substring(9); // Extracting description after "deadline "
        String by = parts[1].trim(); // Extracting the due date
    
        Deadline deadlineTask = new Deadline(description, by);
        Constants.toDoList.add(deadlineTask);
        Task.listCount++;
    
        System.out.println(Constants.LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      [D][ ] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDoBy() + ")");
        System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
    
}
