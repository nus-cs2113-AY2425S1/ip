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

public class Storage {
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String MARKED_SYMBOL = "1";
    private static final String UNMARKED_SYMBOL = "0";
    private static final String SEPARATOR1_SYMBOL = " | ";
    private static final String SEPARATOR2_SYMBOL = "-";
    
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasksFromFile() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        createDataDirectory(file);
        createTaskFile(file);
        Scanner s = new Scanner(file);
        processEveryLineInFile(s, tasks);
        return tasks;
    }
    
    public void createDataDirectory(File file) {
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void createTaskFile(File file) throws IOException{
        file.createNewFile();
    }
    
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

    private static void ConvertLineToEventTask(ArrayList<Task> tasks, String[] parts, String description, boolean isDone) {
        String[] timeParts = parts[3].split("-");
        String startTime = timeParts[0];
        String endTime = timeParts[1];

        Event eventTask = new Event(description, startTime, endTime);
        if (isDone) {
            eventTask.markAsDone();
        }
        tasks.add(eventTask);
    }

    private static void ConvertLineToDeadlineTask(ArrayList<Task> tasks, String description, String[] parts, boolean isDone) {
        Deadline deadlineTask = new Deadline(description, parts[3]);
        if (isDone) {
            deadlineTask.markAsDone();
        }
        tasks.add(deadlineTask);
    }

    private static void ConvertLineToTodoTask(ArrayList<Task> tasks, String description, boolean isDone) {
        Todo todoTask = new Todo(description);
        if (isDone) {
            todoTask.markAsDone();
        }
        tasks.add(todoTask);
    }


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
