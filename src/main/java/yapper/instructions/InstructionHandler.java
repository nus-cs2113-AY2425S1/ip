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
    public static void handleAddInstruction(TaskManager taskManager, Task task) {
        try {
            ErrorHandler.checkIfListFull(taskManager.getCurrTaskTotal(), Yapper.maxCapacity);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
            return;
        }
        taskManager.addTask(task);
    }
    public static void handleMarkingInstruction(TaskManager taskManager, Integer taskOrdinal, boolean isDone) {
        try {
            ErrorHandler.checkIfTaskOrdinalWithinRange(taskManager.getCurrTaskTotal(), taskOrdinal);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
            return;
        }
        taskManager.updateTaskStatus(taskOrdinal - INDEX_OFFSET, isDone);
    }


    public static void handleInstruction(TaskManager taskManager, String userInputString) {
        Instruction instruction = null;
        try {
            instruction = InputStringHandler.parseUserInput(userInputString);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        Instruction.InstructionType instructionType = instruction.getInstructionType();
        switch (instructionType) {
        case TODO:
            String todoDesc = instruction.getInstructionDesc();
            handleAddInstruction(taskManager,
                    new Todo(todoDesc) );
            break;
        case DEADLINE:
            String deadlineDesc = instruction.getInstructionDesc();
            String deadline = instruction.getTaskDates()[0];
            handleAddInstruction(taskManager,
                    new Deadline(deadlineDesc, deadline) );
            break;
        case EVENT:
            String eventDesc = instruction.getInstructionDesc();
            String startDate = instruction.getTaskDates()[0];
            String endDate = instruction.getTaskDates()[1];
            handleAddInstruction(taskManager,
                    new Event(eventDesc, startDate, endDate) );
            break;
        case LIST:
            OutputStringHandler.printTasks(taskManager.getAllTasks(),
                    taskManager.getCurrTaskTotal());
            break;
        case MARK:
            handleMarkingInstruction(taskManager,
                    instruction.getTaskOrdinal(), true);
            break;
        case UNMARK:
            handleMarkingInstruction(taskManager,
                    instruction.getTaskOrdinal(), false);
            break;
        } // BYE instruction is not handled here
    }
}