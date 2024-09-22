import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public static final String FILE_NAME = "./data/yappatron.txt";
    private static File f;
    public FileManager(){
        f = new File(FILE_NAME);
    }
    public File getFile(){
        return f;
    }
    public void createFile(){
        try {

            if (f.exists()) {
                System.out.println("This file exists");
                return;
            }
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }catch (IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }


    public void printFile() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String line = s.nextLine();
            if (line.startsWith("[T]")) {
                // Extract the completion status and description
                String splitLine = line.substring(line.lastIndexOf("]")+1);
                Todo todo = new Todo(splitLine.trim());
                Yappatron.taskArray.add(todo);
                if (line.contains("[X]")){
                    todo.markAsDone();
                }
            }


            else if (line.startsWith("[D]")){
                int indexOfBy = line.indexOf("(by:");
                String activity = line.substring(line.lastIndexOf("]")+2, indexOfBy-1);
                String by = line.substring(indexOfBy + 4, line.length()-1);
                Deadline deadline = new Deadline(activity.trim(), by.trim());
                Yappatron.taskArray.add(deadline);
                if (line.contains("[X]")){
                    deadline.markAsDone();
                }
            }
            else{
                int indexOfFrom = line.indexOf("(from:");
                int indexOfTo = line.lastIndexOf("to");
                String activity = line.substring(line.lastIndexOf("]")+2, indexOfFrom-1);
                String by = line.substring(indexOfFrom + 6, indexOfTo-1);
                String to = line.substring(indexOfTo+3, line.length()-1);
                Events event = new Events(activity.trim(), by.trim(), to.trim());
                Yappatron.taskArray.add(event);
                if (line.contains("[X]")){
                    event.markAsDone();
                }
            }
        }
    }

    public void writeFile() throws IOException{

        FileWriter fileWriter = new FileWriter(FILE_NAME);

        for (int i=0; i<Yappatron.taskArray.size(); i++){
            fileWriter.write(Yappatron.taskArray.get(i).toString() + System.lineSeparator());
        }
        fileWriter.close();

        }

    }




