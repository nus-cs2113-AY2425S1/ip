package CodyChen;

import CodyChen.Task.Task;
import CodyChen.Task.Todo;
import CodyChen.Task.Event;
import CodyChen.Task.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
/**
 * The Storage class handles the creation, loading, and saving of task data
 * to and from a specified file. It ensures that the file exists and is formatted
 * correctly for reading and writing task information.
 */
public class Storage {
    private File dataFile;
    /**
     * Returns the data file associated with this Storage instance.
     *
     * @return The File object representing the data file.
     */
    public File getDataFile() {
        return dataFile;
    }

    /**
     * Constructs a Storage object with the specified file name.
     *
     * @param dataFileName The name of the file to store task data.
     */
    public Storage(String dataFileName) {
        this.dataFile = new File(dataFileName);
    }

    /**
     * Creates the data file if it does not already exist.
     * If the file exists, it attempts to read from it.
     * Prints an error message if there is an issue during file creation.
     */
    public void createFile() {
        try {
            if (dataFile.exists()) {
                System.out.println("File found ... Attempting to read file");
            } else {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error! " + e.getMessage());
        }
    }

    /**
     * Reads the contents of the data file and returns a list of tasks.
     *
     * @return An ArrayList containing Task objects.
     * @throws IOException If there is an error reading the file.
     */

    private ArrayList readFile() throws IOException{
        if(dataFile.length() == 0){
            System.out.println("File is empty");
        }
        if(!dataFile.exists()){
            createFile();
            System.out.println("File created");
        }
        ArrayList<Task> tasks = (ArrayList) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return tasks;
    }

    /**
     * Loads task data from the data file and returns a list of tasks.
     *
     * @return An ArrayList containing Task objects, or null if there was an error.
     */
    public ArrayList<Task> loadData(){
        ArrayList<Task> filteredData = null;
        try{
            ArrayList<String> rawData = readFile();
            filteredData = parse(rawData);
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
        return filteredData;
    }

    /**
     * Parses a list of task strings and creates Task objects.
     *
     * @param taskString An ArrayList of strings representing tasks.
     * @return An ArrayList containing Task objects.
     */
    private ArrayList<Task> parse(ArrayList<String> taskString){
        ArrayList<Task> tasks = new ArrayList<>();

        for(String line : taskString){
            int firstBracer_Index = line.indexOf("[");
            int lastBracer_Index = firstBracer_Index + 7;
            String taskType = line.substring(firstBracer_Index + 1, firstBracer_Index + 2);
            String taskName;
            String deadline;
            String events;

            switch(taskType) {
            case "T": //add (todo)
                taskName = line.substring(lastBracer_Index);
                tasks.add(new Todo(taskName));
                break;

            case "D": //add (deadline)
                taskName = line.substring(lastBracer_Index, line.indexOf("(by: "));
                deadline = line.substring(line.indexOf("by: ") + 4, line.indexOf(")"));
                tasks.add(new Deadline(taskName, deadline));
                break;

            case "E": //add (event)
                taskName = line.substring(lastBracer_Index, line.indexOf("(by: "));
                deadline = line.substring(line.indexOf("(by: ") + 5, line.indexOf(" to:"));
                events = line.substring(line.indexOf("to: ") + 4, line.indexOf(")"));
                tasks.add(new Event(taskName, deadline, events));
                break;

            default:
                System.out.println("Invalid task type: " + taskType);
            }

            int currentSize = tasks.size() - 1;

            if(line.substring(line.indexOf("]["),line.indexOf("][") + 2).equals("X")){
                tasks.get(currentSize).markDone();
            }
        }

        return tasks;
    }

    /**
     * Saves the current list of tasks to the data file.
     *
     * @param taskList The TaskList object containing tasks to save.
     */
    public void save(TaskList taskList){
        ArrayList<Task> tasks = taskList.getTask();
        try {
            FileWriter fw = new FileWriter("./harddisk.txt");
            int loop = 1;
            for (Task task : tasks) {
                char type = task.getType();
                fw.write(loop +". ");
                fw.write("[" + type + "]");
                fw.write(task.getStatusIcon());
                switch(type){
                case 'T': fw.write(task.getDescription());
                    break;
                case 'D': fw.write(task.getDescription());
                    fw.write ("(by: " + task.formattedDeadline() + ")");
                    break;
                case 'E': fw.write(task.getDescription());
                    fw.write("(by: " + task.formattedDeadline());
                    fw.write(task.formattedEvent());
                    break;
                }
                fw.write("\n");
                loop += 1;
            }
            fw.close();

        } catch (Exception e) {
            System.out.println("Unable to be stored to file");
        }
    }
}
