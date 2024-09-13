package yapper.instructions;

import yapper.TaskManager;
import yapper.Yapper;
import yapper.exceptions.ErrorHandler;
import yapper.exceptions.YapperException;
import yapper.io.InputStringHandler;
import yapper.io.OutputStringHandler;
import yapper.tasks.Task;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Todo;

// Human-Yapper Interface. Should this be 2 Classes Instead?
public class InstructionHandler {
    private static final int INDEX_OFFSET = 1;

    // UI Operations
    public static void handleAddInstruction(TaskManager taskManager, String todoDesc) {
        try {
            ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Todo todo = new Todo(todoDesc);
        taskManager.addTask(todo);
        todo.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String endDate) {
        try {
            ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Deadline deadline = new Deadline(taskDesc, endDate);
        taskManager.addTask(deadline);
        deadline.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String startDate, String endDate) {
        try {
            ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Event event = new Event(taskDesc, startDate, endDate);
        taskManager.addTask(event);
        event.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleMarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        try {
            ErrorHandler.checkIfTaskOrdinalWithinRange(taskManager.getCurrTaskTotal(), taskOrdinal);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Task task = taskManager.getTask(taskOrdinal - INDEX_OFFSET);
        task.markAsDone();
        OutputStringHandler.printTaskStatus(task, true);
    }
    public static void handleUnmarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        try {
            ErrorHandler.checkIfTaskOrdinalWithinRange(taskManager.getCurrTaskTotal(), taskOrdinal);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Task task = taskManager.getTask(taskOrdinal - INDEX_OFFSET);
        task.markAsNotDone();
        OutputStringHandler.printTaskStatus(task, false);
    }


    public static void handleInstruction(TaskManager taskManager, String userInputString) {
        Instruction instruction = InputStringHandler.parseUserInput(userInputString);
        Instruction.InstructionType instructionType = instruction.getInstructionType();
        switch (instructionType) {
        case TODO:
            String todoDesc = instruction.getInstructionDesc();
            handleAddInstruction(taskManager, todoDesc);
            break;
        case DEADLINE:
            String deadlineDesc = instruction.getInstructionDesc();
            String deadline = instruction.getTaskDates()[0];
            handleAddInstruction(taskManager, deadlineDesc, deadline);
            break;
        case EVENT:
            String eventDesc = instruction.getInstructionDesc();
            String startDate = instruction.getTaskDates()[0];
            String endDate = instruction.getTaskDates()[1];
            handleAddInstruction(taskManager, eventDesc, startDate, endDate);
            break;
        case LIST:
            OutputStringHandler.printTasks(taskManager.getAllTasks(), taskManager.getCurrTaskTotal());
            break;
        case MARK:
            handleMarkInstruction(taskManager, instruction.getTaskOrdinal());
            break;
        case UNMARK:
            handleUnmarkInstruction(taskManager, instruction.getTaskOrdinal());
            break;
        }
        // BYE instruction is not handled here
    }
}