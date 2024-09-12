package poppy;

import exceptions.CustomExceptions;

import java.util.ArrayList;
import java.util.Scanner;
import tasks.*;
import commands.*;
import exceptions.*;

import static commands.Commands.*;

public class Poppy {

    public static String input;
    private static final int INITIAL_INDEX = 0;
    private static final int TASK_LIST_SIZE = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm poppy.Poppy");
        System.out.println("What can I do for you?");
        ArrayList<Task> taskList = new ArrayList<>();
        input = sc.nextLine();
        int counter = 0;
        while (!input.equals("Bye")) {
            String[] commandArgs = input.split(" ", 2 );
            String command = commandArgs[0];
            try {
                switch (command) {
                    case "delete":
                        delete(taskList, Integer.parseInt(commandArgs[1]));
                        counter--;
                        break;
                    case "mark":
                        markAsDone(taskList, commandArgs);
                        break;
                    case "unmark":
                        markAsNotDone(taskList, commandArgs);
                        break;
                    case "List":
                        showList(taskList);
                        break;
                    case "todo":
                        if (commandArgs.length< 2){
                            throw new CustomExceptions.MissingArgsException("Description of ToDo cannot be empty");
                        }
                        ToDo task = new ToDo(commandArgs[1]);
                        taskList.add(task);
                        counter++;
                        System.out.println(task.toString());
                        System.out.println("You now have " + counter + " tasks");
                        break;
                    case "deadline":
                        if (commandArgs.length< 2){
                            throw new CustomExceptions.MissingArgsException("Description of Deadline cannot be empty");
                        }
                        String[] deadlinestring = commandArgs[1].split("/by", 2);
                        Deadline deadline = new Deadline(deadlinestring[0], deadlinestring[1]);
                        taskList.add(deadline);
                        counter++;
                        System.out.println(deadline.toString());
                        System.out.println("You now have " + counter + " tasks");
                        break;
                    case "event":
                        if (commandArgs.length< 2){
                            throw new CustomExceptions.MissingArgsException("Description of Event cannot be empty");
                        }
                        String[] eventstring = commandArgs[1].split("/from", 2);
                        Events event = new Events(eventstring[0], eventstring[1]);
                        taskList.add(event);
                        counter++;
                        System.out.println(event.toString());
                        System.out.println("You now have " + counter + " tasks");
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid command");
                }
            }catch (IllegalArgumentException e) {
                System.out.println("Wait, I don't understand what you are saying??");
            }catch (CustomExceptions.MissingArgsException e) {
                System.out.println(e.getMessage());
            }catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You are missing a keyword... read manual please!");
            }
            input =sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}

