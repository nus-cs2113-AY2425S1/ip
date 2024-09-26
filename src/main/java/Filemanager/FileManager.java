package Filemanager;

import Commands.Parser;
import Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static final String FILE_NAME = "./data/yappatron.txt";
    private File f;
    public FileManager(){
        f = new File(FILE_NAME);
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
        }
    }


    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> taskArray = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String line = s.nextLine();

            if (line.startsWith("[T]")) {
                Parser.parseTodoFromFile(line, taskArray);
            } else if (line.startsWith("[D]")){
                Parser.parseDeadlineFromFile(line, taskArray);
            } else {
                Parser.parseEventFromFile(line, taskArray);
            }
        }

        return taskArray;
    }

    public void writeFile(ArrayList<Task> taskArray) throws IOException{

        FileWriter fileWriter = new FileWriter(FILE_NAME);

        for (int i=0; i<taskArray.size(); i++){
            fileWriter.write(taskArray.get(i).toString() + System.lineSeparator());
        }
        fileWriter.close();

        }

    }




