import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(String fileData){
        list = new ArrayList<>();
        parseFileData(fileData);
    }

    public void parseFileData(String fileData){
        String[] lines = fileData.split(System.lineSeparator());
        for (String line : lines) {
            try {
                parseLine(line);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Save file has been tampered with or corrupted");
            }
        }
    }

    public void parseLine(String line) throws IndexOutOfBoundsException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Todo todoTask = new Todo(parts[2]);
            if (parts[1].trim().equals("1")) {
                todoTask.markAsDone();
            }
            list.add(todoTask);
            break;
        case "D":
            Deadline deadlineTask = new Deadline(parts[2], parts[3]);
            if (parts[1].trim().equals("1")) {
                deadlineTask.markAsDone();
            }
            list.add(deadlineTask);
            break;
        case "E":
            Event eventTask = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].trim().equals("1")) {
                eventTask.markAsDone();
            }
            list.add(eventTask);
            break;
        }
    }

    public void addTask(Task newTask) {
        System.out.println("I've added this to your list: ");
        newTask.printTask();
        list.add(newTask);
    }

    public void printTaskList() {
        if (list.isEmpty()){
           System.out.println("You currently have no tasks");
           return;
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%d. ", i);
            list.get(i - 1).printTask();
        }
    }

    public void attemptToMarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            list.get(index-1).printTask();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToUnmarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsNotDone();
            System.out.println("Nice! I've marked this task as not done:");
            list.get(index-1).printTask();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToDelete(String listIndex){
        try {
            int index = Integer.parseInt(listIndex);
            Task temp = list.get(index-1);
            list.remove(index-1);
            System.out.println("Nice! I've deleted this task for you:");
            temp.printTask();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public ArrayList<Task> getList() {
        return  list;
    }
}
