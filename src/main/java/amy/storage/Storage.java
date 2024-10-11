package storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import task.Task;
import task.TaskList;

public class Storage {
    private static Path filePath;
    public Storage(String filePath){
        this.filePath = Paths.get(filePath);
    }
    public void save(TaskList tasks) throws IOException{
        ArrayList<Task> taskList = tasks.getTaskList();
        List<String> lines = new ArrayList<>();
        for (Task task : taskList){
            lines.add(task.saveAsString());
        }


        try {
            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws IOException{
        ArrayList<Task> taskList = new ArrayList<>();
        
        if(!Files.exists(filePath)){
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return taskList;
        }
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines){
            Task task = Task.fromStorage(line);
            taskList.add(task);
        }
        return taskList;
    }
}
