package thethinker.parser;

import thethinker.exceptions.FormattingException;
import thethinker.tasks.Task;
import thethinker.tasks.Todo;

public class TodoParser extends UserInputParser{

    public static final int LENGTH_OF_TODO = 4;
    public static final String TODO_FORMAT = "Please follow format : todo [task]";

    /**
     * Parse user input based on the format of To-do and create To-do object
     *
     * @throws FormattingException if task description is not provided in user input
     */
    public static Task parseTodo() throws FormattingException {

        String taskDescription = userInput.substring(LENGTH_OF_TODO).trim();

        if(taskDescription.isEmpty()){
            throw new FormattingException("The task to do is missing. " + TODO_FORMAT);
        }

        return new Todo(taskDescription);
    }
}
