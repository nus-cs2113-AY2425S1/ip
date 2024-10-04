package niwa.command;

import niwa.data.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CommandResult} class represents the result of a command execution.
 * Partly referred to AddressBook project.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result. */
    public final List<String> feedbackToUser;

    /** The list of tasks that were produced by the command. */
    private final List<Task> relevantTasks;

    /**
     * Constructs a CommandResult with feedback to the user.
     *
     * @param feedbackToUser The feedback message to show the user.
     */
    public CommandResult(List<String> feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = null;
    }

    /**
     * Constructs a CommandResult with a single feedback message.
     *
     * @param feedback The feedback message to show the user.
     */
    public CommandResult(String feedback) {
        this.feedbackToUser = new ArrayList<>();
        this.feedbackToUser.add(feedback);
        this.relevantTasks = null;
    }

    /**
     * Constructs a CommandResult with feedback and relevant tasks.
     *
     * @param feedbackToUser The feedback message to show the user.
     * @param relevantTasks The list of tasks that were produced by the command.
     */
    public CommandResult(List<String> feedbackToUser, List<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks; // Store relevant tasks
    }

    /**
     * Returns a list of tasks that were produced by the command, if any.
     *
     * @return A list of relevant tasks.
     */
    public List<Task> getRelevantTasks() {
        return relevantTasks;
    }
}
