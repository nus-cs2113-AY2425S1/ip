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

        //load tasks from file at the start
        try {
            tasks = loadTasks();
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
                    saveTasks(tasks);
                }
                else if (input.startsWith("unmark")) //unmark task and print acknowledge message when user unmarks a task
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text
                    tasks.get(taskNumber).markAsNotDone();
                    ui.showTaskUnmarked(tasks.get(taskNumber));
                    saveTasks(tasks);
                }
                else if (input.startsWith("todo"))
                {
                    String description = input.substring(5); //at index 5 of the string, the description starts
                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);
                    ui.showTaskAdded(newTodo, tasks.size());
                    saveTasks(tasks);
                }
                else if (input.startsWith("deadline"))
                {
                    String[] parts = input.substring(9).split(" /by ");
                    Deadline newDeadline = new Deadline(parts[0], parts[1]);
                    tasks.add(newDeadline);
                    ui.showTaskAdded(newDeadline, tasks.size());
                    saveTasks(tasks);
                }
                else if (input.startsWith("event"))
                {
                    String[] description = input.substring(6).split(" /from ");
                    String[] time = description[1].split(" /to ");
                    Event newEvent = new Event(description[0], time[0], time[1]);
                    tasks.add(newEvent);
                    ui.showTaskAdded(newEvent, tasks.size());
                    saveTasks(tasks);
                }
                else if(input.startsWith("delete"))
                {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; //get the integer in the text. minus 1 to get correct index in the "tasks" array
                    Task taskToRemove = tasks.get(taskNumber);
                    ui.showTaskDeleted(taskToRemove);
                    tasks.remove(taskNumber);
                    saveTasks(tasks);
                }
                else //store other input from user as a task object
                {
                    Task newTask = new Task(input);
                    tasks.add(newTask);
                    ui.showTaskAdded(newTask, tasks.size());
                    saveTasks(tasks);
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


    // Method to save tasks to a file
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File("data");
        if (!file.exists()) {
            file.mkdirs(); // Create the directory if it dont exist
        }
        FileWriter fw = new FileWriter(new File(file, "duke.txt"));

        for (Task task : tasks) //iterate over all tasks, and save them in rquired format.
        {
            fw.write(task.toSaveFormat() + "\n");
        }
        fw.close();
    }

    // Method to load tasks from a file and add it to the ArrayList
    public static ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("data/duke.txt");
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    if (parts[1].equals("1"))
                    {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1"))
                    {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1"))
                    {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case " ": //for generic tasks
                    task = new Task(parts[2]);
                    if (parts[1].equals("1"))
                    {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                }
            }
            sc.close();
        }
        return tasks;
    }

}

