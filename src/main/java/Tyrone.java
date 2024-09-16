import java.util.Scanner;

import tyrone.constants.Constants;
import tyrone.exceptions.MissingTimeInfoException;
import tyrone.exceptions.TyroneException;
import tyrone.exceptions.WrongDeadlineFormatException;
import tyrone.exceptions.WrongEventFormatException;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.Task;
import tyrone.task.ToDo;

public class Tyrone {
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
        } else if (userInput.equals("save")) {
            Constants.saveTasks(); // Call save function
        } else {
            System.out.println(Constants.LINE);
            System.out.println("    Invalid command my brother.");
            System.out.println(Constants.LINE);
        }
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println(Constants.LINE);
            System.out.println("    Hello from\n" + Constants.logo + "\n");
            System.out.println("    What can I do for you cuh?\n");
            System.out.println(Constants.LINE);
            String input = in.nextLine();
            while (!input.equals("bye")) {
                try {
                    getUserInput(input); 
                } catch (TyroneException e) {
                    // General exception handling for TyroneException
                    System.out.println("An error occurred: " + e.getMessage());
                }
                input = in.nextLine();
            }
        }
        Constants.goodbye();
    }
}