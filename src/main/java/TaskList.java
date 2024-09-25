import java.util.ArrayList;

/**
 * Represents a list of tasks. Operations can be done to the list such as adding and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;
    private Parser parser;

    /**
     * Prints out all the tasks in the list.
     */
    public void displayList() {
        int count = 1;
        this.ui.printLineSeperator();
        for (Task task: this.list) {
            System.out.println(count + "." + task);
            count++;
        }
        this.ui.printLineSeperator();
    }

    /**
     * Changes the status of a task from not done to done.
     * If a task is already marked as complete, nothing will happen.
     * If markTask was called while loading the save file, the list of tasks will not be
     * printed. Else, print out all the tasks after marking the task as done.
     * @param position Position of task in the list based on 1-based numbering
     * @param isFromSaveFile Boolean value on whether the function was called while loading the save file
     */
    public void markTask(int position, boolean isFromSaveFile) {
        Task task = this.list.get(position - 1);
        task.setDone();
        this.list.set(position - 1, task);
        if (!isFromSaveFile) {
            displayList();
        }
    }

    /**
     * Deletes a task from the list.
     * @param position Position of task in the list based on 1-based numbering
     */
    public void deleteTask(int position) {
        Task task = this.list.get(position - 1);
        this.list.remove(position - 1);
        this.ui.printBlock("Got it. The task below was removed:\n" + task +
                "\nNow you have " + this.list.size() + " left");
    }

    /**
     * Add a <code>ToDo</code> to the list. If the function was called from the save file, there will
     * be no output on the terminal, else print to output stating that <code>ToDo</code> was added.
     * @param description Description of the todo
     * @param isFromSaveFile Boolean value on whether the function was called while loading the save file
     */
    public void addToDo(String description, boolean isFromSaveFile) {
        ToDo toDo = new ToDo(description);
        this.list.add(toDo);
        if (!isFromSaveFile) {
            this.ui.printBlock(String.format("Got it. Task added\n %s", toDo));
        }
    }

    /**
     * Add a <code>Deadline</code> to the list. If the function was called from the save file, there will
     * be no output on the terminal, else print to output stating that <code>Deadline</code> was added.
     * @param description Description of the task with the deadline
     * @param isFromSaveFile Boolean value on whether the function was called while loading the save file
     * @throws InvalidDeadlineException If "/by" was not in the input or no deadline was inputted after the "/by"
     */
    public void addDeadline(String description, boolean isFromSaveFile) throws InvalidDeadlineException{
        String[] descriptionAndDeadline = description.split("/by");
        if (descriptionAndDeadline.length != 2) {
            throw new InvalidDeadlineException();
        }
        String descriptionText = descriptionAndDeadline[0].trim();
        String by = descriptionAndDeadline[1].trim();

        Deadline deadline = new Deadline(descriptionText, by);
        this.list.add(deadline);

        if (!isFromSaveFile) {
            this.ui.printBlock(String.format("Got it. Task added\n %s", deadline));
        }
    }

    /**
     * Add an <code>Event</code> to the list. If the function was called from the save file, there will
     * be no output on the terminal, else print to output stating that <code>Event</code> was added.
     * @param description Description of the event with the starting time and ending time
     * @param isFromSaveFile Boolean value on whether the function was called while loading the save file
     */
    public void addEvent(String description, boolean isFromSaveFile) {
        String[] descriptionAndEventTimeline = description.split("/from");
        String descriptionText = descriptionAndEventTimeline[0].trim();
        String[] eventTimeline = descriptionAndEventTimeline[1].split("/to");
        String from = eventTimeline[0].trim();
        String to = eventTimeline[1].trim();
        
        Event event = new Event(descriptionText, from, to);
        this.list.add(event);

        if (!isFromSaveFile) {
            this.ui.printBlock(String.format("Got it. Task added\n %s", event));
        }
    }

    /**
     * Find all tasks that match the description. It is considered a match as long as the task 
     * description contains the query string.
     * @param query Query string that is used to check through the list of tasks
     */
    public void find(String query) {
        int count = 1;
        
        this.ui.printLineSeperator();
        System.out.println("Here are the matching tasks in your list");
        
        for (Task task: this.list) {
            if (task.getDescription().contains(query)) {
                System.out.println(count + "." + task);
                count++;
            }
        }

        this.ui.printLineSeperator();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public TaskList(Storage storage) {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
        this.parser = new Parser(storage, this);
        ArrayList<String> commandArray = storage.getCommandArray();
        commandArray.forEach((command) -> {
            this.parser.parse(command, true);
        });
    }

    public TaskList() {
    }
}
