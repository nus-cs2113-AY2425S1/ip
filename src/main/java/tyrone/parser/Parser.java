package tyrone.parser;

import tyrone.constants.Constants;
import tyrone.exceptions.MissingTimeInfoException;
import tyrone.exceptions.TyroneException;
import tyrone.exceptions.WrongDeadlineFormatException;
import tyrone.exceptions.WrongEventFormatException;
import tyrone.storage.Storage;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.ToDo;

    /**
     * Main Logic for the Tyrone ChatBot
     * Each command has its own if else block which calls the appriopriate function in the right packages
     * After each input, the ChatBot runs a save function to save the data into data/Tasks.txt
     * @param userInput
     * @throws TyroneException
     */

public class Parser {
    public static void getUserInput(String userInput) throws TyroneException {
        if (userInput.startsWith("mark ")) {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            if (index >= 0 && index < Constants.toDoList.size()) {
                Constants.markAsDone(index);
            } else {
                System.out.println(Constants.LINE);
                System.out.println("    Invalid task number bro.");
                System.out.println(Constants.LINE);
            }
        } else if (userInput.startsWith("unmark ")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            if (index >= 0 && index < Constants.toDoList.size()) {
                Constants.unmarkAsUndone(index);
            } else {
                System.out.println(Constants.LINE);
                System.out.println("    Invalid task number bro.");
                System.out.println(Constants.LINE);
            }
        } else if (userInput.startsWith("todo ")) {
            ToDo.createToDo(userInput);
        } else if (userInput.startsWith("deadline ")) {
            try{
                Deadline.createDeadline(userInput);
            }catch (WrongDeadlineFormatException e){
                System.out.println(Constants.LINE);
                System.out.println("    WRONG WAY CUH!! Use:  the format: deadline <description> /by <due by>");
                System.out.println(Constants.LINE);
            } catch (MissingTimeInfoException e) {
                Constants.missingTimeInfo();
            }
        }
        else if (userInput.startsWith("delete ")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            Constants.deleteTask(index);
        } 
        else if (userInput.startsWith("event ")) {
            try{
                Event.createEvent(userInput);
            } catch (WrongEventFormatException e){
            System.out.println(Constants.LINE);
            System.out.println("    WRONG WAY CUH!! Use: event <description> /from <start_time> /to <end_time>");
            System.out.println(Constants.LINE);
            } catch (MissingTimeInfoException e) {
                Constants.missingTimeInfo();
            }
        } else if (userInput.equals("list")) {
            Constants.getList();
        } else if (userInput.equals("priority list")) {
            Constants.getPriorityList();
        } else {
            System.out.println(Constants.LINE);
            System.out.println("    Invalid command my brother.");
            System.out.println(Constants.LINE);
        }
        Storage.saveTasks();// Call save function
    }
}
