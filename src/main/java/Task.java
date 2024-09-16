public class Task {
    protected String description;
    protected boolean isDone;
    protected static int tasksCount = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        tasksCount++;
    }

    public static void createNewTask(String userInput) throws InvalidCreateTaskException,
            InvalidCreateToDoException, InvalidCreateDeadlineException, InvalidCreateEventException {
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit[0].equals("todo") && userInput.length() > 4) {
            ToDo.createNewToDo(userInput.substring(5));
        } else if (userInputSplit[0].equals("event") && userInput.length() > 5) {
            Event.createNewEvent(userInput.substring(6));
        } else if (userInputSplit[0].equals("deadline") && userInput.length() > 8) {
            Deadline.createNewDeadline(userInput.substring(9));
        } else {
            throw new InvalidCreateTaskException();
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void printMark() {
        UI.printContent("Nice! You have done this task:\n\t" + this.toString());
    }

    public void printUnmark() {
        UI.printContent("I have unmarked this task:\n\t" + this.toString());
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}
