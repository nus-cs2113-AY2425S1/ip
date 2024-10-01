import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, CorruptedFileException, IndexOutOfBoundsException, GertrudeException {
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
                    newTodo.done = isDone;
                    tasks.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(lineArr[2], lineArr[3]);
                    newDeadline.done = isDone;
                    tasks.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(lineArr[2], lineArr[3], lineArr[4]);
                    newEvent.done = isDone;
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

    public void saveFile(TaskList tasks) throws IOException, FileNotFoundException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTaskList()) {
            fw.write(task.symbol + " | " + task.done + " | " + task.name + task.dataForSave() + System.lineSeparator());
        }
        fw.close();
    }
}
