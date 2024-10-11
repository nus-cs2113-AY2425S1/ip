import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    private static final String FILE_PATH = "./data/Amy.txt";
    public static void saveTasks(ArrayList<Task> taskList){
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(file)))){
            for (Task i : taskList){
                writer.write(i.saveAsString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
    private static Task taskParser(String line){
        try{
            String[] task = line.split(" \\| ");
            switch (task[0]) {
                case "T" -> {
                    return new Todo(task[1], (task[2].equals("true")));
                }
                case "D" -> {
                    return new Deadline(task[1], (task[2].equals("true")), task[3]);
                }
                case "E" -> {
                    return new Event(task[1], (task[2].equals("true")), task[3], task[4]);
                }
            }
        } catch (IndexOutOfBoundsException e){
            System.err.println("Task is not stored in the correct format");
        } catch (Exception e) {
            System.err.println("Error loading task");
        }
        return null;
    }
    public static ArrayList<Task> loadTasks(){
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if(!file.exists()){
            return taskList;
        }
        try (BufferedReader reader = new BufferedReader((new FileReader(file)))){
            String line;
            while( (line = reader.readLine()) != null){
                Task task = taskParser(line);
                taskList.add(task);
            }
        } catch(IOException e){
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }
}
