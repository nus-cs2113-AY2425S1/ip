import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages saving and loading task list data to and from a text file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Finds the matching txt file and fills in a TaskList with the corresponding data.
     * Data is formatted on each line as SYMBOL | DONE | NAME | TIME1 | TIME2
     * @return the loaded task list
     * @throws GertrudeException if the save file isn't found
     * @throws CorruptedFileException if the save file doesn't have the correct format
     */
    public ArrayList<Task> load() throws GertrudeException, CorruptedFileException {
        try {
            File dataDirectory = new File("data");
            dataDirectory.mkdir();
            File f = new File(filePath);
            if (f.createNewFile()) {
                throw new GertrudeException();
            } else {
                System.out.println("Save file found. Loading save now.");
            }
            Scanner s = new Scanner(f);
            String line;
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (s.hasNext()) {
                line = s.nextLine();
                String[] lineArr = line.split(" \\| ");

                boolean isDone = Boolean.parseBoolean(lineArr[1]);

                switch (lineArr[0]) {
                case "T":
                    Todo newTodo = new Todo(lineArr[2]);
                    newTodo.isDone = isDone;
                    tasks.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(lineArr[2], lineArr[3]);
                    newDeadline.isDone = isDone;
                    tasks.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(lineArr[2], lineArr[3], lineArr[4]);
                    newEvent.isDone = isDone;
                    tasks.add(newEvent);
                    break;
                default:
                    throw new CorruptedFileException();
                }
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        }
        return null;
    }

    /**
     * Saves all tasks in a TaskList to a txt file.
     * Executes each iteration of the code.
     * @param tasks the TaskList to be saved
     * @throws IOException if an error with the file writer occurs
     * @throws FileNotFoundException if the file cannot be found
     */
    public void saveFile(TaskList tasks) throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.symbol + " | " + task.isDone + " | " + task.name + task.dataForSave() + System.lineSeparator());
        }
        fw.close();
    }
}
