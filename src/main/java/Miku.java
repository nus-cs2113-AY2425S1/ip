import java.io.IOException;

public class Miku {

    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static final String storageFilePath = "tasks.txt";


    public static void init(String filePath){
       ui = new Ui();
       storage = new Storage(filePath);
       try {
           String fileData= storage.loadFile();
           taskList = new TaskList(fileData);
       } catch (Exception e) {
           taskList = new TaskList();
           System.out.println("Failed to load tasks.txt");
       }
    }

    public static void run(){
        ui.run(taskList);
        try {
            storage.saveFile(taskList);
        }
        catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public static void main(String[] args) {
        init(storageFilePath);
        run();
    }

}
