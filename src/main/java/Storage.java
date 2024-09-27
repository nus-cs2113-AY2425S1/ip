import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) throws CuboydException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.getTask(i) instanceof ToDo todo) {
                    fw.write(String.format(
                            "T|%s|%s\n",
                            todo.getStatusIcon(),
                            Base64.getEncoder().encodeToString(todo.getDescription().getBytes(StandardCharsets.UTF_8))
                    ));
                } else if (taskList.getTask(i) instanceof Deadline deadline) {
                    fw.write(String.format(
                            "D|%s|%s|%s\n",
                            deadline.getStatusIcon(),
                            Base64.getEncoder().encodeToString(
                                    deadline.getDescription().getBytes(StandardCharsets.UTF_8)),
                            Base64.getEncoder().encodeToString(deadline.by.getBytes(StandardCharsets.UTF_8))
                    ));
                } else if (taskList.getTask(i) instanceof Event event) {
                    fw.write(String.format(
                            "E|%s|%s|%s|%s\n",
                            event.getStatusIcon(),
                            Base64.getEncoder().encodeToString(event.getDescription().getBytes(StandardCharsets.UTF_8)),
                            Base64.getEncoder().encodeToString(event.from.getBytes(StandardCharsets.UTF_8)),
                            Base64.getEncoder().encodeToString(event.to.getBytes(StandardCharsets.UTF_8))
                    ));
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new CuboydException(String.format("Having issues saving to %s! Check your file permissions!\n", filePath));
            //System.out.println(e.getMessage());
        }
    }

    private void loadFromScanner(Scanner s, TaskList taskList) throws Exception {
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] list = line.split("\\|");
            Task currentTask = null;
            switch (list[0]){
            case "T":
                currentTask = new ToDo(
                        new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8)
                );
                break;
            case "D":
                currentTask = new Deadline(
                        new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8),
                        new String(Base64.getDecoder().decode(list[3]), StandardCharsets.UTF_8)
                );
                break;
            case "E":
                currentTask = new Event(
                        new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8),
                        new String(Base64.getDecoder().decode(list[3]), StandardCharsets.UTF_8),
                        new String(Base64.getDecoder().decode(list[4]), StandardCharsets.UTF_8)
                );
                break;
            }
            if (currentTask == null){ throw new Exception(); }
            if (Objects.equals(list[1], "X")) {
                currentTask.markAsDone();
            }
            taskList.addTask(currentTask);
        }
    }
    public void load(TaskList taskList) throws CuboydException {
        File f = new File(filePath);
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new CuboydException(String.format("Having issues reading from %s! Check your file permissions!\n", filePath));
        }

        taskList.clear();
        try {
            loadFromScanner(s, taskList);
        } catch (Exception e) {
            throw new CuboydException(String.format("Error when reading from %s! Savefile might be corrupted!\n", filePath));
            //e.printStackTrace();
        }
    }
}