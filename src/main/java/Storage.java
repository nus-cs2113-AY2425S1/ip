import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/* 
 * The Storage class reads task from the file and write the updated tasks into the file
 */
public class Storage {

    private String filePath; 

    /**  
     * Check if the storage file is created. If not, then create a new file
     * 
     * @param filePath The file path to store tasks.
     */
    public Storage(String filePath) {
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

    /**
     * Reads tasks from the storage file and loads them into the ArrayList of the current chatbot.
     *
     * @return An ArrayList of Task objects that were stored in the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
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

    /**
     * Saves the task into the file
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If there is an error during writing to the file.
     */
    public void write(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath); 
        for (Task task: tasks) {
            fw.write(task.writeFile() + System.lineSeparator()); 
        }
        fw.close(); 
    }

    /**
     * Parses a task from a line in the storage file.
     *
     * @param line A line of text representing the task.
     * @return A Task object. 
     */
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
