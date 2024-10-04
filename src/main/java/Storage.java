import Tasks.Task;

import java.io.*;

public class Storage {



    public void load() {
        try {
            FileInputStream fileReader = new FileInputStream(FILE_NAME);
            ObjectInputStream objectReader = new ObjectInputStream(fileReader);
            boolean fileHasData = true;
            while (fileHasData) {
                try {
                    Object taskToAdd = objectReader.readObject();
                    taskList.add((Task) taskToAdd);
                } catch (EOFException e) {
                    fileHasData = false;
                }
            }
            objectReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Read file does not exist, will be created!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }
}
