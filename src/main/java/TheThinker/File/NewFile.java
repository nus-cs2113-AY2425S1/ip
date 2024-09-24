package TheThinker.File;

import TheThinker.Tasks.Deadline;
import TheThinker.Tasks.Event;
import TheThinker.Tasks.Task;
import TheThinker.Tasks.TaskList;
import TheThinker.Tasks.Todo;
import TheThinker.Ui.UiControl;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;

public class NewFile {

    public File file;
    public final String FILE_DIR_FROM_IP_DIR = "ip/src/main/java/TheThinker/Data";

    public NewFile(String filename) {

        String filePath = getRemainingPath();

        if(filePath.equals("invalid")){
            System.out.println("Please place jar file within ip directory");
        }else {
            File directory = new File(filePath);

            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("Directory Data does not exist");
                System.out.println("Please create Package /Data under src/main/java/TheThinker/");
            }
        }

        this.file = new File(getRemainingPath() + "/" + filename);
    }

    /**
     * Returns path from anywhere within ip project to the text file which is Data directory
     *
     * @return Path
     */
    public String getRemainingPath(){
        String pwd = System.getProperty("user.dir").replace("\\", "/");
        String[] userDirectories = pwd.split("/");
        String[] absolutePathDir = FILE_DIR_FROM_IP_DIR.split("/");
        int matchingDirIndexInUserPath = 0;
        int matchingDirIndexInAbsolutePath = 0;
        String matchingDir = "";
        for(int i = userDirectories.length - 1; i >= 0; i--){
            for(int j = absolutePathDir.length - 1; j >= 0; j--){
                if(absolutePathDir[j].equals(userDirectories[i])){
                    matchingDirIndexInAbsolutePath = j;
                    matchingDirIndexInUserPath = i;
                    matchingDir = userDirectories[i];
                }
            }
        }

        if(matchingDir.isEmpty() && matchingDirIndexInUserPath == 0 && matchingDirIndexInAbsolutePath == 0){
            return "invalid";
        }
        String newConstructedPath = "../".repeat(userDirectories.length - matchingDirIndexInUserPath - 1);
        int indexOfMatchingDirInAbsolutePath = FILE_DIR_FROM_IP_DIR.indexOf(matchingDir);
        newConstructedPath += FILE_DIR_FROM_IP_DIR.substring(indexOfMatchingDirInAbsolutePath + matchingDir.length()+1);
        return newConstructedPath;
    }

    public boolean isFileExist() {
        return this.file.exists();
    }

    /**
     * Reads the contents of the file and adds the task to current task list.
     *
     * @throws FileNotFoundException If the filepath is invalid.
     */
    public void loadFile() throws FileNotFoundException {
        Scanner SCANNER = new Scanner(this.file);

        while (SCANNER.hasNext()) {
            String input = SCANNER.nextLine();
            String[] parameters = input.split(" \\| ");
            addTaskAccordingToFileData(parameters);
        }

        System.out.println("File data loaded");
        UiControl.printSeparation();
        TaskList.listTasks();
    }

    /**
     * Adds task without reprinting the task to the user.
     * If task type is not T or D or E , the task will be ignored.
     *
     * @param parameters A string array containing parameters to initialise different task.
     *                   Task : [task type , task description].
     *                   Deadline : [task type , task description , deadline].
     *                   Event : [task type , task description , start time , end time].
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
     *
     * Converts all tasks in task list to file format and writes to file.
     *
     * @throws IOException If the filepath is invalid.
     */
    public void writeTaskToFile() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String textToAdd = convertTaskListToString();
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns a string of task list in file format with
     * Each task member variable is seperated by "|" and each task seperated by "/n".
     * If TaskList is empty , an empty string will be returned.
     *
     * @return A single string of task list in file format with "/n" included.
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
