import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the text data stored at the filepath member
     * If the file does not exist, it is created by Java
     * @return The text data stored in the file, if it was just created then returns null
     * @throws IOException Due to failure of Scanner or createNewFile
     */
    public String loadFile() throws IOException {
        File f = new File(filePath);
        if (f.createNewFile()){
            System.out.println("New save file created successfully");
            return null;
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        StringBuilder fileData = new StringBuilder();
        while (s.hasNext()) {
            fileData.append(s.nextLine()).append(System.lineSeparator());
        }
        s.close();
        return fileData.toString();
    }

    /**
     * Stores all the data in the taskList into the saveFile at the filepath member
     * @param taskList The taskList that is to be stored
     * @throws IOException Due to failure of file writer
     */
    public void saveFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        String fileData = convertToSaveFormat(taskList);
        fw.write(fileData);
        fw.close();
    }


    private String convertToSaveFormat(TaskList taskList) {
        StringBuilder toWrite = new StringBuilder();
        for (Task task : taskList.getList()) {
            toWrite.append(task.convertToSaveFormat()).append(System.lineSeparator());
        }
        return toWrite.toString();
    }
}
