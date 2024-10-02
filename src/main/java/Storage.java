import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the saving and loading of tasks from a file.
 * The tasks are saved in a specified format and can be restored upon loading from the file.
 */
public class Storage {
    //define filepath
    private static final String FILE_PATH = "./data/quag.txt";

    /**
     * Saves all the tasks in the TaskList to a file.
     * Tasks are stored in a specific format, depending on their type (Todo, Deadline, Event).
     */
    public static void saveToFile() {
        try {
            File dir = new File("./data");
            //create directory if file does not exist
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : TaskList.tasks) {
                if (task instanceof Todo) {
                    writer.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + "\n");
                } else if (task instanceof Deadline) {
                    writer.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by + "\n");
                } else if (task instanceof Event) {
                    writer.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to + "\n");
                }

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into the TaskList.
     * The method parses each line to recreate the appropriate Task (Todo, Deadline, or Event)
     * and adds it to the TaskList.
     */
    public static void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No such file! quag");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" \\| ");
                switch (data[0]) {
                    case "T":
                        Todo todo = new Todo(data[2]);
                        if (data[1].equals("1")) {
                            todo.markAsDone();
                        }
                        TaskList.tasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime deadlineTime = LocalDateTime.parse(data[3]);
                        Deadline deadline = new Deadline(data[2], deadlineTime);
                        if (data[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        TaskList.tasks.add(deadline);
                        break;
                    case "E":
                        String startTimeString = data[3];
                        String endTimeString = data[4];
                        LocalDateTime startTime = LocalDateTime.parse(startTimeString);
                        LocalDateTime endTime = LocalDateTime.parse(endTimeString);
                        Event event = new Event(data[2],startTime,endTime);
                        if (data[1].equals("1")) {
                            event.markAsDone();
                        }
                        TaskList.tasks.add(event);
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }
    }

}
