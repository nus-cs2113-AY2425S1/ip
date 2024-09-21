import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileClass {
    private String filePath; 

    // Check if the storage file existed. If not, created a new file 
    public FileClass(String filePath) {
        this.filePath = filePath; 
        try {
            File file = new File(filePath); 
            if (!file.exists()) {
                file.getParentFile().mkdirs(); 
                file.createNewFile(); 
                System.out.println("New file created successfully");
            } 
        } catch (IOException e) {
            System.out.println("Error creating new file!"); 
        }
    }

    // Read the pre-stored tasks in the file into the current Tasks list
    public ArrayList<Task> read() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath); 
        if (f.exists()) {
            Scanner s = new Scanner(f); 
            while (s.hasNext()) {
                String line = s.nextLine(); 
                Task task = parseTask(line); 
                if (task != null) {
                    tasks.add(task);  
                }
            }
        } else {
            System.out.println("File not found, starting with an empty task list.");
        }
        return tasks; 
    }

    // Save the tasks into the file
    public void write(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath); 
        for (Task task: tasks) {
            fw.write(task.writeFile() + System.lineSeparator()); 
        }
        fw.close(); 
    }

    // Pre-process tasks from the file
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| "); 
        String type = parts[0]; 
        Boolean isDone = parts[1].equals("1"); 
        switch (type) {
            case "T": 
                return new Todo(parts[2], isDone); 
            case "D": 
                return new Deadline(parts[2], parts[3], isDone); 
            case "E": 
                return new Event(parts[2], parts[3], parts[4], isDone); 
            default: 
                return null; 
        }
    }
}
