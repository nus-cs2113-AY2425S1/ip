package Filemanager;

import Commands.Parser;
import Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates the txt file for data storage. The class also reads and writes from/to the txt file before and after
 * each program execution, to save the input data for the next run.
 */
public class FileManager {
    public static final String FILE_NAME = "./data/yappatron.txt";
    private File f;
    public FileManager(){
        f = new File(FILE_NAME);
    }

    /**
     * Creates the txt file if it does not already exist
     */
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

    /**
     * Processes the txt file to create Task objects that correspond to the entries in the file.
     * @return ArrayList of Task objects
     * @throws FileNotFoundException if file does not exist
     */
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

    /**
     * Writes to file before program ends.
     * @param taskArray ArrayList of Task objects to be written to file
     * @throws IOException on input error
     */
    public void writeFile(ArrayList<Task> taskArray) throws IOException{

        FileWriter fileWriter = new FileWriter(FILE_NAME);

        for (int i=0; i<taskArray.size(); i++){
            fileWriter.write(taskArray.get(i).toString() + System.lineSeparator());
        }
        fileWriter.close();

        }

    }




