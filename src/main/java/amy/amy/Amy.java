package amy;
import exception.AmyException;
import java.io.IOException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class Amy {
    public static final String NAME = "Amy";
    private static boolean isExit = false;
    private Storage storage;
    private static TaskList tasks;
    public Amy(String filePath){
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch(IOException e){
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void doGreeting(){
        Ui.printGreeting();
    }
    public static void exit(){
        isExit = true;
        Ui.printBye();
    }
    public static void addTask(Task task){
        tasks.addTask(task);
    }
    public static void showTaskList(){
        tasks.listTasks();  
     }
    public static void markTask(int taskNo, boolean isDone) throws AmyException{
        tasks.markTaskStatus(taskNo, isDone);
    }

    public static void addDeadline(String description, String by){
        addTask(new Deadline(description, by));
    }
    public static void addEvent(String description, String start, String end){
        addTask(new Event(description, start, end));
    }
    public static void addTodo(String input){
        addTask(new Todo(input));
    }
    public static void deleteTask(int taskNo) throws AmyException{
        tasks.deleteTask(taskNo);
    }

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
