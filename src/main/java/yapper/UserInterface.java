package yapper;

import yapper.tasks.Task;
import yapper.tasks.TaskDeadline;
import yapper.tasks.TaskEvent;
import yapper.tasks.TaskTodo;

import java.util.Scanner;

// Human-Yapper Interface. Should this be 2 Classes Instead?
public class UserInterface {
    private static final int INDEX_OFFSET = 1;

    // UI Operations
    public static void handleAddInstruction(TaskManager taskManager, String todoDesc) {
        if (taskManager.getCurrTaskTotal() == Yapper.maxCapacity) System.out.println("list full");
        TaskTodo todo = new TaskTodo(todoDesc);
        taskManager.addTask(todo);
        todo.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String endDate) {
        if (taskManager.getCurrTaskTotal() == Yapper.maxCapacity) System.out.println("list full");
        TaskDeadline deadline = new TaskDeadline(taskDesc, endDate);
        taskManager.addTask(deadline);
        deadline.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String startDate, String endDate) {
        if (taskManager.getCurrTaskTotal() == Yapper.maxCapacity) System.out.println("list full");
        TaskEvent event = new TaskEvent(taskDesc, startDate, endDate);
        taskManager.addTask(event);
        event.printAddedTask(taskManager.getCurrTaskTotal());
    }
    public static void handleMarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        int currTaskTotal = taskManager.getCurrTaskTotal();
        if (taskOrdinal >= 0 && taskOrdinal < currTaskTotal) System.out.println("ordinal invalid");
        Task task = taskManager.getTask(taskOrdinal - INDEX_OFFSET);
        task.markAsDone();
        OutputStringHandler.printTaskStatus(task, true);
    }
    public static void handleUnmarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        int currTaskTotal = taskManager.getCurrTaskTotal();
        if (taskOrdinal >= 0 && taskOrdinal < currTaskTotal) System.out.println("ordinal invalid");
        Task task = taskManager.getTask(taskOrdinal - INDEX_OFFSET);
        task.markAsNotDone();
        OutputStringHandler.printTaskStatus(task, false);
    }

    // Main ChatBot Loop
    public static void startYappin(TaskManager taskManager) {
        System.out.println(StringStorage.START_UP_MESSAGE);
//        System.out.println(StringStorage.HELP_MESSAGE); // TODO
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInputString = scanner.nextLine();
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
            case BYE:
                System.out.println(StringStorage.SHUT_DOWN_MESSAGE);
                return;
            }
            // TBA ?
        }
    }
}
