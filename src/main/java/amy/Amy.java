package amy;
import amy.exception.AmyException;
import java.io.IOException;
import amy.parser.Parser;
import amy.storage.Storage;
import amy.task.Deadline;
import amy.task.Event;
import amy.task.Task;
import amy.task.Todo;
import amy.task.TaskList;
import amy.ui.Ui;

/**
 * Represents the main class of the Amy chatbot.
 * The Amy class is responsible for managing the chatbot's state and executing user commands.
 */
public class Amy {
    public static final String NAME = "Amy";
    private static boolean isExit = false;
    private Storage storage;
    private static TaskList tasks;

    /**
     * Constructs an Amy object with a specified file path. 
     * @param filePath The file path to store the chatbot data.
     */
    public Amy(String filePath){
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch(IOException e){
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Displays the greeting message when the chatbot starts.
     */
    public static void doGreeting(){
        Ui.printGreeting();
    }

    /**
     * Displays the goodbye message when the chatbot exits.
     */
    public static void exit(){
        isExit = true;
        Ui.printBye();
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added to the task list.
     */
    public static void addTask(Task task){
        tasks.addTask(task);
    }

    /**
     * Displays the list of tasks in the task list.
     */
    public static void showTaskList(){
        tasks.listTasks();  
    }

    /**
     * Marks a task as done or undone based on the task number.
     * @param taskNo The task number of the task to be marked.
     * @param isDone The status of the task to be marked.
     * @throws AmyException If the task number is invalid.
     */
    public static void markTask(int taskNo, boolean isDone) throws AmyException{
        tasks.markTaskStatus(taskNo, isDone);
    }

    /**
     * Finds a task based on the keyword.
     * @param string The keyword to search for in the task list.
     */
    public static void findTask(String string) {
        tasks.findTask(string);
    }

    /**
     * Adds a deadline task to the task list.
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public static void addDeadline(String description, String by){
        addTask(new Deadline(description, by));
    }

    /**
     * Adds an event task to the task list.
     * @param description The description of the event task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public static void addEvent(String description, String start, String end){
        addTask(new Event(description, start, end));
    }
    
    /**
     * Adds a todo task to the task list.
     * @param input The description of the todo task.
     */
    public static void addTodo(String input){
        addTask(new Todo(input));
    }

    /**
     * Deletes a task from the task list based on the task number.
     * @param taskNo The task number of the task to be deleted.
     * @throws AmyException If the task number is invalid.
     */
    public static void deleteTask(int taskNo) throws AmyException{
        tasks.deleteTask(taskNo);
    }

    /**
     * Runs the Amy chatbot.
     * The chatbot will continue to run until the user exits the chatbot. Chatbot will be waiting for the user 
     * input and parse the input to execute the corresponding action.
     * @throws AmyException If an error occurs during the execution of the chatbot.
     * @throws IOException If an error occurs during the reading or writing of the chatbot data.
     */
    public void run() throws AmyException, IOException{
        Ui.printGreeting();
        Parser parser = new Parser();
        while(!isExit){
            try{
                String request = Ui.readCommand();
                parser.parse(request);
                parser.chooseAction();
                storage.save(tasks);
            } catch (AmyException e){
                Ui.print(e.getMessage());
            } catch (IOException e){
                Ui.showLoadingError();
            }
        }
    }

    public static void main(String[] args) throws AmyException, IOException {
        new Amy("data/tasks.txt").run();
    }
}
