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

        if (parts.length < 3) {
            throw new MissingTimeInfoException();
        }

        Constants.toDoList[Task.listCount] = new Event(parts[0].substring(6), "from: " + parts[1] + " to: " + parts[2]);
        Task.listCount++;
        System.out.println(Constants.LINE);
        System.out.println("    Got it. I've added this task cuh:");
        System.out.println("      [E][ ] " + Constants.toDoList[Task.listCount - 1].getDescription() + " (" + ((Event) Constants.toDoList[Task.listCount - 1]).getTiming() + ")");
        System.out.println("    Now you have " + Task.listCount + " tasks in the list.");
        System.out.println(Constants.LINE);
    }
}
