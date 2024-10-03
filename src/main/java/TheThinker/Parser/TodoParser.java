package TheThinker.Parser;

import TheThinker.Exceptions.FormattingException;
import TheThinker.Tasks.Task;
import TheThinker.Tasks.Todo;

public class TodoParser extends UserInputParser{

    public static final int LENGTH_OF_TODO = 4;
    public static final String TODO_FORMAT = "Please follow format : todo [task]";

    /**
     * Parse user input based on the format of To-do and use the result to create To-do object
     *
     * @return To-do Object
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
