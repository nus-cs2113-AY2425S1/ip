package yapper.instructions;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.io.InputStringHandler;
import yapper.io.OutputFileHandler;
import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.TaskHandler;
import yapper.tasks.Todo;

/**
 * Coordinates string input-output, task management and exception handling for Yapper.
 *
 * <p>
 * A utility class for handling various types of instructions
 * (FIND, LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK) in the Yapper application.
 * It processes user input and performs corresponding actions on tasks using a TaskHandler.
 * <p/>
 *
 */
public class InstructionHandler {

    /**
     * Handles the FIND instruction by printing matching tasks.
     *
     * @param taskHandler The handler that manages the list of tasks.
     * @param query       The string that is to be found in task descriptions.
     */
    public static void handleFindInstruction(TaskHandler taskHandler, String query) {
        OutputStringHandler.printSelectedTasks(taskHandler, query);
    }

    /**
     * Handles the LIST instruction by printing all tasks.
     *
     * @param taskHandler      The handler that manages the list of tasks.
     * @throws YapperException If an error occurs while listing the tasks.
     */
    public static void handleListInstruction(TaskHandler taskHandler) throws YapperException {
        try {
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal());
            OutputStringHandler.printAllTasks(taskHandler);
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to list all tasks. \n"
                    + e.getMessage());
        }
    }

    /**
     * Handles the TODO, DEADLINE, and EVENT instructions by adding a task.
     *
     * @param taskHandler      The handler that manages the list of tasks.
     * @param task             The task to be added.
     * @throws YapperException If an error occurs while adding the task.
     */
    public static void handleAddInstruction(TaskHandler taskHandler, Task task) throws YapperException {
        try {
            taskHandler.addTask(task);

            OutputStringHandler.printAddedTask(task, taskHandler.getCurrTaskTotal());

            OutputFileHandler.storeAddedTask(task);
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to add a task. \n"
                    + e.getMessage());
        }
    }

    /**
     * Handles the DELETE instruction by removing a task.
     *
     * @param taskHandler      The handler that manages the list of tasks.
     * @param taskOrdinal      The ordinal (index) of the task to delete.
     * @throws YapperException If an error occurs while deleting the task.
     */
    public static void handleDeleteInstruction(TaskHandler taskHandler, Integer taskOrdinal) throws YapperException {
        try {
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);

            Task task = taskHandler.getTaskAtOrdinal(taskOrdinal);
            taskHandler.deleteTask(taskOrdinal);

            OutputStringHandler.printDeletedTask(task, taskHandler.getCurrTaskTotal());

            OutputFileHandler.unstoreDeletedTask(taskOrdinal);
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to delete a task. \n"
                    + e.getMessage());
        }
    }

    /**
     * Handles the MARK and UNMARK instructions by updating the status of a task.
     *
     * @param taskHandler      The handler that manages the list of tasks.
     * @param taskOrdinal      The ordinal (index) of the task to mark/unmark.
     * @param isDone           The new completion status of the task (true for mark, false for unmark).
     * @throws YapperException If an error occurs while updating the task status.
     */
    public static void handleMarkingInstruction(TaskHandler taskHandler, Integer taskOrdinal, boolean isDone)
            throws YapperException {
        try {
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            Task task = taskHandler.getTaskAtOrdinal(taskOrdinal); // need for methods later
            ExceptionHandler.checkIfDoneStatusNeedsChanging(task.isDone(), isDone);

            taskHandler.updateTaskStatus(task, isDone);

            OutputStringHandler.printTaskStatus(task, isDone);

            OutputFileHandler.amendTaskStatus(task, taskOrdinal); // uses taskToString after doneStatus is changed
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to mark/unmark a task. \n"
                    + e.getMessage());
        }
    }

    /**
     * Parses and performs the appropriate executions, after being given a user instruction string.
     *
     * @param taskHandler     The handler that manages the list of tasks.
     * @param userInputString The raw user input string representing the instruction.
     */
    public static void handleInstruction(TaskHandler taskHandler, String userInputString) {
        Instruction instruction = null;
        try {
            instruction = InputStringHandler.parseUserInput(userInputString.trim());
        } catch (YapperException e) {
            System.out.println(
                    "YapperException has occurred when parsing user input. \n"
                    + e.getMessage());
            return;
        }
        /* There are 2 try-catch to separate the exception messages for parsing and executing */
        try {
            Instruction.InstructionType instructionType = instruction.getInstructionType();
            switch (instructionType) {
            case LIST:
                handleListInstruction(taskHandler);
                break;
            case FIND:
                String query = instruction.getInstructionDesc();
                handleFindInstruction(taskHandler, query);
                break;
            case TODO:
                String todoDesc = instruction.getInstructionDesc();
                handleAddInstruction(taskHandler,
                        new Todo(todoDesc));
                break;
            case DEADLINE:
                String deadlineDesc = instruction.getInstructionDesc();
                String deadline = instruction.getTaskDates()[0];
                handleAddInstruction(taskHandler,
                        new Deadline(deadlineDesc, deadline));
                break;
            case EVENT:
                String eventDesc = instruction.getInstructionDesc();
                String startDate = instruction.getTaskDates()[0];
                String endDate = instruction.getTaskDates()[1];
                handleAddInstruction(taskHandler,
                        new Event(eventDesc, startDate, endDate));
                break;
            case DELETE:
                handleDeleteInstruction(taskHandler,
                        instruction.getTaskOrdinal() - StringStorage.INDEX_OFFSET);
                break;
            case MARK:
                handleMarkingInstruction(taskHandler,
                        instruction.getTaskOrdinal() - StringStorage.INDEX_OFFSET,
                        true);
                break;
            case UNMARK:
                handleMarkingInstruction(taskHandler,
                        instruction.getTaskOrdinal() - StringStorage.INDEX_OFFSET,
                        false);
                break;
            case HELP:
                System.out.println(StringStorage.HELP_MESSAGE);
                break;
            default:
            }
        } catch (YapperException e) {
            System.out.println(
                    "YapperException has occurred when executing instruction. \n"
                    + e.getMessage());
        }

    }
}
