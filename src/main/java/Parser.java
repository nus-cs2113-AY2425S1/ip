import java.io.IOException;

public class Parser {
    public Ui ui;
    public Storage storage;
    public TaskList tasks;

    public Parser(Ui ui, Storage storage, TaskList tasks) {
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
                else if (input.startsWith("find"))
                {
                    findTasks(input);
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
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1; // Get task number from the input
        tasks.getTask(taskNumber).markAsDone();
        ui.showTaskMarked(tasks.getTask(taskNumber));
        storage.saveTasks(tasks);
    }

    private void unmarkTask(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.getTask(taskNumber).markAsNotDone();
        ui.showTaskUnmarked(tasks.getTask(taskNumber));
        storage.saveTasks(tasks);
    }

    private void addTodoTask(String input) throws IOException {
        String description = input.substring(5);
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showTaskAdded(newTodo, tasks.getSize());
        storage.saveTasks(tasks);
    }

    private void addDeadlineTask(String input) throws IOException {
        String[] parts = input.substring(9).split(" /by ");
        Deadline newDeadline = new Deadline(parts[0], parts[1]);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.getSize());
        storage.saveTasks(tasks);
    }

    private void addEventTask(String input) throws IOException {
        String[] description = input.substring(6).split(" /from ");
        String[] time = description[1].split(" /to ");
        Event newEvent = new Event(description[0], time[0], time[1]);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks.getSize());
        storage.saveTasks(tasks);
    }

    private void deleteTask(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        Task taskToRemove = tasks.getTask(taskNumber);
        ui.showTaskDeleted(taskToRemove);
        tasks.deleteTask(taskNumber);
        storage.saveTasks(tasks);
    }

    private void addGenericTask(String input) throws IOException {
        Task newTask = new Task(input);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
        storage.saveTasks(tasks);
    }

    private void findTasks(String input) {
        String keyword = input.substring(5);
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
