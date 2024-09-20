import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.showWelcome();
        ArrayList<Task> tasks = new ArrayList<>(); //Array of "task" object to store the tasks

        Storage storage = new Storage("data/duke.txt");
        //load tasks from file at the start
        try {
            tasks = storage.loadTasks();
        }
        catch (FileNotFoundException e) {
            ui.showLoadingError();
        }

        String input;
        while (true) {
            input = ui.readCommand();

            try
            {
                if (input.equals("bye"))
                {
                    ui.showGoodbye();
                    break;
                }
                else if (input.equals("list")) //display tasks when user inputs "list"
                {
                    ui.showTaskList(tasks);
                }
                else if (input.startsWith("mark")) //mark task and print acknowledge message when user marks a task
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text. minus 1 to get correct index in the "tasks" array
                    tasks.get(taskNumber).markAsDone();
                    ui.showTaskMarked(tasks.get(taskNumber));
                    storage.saveTasks(tasks);
                }
                else if (input.startsWith("unmark")) //unmark task and print acknowledge message when user unmarks a task
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text
                    tasks.get(taskNumber).markAsNotDone();
                    ui.showTaskUnmarked(tasks.get(taskNumber));
                    storage.saveTasks(tasks);
                }
                else if (input.startsWith("todo"))
                {
                    String description = input.substring(5); //at index 5 of the string, the description starts
                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);
                    ui.showTaskAdded(newTodo, tasks.size());
                    storage.saveTasks(tasks);
                }
                else if (input.startsWith("deadline"))
                {
                    String[] parts = input.substring(9).split(" /by ");
                    Deadline newDeadline = new Deadline(parts[0], parts[1]);
                    tasks.add(newDeadline);
                    ui.showTaskAdded(newDeadline, tasks.size());
                    storage.saveTasks(tasks);
                }
                else if (input.startsWith("event"))
                {
                    String[] description = input.substring(6).split(" /from ");
                    String[] time = description[1].split(" /to ");
                    Event newEvent = new Event(description[0], time[0], time[1]);
                    tasks.add(newEvent);
                    ui.showTaskAdded(newEvent, tasks.size());
                    storage.saveTasks(tasks);
                }
                else if(input.startsWith("delete"))
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text. minus 1 to get correct index in the "tasks" array
                    Task taskToRemove = tasks.get(taskNumber);
                    ui.showTaskDeleted(taskToRemove);
                    tasks.remove(taskNumber);
                    storage.saveTasks(tasks);
                }
                else //store other input from user as a task object
                {
                    Task newTask = new Task(input);
                    tasks.add(newTask);
                    ui.showTaskAdded(newTask, tasks.size());
                    storage.saveTasks(tasks);
                }
            }
            catch (NumberFormatException e){
                ui.showFormatError();
            }
            catch (ArrayIndexOutOfBoundsException e){
                ui.showInputError();
            }
            catch (NullPointerException e){ //when access nullptr, eg when marking task 6 but only got 5 task
                ui.showIndexError();
            }
            catch (StringIndexOutOfBoundsException e){ //access index in string outside valid range. happen for T/D/E commands
                ui.showInputError();
            }
            catch (IndexOutOfBoundsException e){
                ui.showIndexError();
            }
            catch (IOException e){
                ui.showSaveError();
            }
        }

    }


}

