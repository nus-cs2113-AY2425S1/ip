import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Utilities {
    public static final char DONE_SYMBOL = 'X';
    public static final char NOT_DONE_SYMBOL = 'O';

    public static Hashtable<String, String> getCommandArgument(String line) {
        Hashtable<String, String> args = new Hashtable<>();
        String[] commandParams = (line.substring(line.indexOf(" ") + 1).split("(?=/)|(?<=/)"));
        boolean isArgument = false;
        for (String s : commandParams) {
            s = s.trim();
            if (s.equals("/")) {
                isArgument = true;
            } else if (isArgument) {
                args.put(s.substring(0, s.indexOf(' ')), s.substring(s.indexOf(' ')).trim());
                isArgument = false;
            }
        }
        return args;
    }

    public static boolean saveData(List<Task> taskList) {
        try {
            File file = new File("data/taskList.txt");
            file.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();

            for (Task task : taskList) {
                sb.append(task.isDone() ? DONE_SYMBOL : NOT_DONE_SYMBOL);
                if (task instanceof Deadline) {
                    sb.append('D');
                    sb.append(task.getTask());
                    sb.append(System.lineSeparator());
                    sb.append(((Deadline) task).getBy());
                    sb.append(System.lineSeparator());
                } else if (task instanceof Event) {
                    sb.append('E');
                    sb.append(task.getTask());
                    sb.append(System.lineSeparator());
                    sb.append(((Event) task).getFrom());
                    sb.append(System.lineSeparator());
                    sb.append(((Event) task).getTo());
                    sb.append(System.lineSeparator());
                } else if (task instanceof Todo) {
                    sb.append('T');
                    sb.append(task.getTask());
                    sb.append(System.lineSeparator());
                }
            }

            fw.write(sb.toString());
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Task> loadData() {
        List<Task> data = new ArrayList<>();
        try {
            File file = new File("data/taskList.txt");
            if (!file.exists()) {
                System.out.println("Welcome");
                return data;
            }
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.charAt(1) == 'T') {
                    Todo todo = new Todo(line.substring(2));
                    if (line.charAt(0) == DONE_SYMBOL) todo.mark();
                    data.add(todo);
                } else if (line.charAt(1) == 'D') {
                    Deadline deadline = new Deadline(line.substring(2), s.nextLine());
                    if (line.charAt(0) == DONE_SYMBOL) deadline.mark();
                    data.add(deadline);
                } else if (line.charAt(1) == 'E') {
                    Event event = new Event(line.substring(2), s.nextLine(), s.nextLine());
                    if (line.charAt(0) == DONE_SYMBOL) event.mark();
                    data.add(event);
                }
            }
            System.out.println("Data loaded");
            return data;
        } catch (Exception e) {
            System.out.println("Error loading data");
            return new ArrayList<>();
        }
    }
}
