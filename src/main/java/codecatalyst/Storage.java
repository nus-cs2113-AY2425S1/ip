package codecatalyst;

import codecatalyst.task.Deadline;
import codecatalyst.task.Event;
import codecatalyst.task.Task;
import codecatalyst.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class is responsible for reading tasks from storage (a file) and saving tasks back to the file.
 */
public class Storage {
    // Constants for task types and symbols used in file storage.
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String MARKED_SYMBOL = "1";
    private static final String UNMARKED_SYMBOL = "0";
    private static final String SEPARATOR1_SYMBOL = " | ";
    private static final String SEPARATOR2_SYMBOL = "-";
    
    private String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file into an {@code ArrayList}.
     * If the file or directory does not exist, they are created.
     *
     * @return An {@code ArrayList} containing the loaded tasks.
     * @throws IOException If an error occurs during file operations.
     */
    public ArrayList<Task> loadTasksFromFile() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        createDataDirectory(file);
        createTaskFile(file);
        Scanner s = new Scanner(file);
        processEveryLineInFile(s, tasks);
        return tasks;
    }

    /**
     * Creates the data directory if it does not already exist.
     *
     * @param file The file whose parent directory is checked or created.
     */
    public void createDataDirectory(File file) {
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Creates the task file if it does not already exist.
     *
     * @param file The file to be created.
     * @throws IOException If an error occurs during file creation.
     */
    public void createTaskFile(File file) throws IOException{
        file.createNewFile();
    }

    /**
     * Processes each line in the file to extract and add tasks to the task list.
     *
     * @param s The scanner object reading the file.
     * @param tasks The list of tasks to which the extracted tasks are added.
     */
    private static void processEveryLineInFile(Scanner s, ArrayList<Task> tasks) {
        while (s.hasNext()) {
            String[] parts = s.nextLine().split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals(MARKED_SYMBOL);
            String description = parts[2];
            switch (taskType) {
            case TODO_TYPE:
                ConvertLineToTodoTask(tasks, description, isDone);
                break;
            case DEADLINE_TYPE:
                ConvertLineToDeadlineTask(tasks, description, parts, isDone);
                break;
            case EVENT_TYPE:
                ConvertLineToEventTask(tasks, parts, description, isDone);
                break;
            default:
                System.out.println("Invalid task type: " + taskType);
            }

        }
    }

    /**
     * Converts a line in the file to an {@code Event} task and adds it to the task list.
     *
     * @param tasks The task list to which the {@code Event} task is added.
     * @param parts The parts of the task line, including start and end times.
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     */
    private static void ConvertLineToEventTask(ArrayList<Task> tasks, String[] parts, String description,
                                               boolean isDone) {
        String[] timeParts = parts[3].split("-");
        String startTime = timeParts[0];
        String endTime = timeParts[1];

        Event eventTask = new Event(description, startTime, endTime);
        if (isDone) {
            eventTask.markAsDone();
        }
        tasks.add(eventTask);
    }

    /**
     * Converts a line in the file to a {@code Deadline} task and adds it to the task list.
     *
     * @param tasks The task list to which the {@code Deadline} task is added.
     * @param description The description of the task.
     * @param parts The parts of the task line, including the due date.
     * @param isDone Whether the task is marked as done.
     */
    private static void ConvertLineToDeadlineTask(ArrayList<Task> tasks, String description, String[] parts,
                                                  boolean isDone) {
        Deadline deadlineTask = new Deadline(description, parts[3]);
        if (isDone) {
            deadlineTask.markAsDone();
        }
        tasks.add(deadlineTask);
    }

    /**
     * Converts a line in the file to a {@code Todo} task and adds it to the task list.
     *
     * @param tasks The task list to which the {@code Todo} task is added.
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     */
    private static void ConvertLineToTodoTask(ArrayList<Task> tasks, String description, boolean isDone) {
        Todo todoTask = new Todo(description);
        if (isDone) {
            todoTask.markAsDone();
        }
        tasks.add(todoTask);
    }

    /**
     * Saves the task list to the file.
     * Each task is saved in a specific format based on its type (Todo, Deadline, Event).
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs during file writing.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : tasks) {
            String CompletionNumber = task.isDone() ? MARKED_SYMBOL : UNMARKED_SYMBOL;

            if (task instanceof Todo){
                fw.write(TODO_TYPE + SEPARATOR1_SYMBOL + CompletionNumber +
                        SEPARATOR1_SYMBOL + task.getDescription() + "\n");
            } else if (task instanceof Deadline){
                fw.write(DEADLINE_TYPE + SEPARATOR1_SYMBOL + CompletionNumber +
                        SEPARATOR1_SYMBOL + task.getDescription() + SEPARATOR1_SYMBOL +
                        ((Deadline) task).getBy() + "\n");
            } else if (task instanceof Event){
                fw.write(EVENT_TYPE + SEPARATOR1_SYMBOL + CompletionNumber + 
                        SEPARATOR1_SYMBOL + task.getDescription() + SEPARATOR1_SYMBOL +
                        ((Event) task).getFrom() + SEPARATOR2_SYMBOL + ((Event) task).getTo() + "\n");
            }
        }
        fw.close();
    }
}
