import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static int count = 0;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public static void load() throws AnkeException {
        System.out.println("Loading data from file");
        Scanner s;
        try {
            File f = new File(filePath);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new AnkeException(e.getMessage());
        }
        while (s.hasNext()) {
            String line = s.nextLine();
//            System.out.println(line);
            try {
                String data = "";
                switch (line.charAt(1)) {
                case 'T':
                    data = "todo " + line.substring(7);
//                    System.out.println(data);
                    TaskList.createTodo(data);
                    break;
                case 'D':
                    data = "deadline " + line.substring(7);
                    data = data.replace("(", "/");
                    data = data.replace(")", "");
                    data = data.replace(":", "");
//                    System.out.println(data);
                    TaskList.createDeadline(data);
                    break;
                case 'E':
                    data = "event " + line.substring(7);
                    data = data.replace("(", "/");
                    data = data.replace(")", "");
                    data = data.replace("to", "/to");
                    data = data.replace(":", "");
//                    System.out.println(data);
                    TaskList.createEvent(data);
                    break;
                }
                if (line.charAt(4) == 'X') {
                    TaskList.markTask(count);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        System.out.println("Finish loading data\n");
    }

    public static int getCount(){
        return count;
    }

    public static void incrementCount(){
        count++;
    }

    public static void decrementCount(){
        count--;
    }

    public static void saveFile() {
        try {
            FileWriter fw = new FileWriter("./Anke.txt");
            for (int i = 0; i < count; ++i) {
                fw.write(TaskList.getTask(i)+ System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during saving changes: " + e.getMessage());
        }
    }
}
