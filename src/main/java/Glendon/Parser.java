package Glendon;

import Glendon.task.Task;
import java.util.ArrayList;

public class Parser {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public Storage storage;
    public ArrayList<String> commands;

    /**
     * Creates a Parser for the chatbot for checking of the commands
     *
     * @param storage the storage of the saved task from previous usage of the chatbot
     */
    Parser(Storage storage) {
        this.storage = storage;
        this.commands = new ArrayList<>();
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("mark");
        commands.add("unmark");
        commands.add("delete");
    }

    /**
     * Checks the commands given by the user and executes them
     *
     * @param response the command given by the user
     * @param ui the user interface to interact with the user
     * @param tasks the list of tasks that are already present
     */
    public void inputs(String response, UI ui, TaskList tasks) {
        int taskIndex;
        int taskCounter;
        switch (response.split(" ")[0]) {
        case BYE:
            ui.showBye();
            response = null;
            this.storage.saveTasks(tasks);
            break;
        case LIST:
            ui.showTaskList(tasks);
            break;
        case MARK:
            if (tasks.checkMark(response)) {
                ui.showMarkError();
                break;
            };
            try {
                tasks.markTask(response);
                taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                ui.markedTaskMessage(tasks, taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showTaskInfoError();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNumberError();
            } catch (NumberFormatException e) {
                ui.showTaskNameError();
            }
            break;
        case UNMARK:
            if (tasks.checkUnmark(response)) {
                ui.showUnmarkError();
                break;
            };
            try {
                tasks.unmarkTask(response);
                taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                ui.unmarkedTaskMessage(tasks, taskIndex);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showTaskInfoError();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNumberError();
            } catch (NumberFormatException e) {
                ui.showTaskNameError();
            }
            break;
        case TODO:
            try {
                tasks.addTodo(response);
                ui.showAddedTask(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                ui.showTaskInfoError();
            } catch (GlendonException e) {
                ui.showTaskInfoError();
            }
            break;
        case DEADLINE:
            try {
                tasks.addDeadline(response);
                ui.showAddedTask(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                ui.showTaskInfoError();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showDateError();
            } catch (GlendonException e) {
                ui.showTaskInfoError();
            }
            break;
        case EVENT:
            try {
                tasks.addEvent(response);
                ui.showAddedTask(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                ui.showTaskInfoError();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showDateError();
            } catch (GlendonException e) {
                ui.showTaskInfoError();
            }
            break;
        case DELETE:
            try {
                taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                Task deletedTask = tasks.taskList.get(taskIndex);
                tasks.deleteTask(response);
                ui.showDeletedMessage(deletedTask, tasks);
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNumberError();
            }
            break;
        case FIND:
            try {
                tasks.findTask(response.substring(5));
                break;
            } catch (StringIndexOutOfBoundsException e) {
                ui.showTaskInfoError();
            }
        default:
            try {
                checkResponse(response);
            } catch (GlendonException e) {
                ui.showMissingCommandError();
            }
            break;
        }
    }

    /**
     * Check if the command, inputted by users, exists
     *
     * @param response the input that is given by the user
     * @throws GlendonException error when no such command exists
     */
    private void checkResponse(String response) throws GlendonException {
        if(!commands.contains(response)) {
            throw new GlendonException();
        }
    }
}
