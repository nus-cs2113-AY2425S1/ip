package medea.command;
import medea.task.TaskManager;
import medea.task.Task;
import medea.exceptions.MedeaException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CommandHandler {
    private TaskManager taskManager;
    private final String TASK_DATA_PATH = "./data/medea.txt";
    private final int LINE_LENGTH = 50;

    public CommandHandler(){
        this.taskManager = new TaskManager();
    }

    public void loadTasks(){
        try{
            addTasksFromFile();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    private void addTasksFromFile() throws FileNotFoundException {
        File file = new File(TASK_DATA_PATH);
        if (!file.exists()) return;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] arguments = line.split(",");
            String taskType = arguments[0];

            switch(taskType){
                case "T":
                    loadTodo(arguments);
                    break;
                case "D":
                    loadDeadline(arguments);
                    break;
                case "E":
                    loadEvent(arguments);
                    break;
            }
        }
    }

    public void saveTasks(){
        String csvString = this.taskManager.toCSVString();
        try {
            writeTasksToFile(csvString);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void writeTasksToFile(String csvString) throws IOException {
        FileWriter fileWriter =  new FileWriter(TASK_DATA_PATH);
        fileWriter.write(csvString);
        fileWriter.close();
    }

    public void handleInput(String input){
        String[] inputArguments = input.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";

        Command command = Command.parseString(commandString);

        printLine();
        try{
            handleCommand(command, argumentString);
        }catch(MedeaException e){
            System.out.println(e.getMessage());
        }
        printLine();
    }

    private void printLine(){
        System.out.println("-".repeat(LINE_LENGTH));
    }

    private void handleCommand(Command command, String argumentString) {
        if (command == null) {
            handleInvalidCommand();
            return;
        }

        switch (command) {
            case LIST:
                handleListCommand();
                break;
            case MARK:
                handleTaskDoneUpdate(argumentString, true);
                break;
            case UNMARK:
                handleTaskDoneUpdate(argumentString, false);
                break;
            case DELETE:
                handleTaskDelete(argumentString);
                break;
            case TODO:
                handleTodoCommand(argumentString);
                break;
            case DEADLINE:
                handleDeadlineCommand(argumentString);
                break;
            case EVENT:
                handleEventCommand(argumentString);
                break;
        }
    }

    private void handleInvalidCommand(){
        throw new MedeaException("Unrecognized command. Please use a valid command.");
    }

    private void handleListCommand() {
        String taskListString = taskManager.getTaskListString();
        System.out.printf(taskListString);
    }

    private void handleTaskDoneUpdate(String taskIndex, boolean isDone) {
        int index = parseTaskIndex(taskIndex);
        int maxIndex = taskManager.getSize();

        if (index < 0 || index >= maxIndex) {
            String errorMsg = String.format("Invalid %s command. Please provide a valid task number.%n", isDone ? "mark":"unmark");
            throw new MedeaException(errorMsg);
        }

        Task updatedTask = taskManager.updateTaskDoneStatus(index, isDone);
        String notification = isDone ? "Nice! I've marked this task as done:%n  %s%n"
                                     : "OK, I've marked this task as not done yet:%n %s%n";
        System.out.printf(notification, updatedTask);
    }

    private void handleTaskDelete(String taskIndex) {
        int index = parseTaskIndex(taskIndex);
        int maxIndex = taskManager.getSize();

        if (index < 0 || index >= maxIndex) {
            throw new MedeaException("Invalid delete command. Please provide a valid task number.");
        }

        Task deletedTask = taskManager.deleteTask(index);
        String formatString = "Got it. I've deleted this task:%n  %s%nNow you have %d tasks in the list.%n";
        System.out.printf(formatString, deletedTask, taskManager.getSize());
    }

    private int parseTaskIndex(String taskIndex) {
        try {
            return Integer.parseInt(taskIndex.trim()) - 1;
        } catch (Error e) {
            return -1;
        }
    }

    private void handleTodoCommand(String description) {
        if (description.isEmpty()){
            throw new MedeaException("Invalid todo command. Please provide a task description.");
        }
        Task addedTask = taskManager.addTodo(description);
        printAddTaskMessage(addedTask);
    }

    private void loadTodo(String[] arguments){
        String description = arguments[1];
        taskManager.addTodo(description);
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }

    private void handleDeadlineCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /by ");

        if (arguments.length != 2) {
            throw new MedeaException("Invalid deadline command. Please provide a task description and a deadline using '/by'.");
        }

        String description = arguments[0];
        String deadlineDate = arguments[1];

        Task addedTask = taskManager.addDeadline(description, deadlineDate);
        printAddTaskMessage(addedTask);
    }

    private void loadDeadline(String[] arguments){

        String description = arguments[1];
        String deadlineDate = arguments[2];

        taskManager.addDeadline(description, deadlineDate);
    }

    private void handleEventCommand(String argumentString) {
        String[] arguments = parseArguments(argumentString, " /from ", " /to ");

        if (arguments.length != 3) {
            throw new MedeaException("Invalid event command. Please provide a task, start time, and end time using '/from' and '/to'.");
        }

        String description = arguments[0];
        String eventStart = arguments[1];
        String eventEnd = arguments[2];

        Task addedTask = taskManager.addEvent(description, eventStart, eventEnd);
        printAddTaskMessage(addedTask);
    }

    private void loadEvent(String[] arguments){
        String description = arguments[1];
        String eventStart = arguments[2];
        String eventEnd = arguments[3];

        taskManager.addEvent(description, eventStart, eventEnd);
    }

    private void printAddTaskMessage(Task addedTask){
        System.out.printf("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.%n", addedTask, taskManager.getSize());
    }
}
