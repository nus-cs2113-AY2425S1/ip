package yapper.instructions;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.io.*;
import yapper.tasks.TaskHandler;
import yapper.tasks.Task;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Todo;

// Human-Yapper Interface
public class InstructionHandler {
    // UI Operations: Error_Check -> Do -> Print -> Update_File

    public static void handleFindInstruction(TaskHandler taskHandler, String query) {
        OutputStringHandler.printTasksMatchQuery(taskHandler, query);
    }
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
    public static void handleAddInstruction(TaskHandler taskHandler, Task task) throws YapperException {
        try {
            // No Error_Check yet ?
            // Do
            taskHandler.addTask(task);
            // Print
            OutputStringHandler.printAddedTask(task, taskHandler.getCurrTaskTotal());
            // Update_File
            InputFileHandler.storeAddedTask(task);
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to add a task. \n"
                    + e.getMessage());
        }
    }
    public static void handleDeleteInstruction(TaskHandler taskHandler, Integer taskOrdinal) throws YapperException {
        // OOB method should indirectly check if list is empty?
        try {
            // No Error_Check yet
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            // Do
            Task task = taskHandler.getTask(taskOrdinal);
            taskHandler.deleteTask(taskOrdinal);
            // Print
            OutputStringHandler.printDeletedTask(task, taskHandler.getCurrTaskTotal());
            // Update_File
            InputFileHandler.unstoreDeletedTask(taskOrdinal);
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to delete a task. \n"
                    + e.getMessage());
        }
    }
    public static void handleMarkingInstruction(TaskHandler taskHandler, Integer taskOrdinal, boolean isDone) throws YapperException {
        try {
            // Error Check
            ExceptionHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            Task task = taskHandler.getTask(taskOrdinal); // need for methods later
            ExceptionHandler.checkIfDoneStatusNeedsChanging(task.isDone(), isDone);
            // Do
            taskHandler.updateTaskStatus(task, isDone);
            // Print
            OutputStringHandler.printTaskStatus(task, isDone);
            // Update_File
            InputFileHandler.amendTaskStatus(task, taskOrdinal); // uses taskToString after doneStatus is changed
        } catch (YapperException e) {
            throw new YapperException(
                    "YapperException has occurred when trying to mark/unmark a task. \n"
                    + e.getMessage());
        }
    }

    //
    public static void handleInstruction(TaskHandler taskHandler, String userInputString) {
        Instruction instruction = null;
        try {
            instruction = InputStringHandler.parseUserInput(userInputString.trim());
        } catch (YapperException e) {
            System.out.println(
                    "YapperException has occurred when parsing user input. " +
                    "See info below for more details: \n"
                    + e.getMessage());
            return;
        }
        // TO DECIDE: try-catch to extend to the whole method?
        try {
            Instruction.InstructionType instructionType = instruction.getInstructionType();
            switch (instructionType) {
            case LIST:
                handleListInstruction(taskHandler);
                break;
            case FIND:
                String query = instruction.getInstructionDesc(); // TODO
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
//            case HELP:
//                System.out.println(StringStorage.HELP_MESSAGE);
//                break;
            }
            // FYI: BYE instruction is not handled here, but in Yapper.startYappin()
        } catch (YapperException e) {
            System.out.println(
                    "YapperException has occurred when executing instruction. " +
                    "See info below for more details: \n"
                    + e.getMessage());
        }

    }
}