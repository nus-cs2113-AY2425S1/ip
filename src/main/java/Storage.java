import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Storage {
    //define filepath
    private static final String FILE_PATH = "./data/quag.txt";

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
                        Deadline deadline = new Deadline(data[2], data[3]);
                        if (data[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        TaskList.tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(data[2],data[3],data[4]);
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
