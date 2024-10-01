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
    // UI Operations: Error_Check -> Do -> Print -> Update_File

    /**
     * Handles the FIND instruction by printing matching tasks.
     *
     * @param taskHandler The handler that manages the list of tasks.
     * @param query       The string that is to be found in task descriptions.
     */
    public static void handleFindInstruction(TaskHandler taskHandler, String query) {
        // No Error_Check yet ?
        // Do & Print
        OutputStringHandler.printSelectedTasks(taskHandler, query);
        // No Update_File needed
    }

    /**
     * Handles the LIST instruction by printing all tasks.
     *
     * @param taskHandler      The handler that manages the list of tasks.
     * @throws YapperException If an error occurs while listing the tasks.
     */
    public static void handleListInstruction(TaskHandler taskHandler) throws YapperException {
        try {
            // Error_Check
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal());
            // Do & Print
            OutputStringHandler.printAllTasks(taskHandler);
            // No Update_File needed
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
            // No Error_Check yet ?
            // Do
            taskHandler.addTask(task);
            // Print
            OutputStringHandler.printAddedTask(task, taskHandler.getCurrTaskTotal());
            // Update_File
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
        // OOB method should indirectly check if list is empty?
        try {
            // No Error_Check yet
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            // Do
            Task task = taskHandler.getTaskAtOrdinal(taskOrdinal);
            taskHandler.deleteTask(taskOrdinal);
            // Print
            OutputStringHandler.printDeletedTask(task, taskHandler.getCurrTaskTotal());
            // Update_File
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
            // Error Check
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            Task task = taskHandler.getTaskAtOrdinal(taskOrdinal); // need for methods later
            ExceptionHandler.checkIfDoneStatusNeedsChanging(task.isDone(), isDone);
            // Do
            taskHandler.updateTaskStatus(task, isDone);
            // Print
            OutputStringHandler.printTaskStatus(task, isDone);
            // Update_File
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
                    "YapperException has occurred when parsing user input. "
                    + "See info below for more details: \n"
                    + e.getMessage());
            return;
        }
        // There are 2 try-catch to separate the exception messages for parsing and executing
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
            // FYI: BYE instruction is not handled here, but in Yapper.startYappin()
        } catch (YapperException e) {
            System.out.println(
                    "YapperException has occurred when executing instruction. "
                    + "See info below for more details: \n"
                    + e.getMessage());
        }

    }
}
