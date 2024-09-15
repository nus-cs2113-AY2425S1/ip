package yapper.instructions;

import yapper.tasks.TaskHandler;
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
    public static void handleAddInstruction(TaskHandler taskHandler, Task task) {
        taskHandler.addTask(task);
    }
    public static void handleMarkingInstruction(TaskHandler taskHandler, Integer taskOrdinal, boolean isDone) {
        try {
            ErrorHandler.checkIfTaskOrdinalWithinRange(taskHandler.getCurrTaskTotal(), taskOrdinal);
        } catch (YapperException e) {
            System.out.println(e.getMessage());
            return;
        }
        taskHandler.updateTaskStatus(taskOrdinal - INDEX_OFFSET, isDone);
    }


    public static void handleInstruction(TaskHandler taskHandler, String userInputString) {
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
        case LIST:
            OutputStringHandler.printTasks(taskHandler.getAllTasks(),
                    taskHandler.getCurrTaskTotal());
            break;
        case MARK:
            handleMarkingInstruction(taskHandler,
                    instruction.getTaskOrdinal(), true);
            break;
        case UNMARK:
            handleMarkingInstruction(taskHandler,
                    instruction.getTaskOrdinal(), false);
            break;
        } // BYE instruction is not handled here
    }
}