package yapper.instructions;

import yapper.io.SaveFileHandler;
import yapper.tasks.TaskHandler;
import yapper.exceptions.ErrorHandler;
import yapper.exceptions.YapperException;
import yapper.io.InputStringHandler;
import yapper.io.OutputStringHandler;
import yapper.io.StringStorage;
import yapper.tasks.Task;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Todo;

import java.util.List;

// Human-Yapper Interface
public class InstructionHandler {
    private static final int INDEX_OFFSET = 1; // list is 1-indexed

    // UI Operations
    public static void handleListInstruction(List<Task> tasks, int taskCount) {
        try {
            ErrorHandler.checkIfListEmpty(taskCount);
            OutputStringHandler.printTasks(tasks, taskCount);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
    public static void handleAddInstruction(TaskHandler taskHandler, Task task) {
        // No Exception Handling here at the moment
        taskHandler.addTask(task);
    }
    public static void handleDeleteInstruction(TaskHandler taskHandler, Integer taskOrdinal) {
        try {
            // this should indirectly check if list is empty?
            ErrorHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
            taskHandler.deleteTask(taskOrdinal);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
    public static void handleMarkingInstruction(TaskHandler taskHandler, Integer taskOrdinal, boolean isDone) {
        try {
            ErrorHandler.checkIfTaskOrdinalIsOutOfRange(taskHandler.getCurrTaskTotal(), taskOrdinal);

            Task task = taskHandler.getTask(taskOrdinal - INDEX_OFFSET);
            ErrorHandler.checkIfDoneStatusNeedsChanging(task.isDone(), isDone);
            taskHandler.updateTaskStatus(task, isDone);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
    public static void handleSaveInstruction(TaskHandler taskHandler) {
        try {
            ErrorHandler.checkIfB(); // TODO
            SaveFileHandler.storeTasksData(taskHandler);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
    public static void handleLoadInstruction(TaskHandler taskHandler) {
        try {
            ErrorHandler.checkIfA(); // TODO
            SaveFileHandler.loadTasksData();
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }

    //
    public static void handleInstruction(TaskHandler taskHandler, String userInputString) {
        Instruction instruction = null;
        try {
            instruction = InputStringHandler.parseUserInput(userInputString);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Instruction.InstructionType instructionType = instruction.getInstructionType();
        switch (instructionType) {
        case LIST:
            handleListInstruction(taskHandler.getAllTasks(),
                    taskHandler.getCurrTaskTotal());
            break;
        case TODO:
            String todoDesc = instruction.getInstructionDesc();
            handleAddInstruction(taskHandler,
                    new Todo(todoDesc) );
            break;
        case DEADLINE:
            String deadlineDesc = instruction.getInstructionDesc();
            String deadline = instruction.getTaskDates()[0];
            handleAddInstruction(taskHandler,
                    new Deadline(deadlineDesc, deadline) );
            break;
        case EVENT:
            String eventDesc = instruction.getInstructionDesc();
            String startDate = instruction.getTaskDates()[0];
            String endDate = instruction.getTaskDates()[1];
            handleAddInstruction(taskHandler,
                    new Event(eventDesc, startDate, endDate) );
            break;
        case DELETE:
            handleDeleteInstruction(taskHandler,
                    instruction.getTaskOrdinal() );
            break;
        case MARK:
            handleMarkingInstruction(taskHandler,
                    instruction.getTaskOrdinal(), true);
            break;
        case UNMARK:
            handleMarkingInstruction(taskHandler,
                    instruction.getTaskOrdinal(), false);
            break;
        // WIP
//        case SAVE:
//            handleSaveInstruction(taskHandler);
//            break;
//        case LOAD:
//            handleLoadInstruction(taskHandler);
//            break;
//        case HELP:
//            StringStorage.printWithDividers(StringStorage.HELP_MESSAGE);
//            break;
        }
        // FYI: BYE instruction is not handled here, but in Yapper.startYappin()
    }
}