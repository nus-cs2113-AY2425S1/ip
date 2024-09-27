package tyrone.parser;

import tyrone.constants.Constants;
import tyrone.exceptions.*;
import tyrone.storage.Storage;
import tyrone.task.*;
import tyrone.tasklist.TaskList;

public class Parser {
    public static boolean handleUserInput(String userInput, TaskList tasks) throws TyroneException {
        if (userInput.equals("bye")) {
            return true; // Exit the program when "bye" is typed
        } else if (userInput.startsWith("mark ")) {
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
        } else {
            System.out.println(Constants.LINE);
            System.out.println("    Invalid command my brother.");
            System.out.println(Constants.LINE);
        }
        Storage.saveTasks(tasks.getTasks()); // Call save function
        return false;
    }
}
