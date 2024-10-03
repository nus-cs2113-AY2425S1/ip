import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String loadFile() throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        StringBuilder fileData = new StringBuilder();
        while (s.hasNext()) {
            fileData.append(s.nextLine()).append(System.lineSeparator());
        }
        s.close();
        return fileData.toString();
    }

    public void saveFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        String fileData = convertToSaveFormat(taskList);
        fw.write(fileData);
        fw.close();
    }


    public String convertToSaveFormat(TaskList taskList) throws IOException {
        StringBuilder toWrite = new StringBuilder();
        for (Task task : taskList.getList()) {
            toWrite.append(task.convertToSaveFormat()).append(System.lineSeparator());
        }
        return toWrite.toString();
    }
}
