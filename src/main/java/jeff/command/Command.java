package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;

import java.io.IOException;

public abstract class Command {
    protected String firstWord;
    protected String line;
    protected boolean isExit;

    Command() {
        this.isExit = false;
    }

    public Command(String firstWord, String line) {
        this.firstWord = firstWord;
        this.line = line;
        this.isExit = false;
    }

    //Processes taskNumber and throws an exception if any
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

    //Deletes the specified task, catches any error thrown
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
