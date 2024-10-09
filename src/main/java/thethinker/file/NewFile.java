package thethinker.file;

import thethinker.tasks.Deadline;
import thethinker.tasks.Event;
import thethinker.tasks.Task;
import thethinker.tasks.TaskList;
import thethinker.tasks.Todo;
import thethinker.ui.UiControl;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

/**
 * NewFile class handles file related operations
 */
public class NewFile {

    public File file;

    public NewFile(String filename) {

        this.file = new File(FileLoader.FILE_DIR + "/" +  filename);
    }

    public boolean isFileExist() {
        return this.file.exists();
    }

    /**
     * Read content of a file and adds all task to current task list.
     *
     * @throws FileNotFoundException If the filepath is invalid.
     */
    public void loadFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] parameters = input.split(" \\| ");
            addTaskAccordingToFileData(parameters);
        }

        System.out.println("File data loaded");
        UiControl.printSeparation();
        TaskList.listTasks();
    }

    /**
     * Adds task to task list without printing anything to the console.
     * If task type is not T or D or E , the task will be ignored.
     *
     * @param parameters A string array containing values to initialise different task.
     *
     */
    private void addTaskAccordingToFileData(String[] parameters){

        switch (parameters[0]) {
        case "T" :
            TaskList.addTaskWithoutResponse(new Todo(parameters[2]));
            if(Boolean.parseBoolean(parameters[1])){
                TaskList.setAsDone(TaskList.listLength);
            }
            break;
        case "E" :
            TaskList.addTaskWithoutResponse(new Event(parameters[2] , parameters[3] , parameters[4]));
            if(Boolean.parseBoolean(parameters[1])){
                TaskList.setAsDone(TaskList.listLength);
            }
            break;

        case "D" :
            TaskList.addTaskWithoutResponse(new Deadline(parameters[2] , parameters[3]));
            if(Boolean.parseBoolean(parameters[1])){
                TaskList.setAsDone(TaskList.listLength);
            }
            break;

        default :
            System.out.println("Wrong task type");
            break;
        }
    }

    /**
     * @throws IOException If the filepath is invalid.
     */
    public void writeTaskListToFile() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String textToAdd = convertTaskListToString();
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Converts task list into a load file format
     * If TaskList is empty , an empty string will be returned.
     *
     * @return A single string of task list in file format.
     */
    private String convertTaskListToString(){
        StringBuilder TaskListString = new StringBuilder();

        for(Task task : TaskList.listOfTasks) {
            String taskInFileFormat = task.convertToFileFormat();
            TaskListString.append(taskInFileFormat).append("\n");
        }

        return TaskListString.toString();
    }



}
