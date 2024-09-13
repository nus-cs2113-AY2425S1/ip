package yapper.instructions;

import yapper.TaskManager;
import yapper.Yapper;
import yapper.exceptions.ErrorHandler;
import yapper.stringHandlers.InputStringHandler;
import yapper.stringHandlers.OutputStringHandler;
import yapper.tasks.Task;
import yapper.tasks.TaskDeadline;
import yapper.tasks.TaskEvent;
import yapper.tasks.TaskTodo;

// Human-Yapper Interface. Should this be 2 Classes Instead?
public class InstructionHandler {
    private static final int INDEX_OFFSET = 1;

    // UI Operations
    public static void handleAddInstruction(TaskManager taskManager, String todoDesc) {
        ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        TaskTodo todo = new TaskTodo(todoDesc);
        taskManager.addTask(todo);
        todo.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String endDate) {
        ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        TaskDeadline deadline = new TaskDeadline(taskDesc, endDate);
        taskManager.addTask(deadline);
        deadline.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String startDate, String endDate) {
        ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        TaskEvent event = new TaskEvent(taskDesc, startDate, endDate);
        taskManager.addTask(event);
        event.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleMarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        ErrorHandler.checkIfTaskOrdinalWithinRange(taskManager.getCurrTaskTotal(), taskOrdinal);
        Task task = taskManager.getTask(taskOrdinal - INDEX_OFFSET);
        task.markAsDone();
        OutputStringHandler.printTaskStatus(task, true);
    }
    public static void handleUnmarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        ErrorHandler.checkIfTaskOrdinalWithinRange(taskManager.getCurrTaskTotal(), taskOrdinal);
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