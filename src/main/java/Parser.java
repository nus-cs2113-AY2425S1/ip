// Parser.java
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public Ui ui;
    public Storage storage;
    public ArrayList<Task> tasks;

    public Parser(Ui ui, Storage storage, ArrayList<Task> tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    public void run() {
        String input;
        while (true) {
            input = ui.readCommand();

            try {
                if (input.equals("bye"))
                {
                    ui.showGoodbye();
                    break;
                }
                else if (input.equals("list"))
                {
                    ui.showTaskList(tasks);
                }
                else if (input.startsWith("mark"))
                {
                    markTask(input);
                }
                else if (input.startsWith("unmark"))
                {
                    unmarkTask(input);
                }
                else if (input.startsWith("todo"))
                {
                    addTodoTask(input);
                }
                else if (input.startsWith("deadline"))
                {
                    addDeadlineTask(input);
                }
                else if (input.startsWith("event"))
                {
                    addEventTask(input);
                }
                else if (input.startsWith("delete"))
                {
                    deleteTask(input);
                }
                else
                {
                    addGenericTask(input);
                }
            }
            catch (NumberFormatException e) {
                ui.showFormatError();
            }
            catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                ui.showInputError();
            }
            catch (NullPointerException | IndexOutOfBoundsException e) {
                ui.showIndexError();
            }
            catch (IOException e) {
                ui.showSaveError();
            }
        }
    }

    private void markTask(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(taskNumber).markAsDone();
        ui.showTaskMarked(tasks.get(taskNumber));
        storage.saveTasks(tasks);
    }

    private void unmarkTask(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(taskNumber).markAsNotDone();
        ui.showTaskUnmarked(tasks.get(taskNumber));
        storage.saveTasks(tasks);
    }

    private void addTodoTask(String input) throws IOException {
        String description = input.substring(5);
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        ui.showTaskAdded(newTodo, tasks.size());
        storage.saveTasks(tasks);
    }

    private void addDeadlineTask(String input) throws IOException {
        String[] parts = input.substring(9).split(" /by ");
        Deadline newDeadline = new Deadline(parts[0], parts[1]);
        tasks.add(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.size());
        storage.saveTasks(tasks);
    }

    private void addEventTask(String input) throws IOException {
        String[] description = input.substring(6).split(" /from ");
        String[] time = description[1].split(" /to ");
        Event newEvent = new Event(description[0], time[0], time[1]);
        tasks.add(newEvent);
        ui.showTaskAdded(newEvent, tasks.size());
        storage.saveTasks(tasks);
    }

    private void deleteTask(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        Task taskToRemove = tasks.get(taskNumber);
        ui.showTaskDeleted(taskToRemove);
        tasks.remove(taskNumber);
        storage.saveTasks(tasks);
    }

    private void addGenericTask(String input) throws IOException {
        Task newTask = new Task(input);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        storage.saveTasks(tasks);
    }
}
