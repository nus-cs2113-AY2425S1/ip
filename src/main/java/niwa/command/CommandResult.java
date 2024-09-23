package niwa.command;

import niwa.data.task.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final List<String> feedbackToUser;

    /** The list of persons that was produced by the command */
    private final List<Task> relevantTasks;

    public CommandResult(List<String> feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantTasks = null;
    }

    public CommandResult(String feedback) {
        feedbackToUser = new ArrayList<>();
        feedbackToUser.add(feedback);
        relevantTasks = null;
    }

    public CommandResult(List<String> feedbackToUser, List<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Returns a list of persons that was produced by the command, if any.
     */
    public List<Task> getRelevantTasks() {
        return relevantTasks;
    }

}
