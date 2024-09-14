package TheThinker.NewFile;
import TheThinker.Command.TheThinker;
import TheThinker.Tasks.Deadline;
import TheThinker.Tasks.Event;
import TheThinker.Tasks.Task;
import TheThinker.Tasks.Todo;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class NewFile {

    public File file;

    public NewFile(String filename) {
        File directory = new File("src/main/java/TheThinker/Data");

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory Data does not exist");
            System.out.println("Please create Package /Data under src/main/java/TheThinker/");
        }

        this.file = new File("src/main/java/TheThinker/Data/" + filename);
    }

    public void loadFile() throws FileNotFoundException {
        Scanner SCANNER = new Scanner(this.file);

        while (SCANNER.hasNext()) {
            String input = SCANNER.nextLine();
            String[] parameters = input.split(" \\| ");
            doAccordingToParameters(parameters);
        }

        System.out.println("File data loaded");
        TheThinker.printSeparation();
        Task.listTasks();
    }

    public void doAccordingToParameters(String[] parameters){
        switch (parameters[0]) {
            case "T" :
                Task.addTaskWithoutResponse(new Todo(parameters[2]));
                if(Boolean.parseBoolean(parameters[1])){
                    Task.setAsDone(Task.listLength);
                }
                break;
            case "E" :
                Task.addTaskWithoutResponse(new Event(parameters[2] , parameters[3] , parameters[4]));
                if(Boolean.parseBoolean(parameters[1])){
                    Task.setAsDone(Task.listLength);
                }
                break;

            case "D" :
                Task.addTaskWithoutResponse(new Deadline(parameters[2] , parameters[3]));
                if(Boolean.parseBoolean(parameters[1])){
                    Task.setAsDone(Task.listLength);
                }
                break;

            default :
                System.out.println("Wrong task type");
                break;
        }
    }

    public void writeTaskToFile() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String textToAdd = convertTaskListToString();
        fw.write(textToAdd);
        //System.out.println("File data written");
        fw.close();
    }

    public String convertTaskListToString(){
        StringBuilder TaskListString = new StringBuilder();
        for(Task task : Task.listOfTasks) {
            String taskInFileFormat = task.convertToFileFormat();
            TaskListString.append(taskInFileFormat).append("\n");
        }
        return TaskListString.toString();
    }



}
