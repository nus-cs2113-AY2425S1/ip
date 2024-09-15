import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TaskTrackerUI {
    public ArrayList<Task> tasks;
    public TaskTrackerUI() {
        this.tasks = new ArrayList<>();

        try{
            load();
        } catch (CuboydException e) {
            System.out.println(e.getMessage());
        }
    }
    // Helpers /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addTaskWithUI(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
    public int parseValidTaskIndex(HashMap<String, String> argumentsList, ArrayList<Task> tasks)
    throws CuboydException{
        String commandFormatPlea = " Please run the command again with `<command> <task index>`!";
        int index;
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            System.out.println();
            throw new CuboydException("No Task Index was given!" + commandFormatPlea);
        }
        try {
            index = Integer.parseInt(argumentsList.get(CommandParser.ARGUMENT_MAIN)) - 1;
        } catch (NumberFormatException e){
            throw new CuboydException("Task Index given not a valid number!" + commandFormatPlea);
        }
        if (index < 0 || index >= this.tasks.size()){
            throw new CuboydException("Task Index out of range! There are only " + String.valueOf(this.tasks.size()) +
                    " tasks." + commandFormatPlea);
        }
        return index;
    }
    // Menu Options - List /////////////////////////////////////////////////////////////////////////////////////////////
    public void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int currentItemIndex = 0; currentItemIndex < this.tasks.size(); currentItemIndex++){
            System.out.println(String.valueOf(currentItemIndex+1) + "." + this.tasks.get(currentItemIndex));
        }
    }
    // Menu Options - Add //////////////////////////////////////////////////////////////////////////////////////////////
    public void addTodo(HashMap<String,String> argumentsList) throws CuboydException {
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            String commandFormatPlea = " Please run the command again with `todo <description>`!";
            System.out.println("No description was given!" + commandFormatPlea);
            return;
        }
        addTaskWithUI(new ToDo(argumentsList.get("main")));
        save();
    }
    public void addDeadline(HashMap<String,String> argumentsList) throws CuboydException {
        String commandFormatPlea = " Please run the command again with `deadline <description> /by <by date>`!";
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            throw new CuboydException("No description was given!" + commandFormatPlea);
        }
        if (argumentsList.get("/by") == null){
            throw new CuboydException("/by not given!" + commandFormatPlea);
        }
        addTaskWithUI(new Deadline(argumentsList.get("main"), argumentsList.get("/by")));
        save();
    }
    public void addEvent(HashMap<String,String> argumentsList) throws CuboydException {
        String commandFormatPlea = " Please run the command again with " +
                "`event <description> /from <from date> /to <to date>`!";
        if (argumentsList.get(CommandParser.ARGUMENT_MAIN) == null){
            throw new CuboydException("No description was given!" + commandFormatPlea);
        }
        if (argumentsList.get("/from") == null){
            throw new CuboydException("/from not given!" + commandFormatPlea);
        }
        if (argumentsList.get("/to") == null){
            throw new CuboydException("/to not given!" + commandFormatPlea);
        }
        addTaskWithUI(new Event(
                argumentsList.get("main"),
                argumentsList.get("/from"),
                argumentsList.get("/to")
        ));
        save();
    }
    // Menu Options - Mark /////////////////////////////////////////////////////////////////////////////////////////////
    public void markTask(HashMap<String,String> argumentsList) throws CuboydException {
        int index = parseValidTaskIndex(argumentsList, tasks);
        Task currentTask = tasks.get(index);
        currentTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currentTask.toString());
        save();
    }
    public void unmarkTask(HashMap<String,String> argumentsList) throws CuboydException {
        int index = parseValidTaskIndex(argumentsList, tasks);
        Task currentTask = tasks.get(index);
        currentTask.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currentTask.toString());
        save();
    }
    // Save & Load /////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String SAVE_FILE_PATH = "./savedata.txt";
    public void save() throws CuboydException {
        FileWriter fw;
        try {
            fw = new FileWriter(SAVE_FILE_PATH);
            for (int i = 0; i < this.tasks.size(); i++) {
                if (this.tasks.get(i) instanceof ToDo todo) {
                    fw.write(String.format(
                            "T|%s|%s\n",
                            todo.getStatusIcon(),
                            Base64.getEncoder().encodeToString(todo.getDescription().getBytes(StandardCharsets.UTF_8))
                    ));
                } else if (this.tasks.get(i) instanceof Deadline deadline) {
                    fw.write(String.format(
                            "D|%s|%s|%s\n",
                            deadline.getStatusIcon(),
                            Base64.getEncoder().encodeToString(
                                    deadline.getDescription().getBytes(StandardCharsets.UTF_8)),
                            Base64.getEncoder().encodeToString(deadline.by.getBytes(StandardCharsets.UTF_8))
                    ));
                } else if (this.tasks.get(i) instanceof Event event) {
                    fw.write(String.format(
                            "E|%s|%s|%s|%s\n",
                            event.getStatusIcon(),
                            Base64.getEncoder().encodeToString(event.getDescription().getBytes(StandardCharsets.UTF_8)),
                            Base64.getEncoder().encodeToString(event.from.getBytes(StandardCharsets.UTF_8)),
                            Base64.getEncoder().encodeToString(event.to.getBytes(StandardCharsets.UTF_8))
                    ));
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new CuboydException(String.format("Having issues saving to %s! Check your file permissions!\n",
                    SAVE_FILE_PATH));
        }
        System.out.println("Saved!");
    }
    private void loadFromScanner(Scanner s) throws Exception {
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] list = line.split("\\|");
            Task currentTask = null;
            switch (list[0]){
            case "T":
                currentTask = new ToDo(
                        new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8)
                );
                break;
            case "D":
                currentTask = new Deadline(
                        new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8),
                        new String(Base64.getDecoder().decode(list[3]), StandardCharsets.UTF_8)
                );
                break;
            case "E":
                currentTask = new Event(
                        new String(Base64.getDecoder().decode(list[2]), StandardCharsets.UTF_8),
                        new String(Base64.getDecoder().decode(list[3]), StandardCharsets.UTF_8),
                        new String(Base64.getDecoder().decode(list[4]), StandardCharsets.UTF_8)
                );
                break;
            }
            if (currentTask == null){
                throw new CuboydException("Invalid Task!");
            }
            if (Objects.equals(list[1], "X")) {
                currentTask.markAsDone();
            }
            this.tasks.add(currentTask);
        }
    }
    public void load() throws CuboydException {
        System.out.printf("Loading savefile from %s\n", SAVE_FILE_PATH);
        File f = new File(SAVE_FILE_PATH);
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new CuboydException(String.format(
                    "No save file detected at %s! If there is, check your file permissions!", SAVE_FILE_PATH));
        }

        tasks.clear();
        try {
            loadFromScanner(s);
        } catch (Exception e) {
            throw new CuboydException(String.format("Error when reading from %s! Savefile might be corrupted!",
                    SAVE_FILE_PATH));
        }
        System.out.println("Loaded!");
    }
}