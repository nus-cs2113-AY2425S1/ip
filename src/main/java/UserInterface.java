import java.util.Scanner;

// Human-Yapper Interface. Should this be 2 Classes Instead?
public class UserInterface {
    // UI Operations
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc) {
        Task task = new Task(taskDesc);
        taskManager.addTask(task);
        OutputStringHandler.printAddedTask(taskManager.getTaskCount(), task);
    }
    public static void handleAddInstruction(TaskManager taskManager, String todoDesc, Instruction.InstructionType type) {
        TaskTodo todo = new TaskTodo(todoDesc); // type is to differentiate between overriden method handleAddInstruction() for task vs todo
        taskManager.addTask(todo);
        OutputStringHandler.printAddedTask(taskManager.getTaskCount(), todo);
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String endDate) {
        TaskDeadline deadline = new TaskDeadline(taskDesc, endDate);
        taskManager.addTask(deadline);
        OutputStringHandler.printAddedTask(taskManager.getTaskCount(), deadline);
    }
    public static void handleAddInstruction(TaskManager taskManager, String taskDesc, String startDate, String endDate) {
        TaskEvent event = new TaskEvent(taskDesc, startDate, endDate);
        taskManager.addTask(event);
        OutputStringHandler.printAddedTask(taskManager.getTaskCount(), event);
    }
    public static void handleMarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        Task task = taskManager.getTask(taskOrdinal - 1); // 0-Indexed
        task.markAsDone();
        OutputStringHandler.printTaskStatus(task, true);
    }
    public static void handleUnmarkInstruction(TaskManager taskManager, Integer taskOrdinal) {
        Task task = taskManager.getTask(taskOrdinal - 1); // 0-Indexed
        task.markAsNotDone();
        OutputStringHandler.printTaskStatus(task, false);
    }

    // Main ChatBot Loop
    public static void startYappin(TaskManager taskManager) {
        System.out.println(CommonStrings.START_UP_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInputString = scanner.nextLine();
            Instruction instruction = InputStringHandler.parseUserInput(userInputString);
            Instruction.InstructionType instructionType = instruction.getInstructionType();
            switch (instructionType) {
            case ADD:
                String taskDesc = instruction.getInstructionStringArg();
                handleAddInstruction(taskManager, taskDesc);
                break;
            case TODO:
                String todoDesc = instruction.getInstructionStringArg();
                handleAddInstruction(taskManager, todoDesc, instructionType);
                break;
            case DEADLINE:
                String deadlineDesc = instruction.getInstructionStringArgs()[0];
                String deadline = instruction.getInstructionStringArgs()[1];
                handleAddInstruction(taskManager, deadlineDesc, deadline);
                break;
            case EVENT:
                String eventDesc = instruction.getInstructionStringArgs()[0];
                String startDate = instruction.getInstructionStringArgs()[1];
                String endDate = instruction.getInstructionStringArgs()[2];
                handleAddInstruction(taskManager, eventDesc, startDate, endDate);
                break;
            case LIST:
                OutputStringHandler.printTasks(taskManager.getAllTasks(), taskManager.getTaskCount());
                break;
            case MARK:
                handleMarkInstruction(taskManager, instruction.getInstructionIntegerArg());
                break;
            case UNMARK:
                handleUnmarkInstruction(taskManager, instruction.getInstructionIntegerArg());
                break;
            case BYE:
                System.out.println(CommonStrings.SHUT_DOWN_MESSAGE);
                return;
            }
            // TBA ?
        }
    }
}
