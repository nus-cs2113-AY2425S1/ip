package tyrone.task;

import tyrone.constants.Constants;
import tyrone.exceptions.MissingTimeInfoException;
import tyrone.exceptions.TyroneException;
import tyrone.exceptions.WrongEventFormatException;

public class Event extends Task {
    protected String timing;

    public Event(String description, String time) {
        super(description);
        this.timing = time;
    }

    public String getTiming() {
        return timing;
    }

    public static void createEvent(String userInput) throws TyroneException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new WrongEventFormatException();
        }

        String[] parts = userInput.split(" /from | /to ");

        // Check if the description part exists and is long enough
        if (parts[0].length() < 6) { // "event " is 6 characters long
            throw new WrongEventFormatException();
        }

        if (parts.length < 3) {
            throw new MissingTimeInfoException();
        }

        String description = parts[0].substring(6); // Extracting description after "event "
        String timing = "from: " + parts[1] + " to: " + parts[2];

        Constants.toDoList.add(new Event(description, timing));
        Task.listCount++;
        System.out.println(Constants.LINE);
        System.out.println("    Got it. I've added this task cuh:");
        System.out.println("      [E][ ] " + Constants.toDoList.get(Task.listCount - 1).getDescription() + " (" + ((Event) Constants.toDoList.get(Task.listCount - 1)).getTiming() + ")");
        System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
}
