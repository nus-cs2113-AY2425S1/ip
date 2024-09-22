package november.strings;

public class Strings {
    public static final String SEPARATOR = "____________________________________________________________"; // Separator line to demarcate output messages

    public static final String TODO_COMMAND = "todo"; // Command to add a TODO task
    public static final String DEADLINE_COMMAND = "deadline"; // Command to add a DEADLINE task
    public static final String EVENT_COMMAND = "event"; // Command to add an EVENT task
    public static final String LIST_COMMAND = "list"; // Command to list all tasks
    public static final String DELETE_COMMAND = "delete"; // Command to delete a task
    public static final String MARK_COMMAND = "mark"; // Command to mark a task as done
    public static final String UNMARK_COMMAND = "unmark"; // Command to unmark a task
    public static final String EXIT_COMMAND = "bye"; // Command to exit the chatbot

    public static final String INIT_SENTENCE = "Hello! I'm November." + System.lineSeparator() + "What can I do for you?"; // Initial greeting
    public static final String LIST_MESSAGE = "Here are the tasks in your list:"; // Message when listing tasks
    public static final String LIST_EMPTY_MESSAGE = "Your list is currently empty."; // Message when no tasks are in the list
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:" + System.lineSeparator() + "  "; // Message when a new task is added
    public static final String DELETE_TASK_MESSAGE = "Got it. I've removed this task:" + System.lineSeparator() + "  "; // Message when a task is deleted
    public static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:" + System.lineSeparator() + "  "; // Message when a task is marked as done
    public static final String UNMARK_TASK_MESSAGE = "Ok, I've marked this task as not done yet:" + System.lineSeparator() + "  "; // Message when a task is unmarked
    public static final String NONNUMERICAL_INDEX_MESSAGE = "Please provide a valid numerical index."; // Message for non-numeric index
    public static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Sorry, but that index is not within the list."; // Message for out-of-bounds index
    public static final String INVALID_INPUT_MESSAGE = "I'm sorry, I don't know what that means."; // Message for invalid input
    public static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!"; // Exit message
}
