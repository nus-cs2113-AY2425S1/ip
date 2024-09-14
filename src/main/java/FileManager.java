import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public static final String FILE_NAME = "yappatron.txt";

    public static void createFile(){
        try {
            File f = new File(FILE_NAME);
            if (f.createNewFile()) {
                System.out.println("File has been created successfully");
            }
        }catch (IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }


    public static void printFile(String filepath) throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String line = s.nextLine();
            if (line.startsWith("[T]")) {
                // Extract the completion status and description
                String splitLine = line.substring(line.lastIndexOf("]")+1);
                Todo todo = new Todo(splitLine.trim());
                Yappatron.taskArray[Yappatron.taskNumber++] = todo;
                if (line.contains("[X]")){
                    todo.markAsDone();
                }
            }


            else if (line.startsWith("[D]")){
                int indexOfBy = line.indexOf("(by:");
                String activity = line.substring(line.lastIndexOf("]")+2, indexOfBy-1);
                String by = line.substring(indexOfBy + 5, line.length()-1);
                Deadline deadline = new Deadline(activity, by);
                Yappatron.taskArray[Yappatron.taskNumber++] = deadline;
                if (line.contains("[X]")){
                    deadline.markAsDone();
                }
            }
            else{
                int indexOfBy = line.indexOf("(by:");
                int indexOfTo = line.lastIndexOf("to");
                String activity = line.substring(line.lastIndexOf("]")+2, indexOfBy-1);
                String by = line.substring(indexOfBy + 5, indexOfTo-1);
                String to = line.substring(indexOfTo+4, line.length()-1);
                Events event = new Events(activity, by, to);
                Yappatron.taskArray[Yappatron.taskNumber++] = event;
                if (line.contains("[X]")){
                    event.markAsDone();
                }
            }
        }
    }


}
