package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;

import java.io.IOException;


/**
 *  Represents an abstract Command.
 *  A <code>Command</code> object processes user input and performs actions,
 *  potentially interacting with the the task list, ui interface, and storage.
 */
public abstract class Command {
    protected String firstWord;
    protected String line;
    protected boolean isExit;

    /**
     * Constructor for Command if called with no arguments, sets <code>isExit</code> to false.
     */
    Command() {
        this.isExit = false;
    }

    /**
     * Constructs a Command with a given first word and input line.
     * Initializes <code>isExit</code> to false.
     *
     * @param firstWord The first word of the user input.
     * @param line The full input line from the user.
     */
    public Command(String firstWord, String line) {
        this.firstWord = firstWord;
        this.line = line;
        this.isExit = false;
    }

    /**
     * Extracts the task number from the user input, <code>line</code>.
     * Ensures the task number is a valid integer and is within the bounds of the task list size.
     *
     * @param line The full line input given by the user. It contains the command and task number.
     * @param taskListSize The current size of the task list.
     * @return The task number as an integer.
     * @throws IllegalArgumentException if the task number is not a valid integer.
     * @throws IndexOutOfBoundsException if the task number is out of bounds of the task list size.
     */
    protected int getTaskNumber(String line, int taskListSize) throws IllegalArgumentException,
            IndexOutOfBoundsException{
        int dividerPosition = line.indexOf(" ");

        String taskNumberString = line.substring(dividerPosition + 1).trim();
        //If no number is given, or a non-number is inputted, throw exception
        if(!taskNumberString.matches("[0-9]+") || taskNumberString.isEmpty()){
            throw new IllegalArgumentException();
        }
        int taskNumber = Integer.parseInt(taskNumberString);

        if(taskNumber > taskListSize){
            throw new IndexOutOfBoundsException();
        }
        return taskNumber;
    }

    public boolean isExit(){
        return isExit;
    }

    /**
     * Executes the command using the provided task list, user interface, and storage handler.
     * The specific behavior is defined by subclasses.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface to interact with the user.
     * @param storage The storage handler to manage data persistence.
     * @throws IOException if an I/O error occurs during execution.
     */
    //Deletes the specified task, catches any error thrown
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
