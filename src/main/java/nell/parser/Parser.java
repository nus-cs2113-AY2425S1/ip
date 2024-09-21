package nell.parser;

import nell.TaskList;

/**
 * Handles the parsing and execution of commands entered by the user into the UI
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructs a parser object with a given task list
     *
     * @param tasks The task list to be used by the object
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
}
