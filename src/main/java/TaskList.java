import java.util.Arrays;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;
    private Parser parser;
    private final String LINE_SEPERATOR = "____________________________________________________________";

    public void displayList() {
        int count = 1;
        System.out.println(LINE_SEPERATOR);
        for (Task task: this.list) {
            System.out.println(count + "." + task);
            count++;
        }
        System.out.println(LINE_SEPERATOR);
    }

    public void markTask(int position, boolean isFromSaveFile) {
        Task task = this.list.get(position - 1);
        task.setDone();
        this.list.set(position - 1, task);
        if (!isFromSaveFile) {
            displayList();
        }
    }

    public void deleteTask(int position) {
        Task task = this.list.get(position - 1);
        this.list.remove(position - 1);
        this.ui.printBlock("Got it. The task below was removed:\n" + task +
                "\nNow you have " + this.list.size() + " left");
    }

    public void addToDo(String description, boolean isFromSaveFile) {
        ToDo toDo = new ToDo(description);
        this.list.add(toDo);
        if (!isFromSaveFile) {
            this.ui.printBlock(String.format("Got it. Task added\n %s", toDo));
        }
    }

    public void addDeadline(String description, boolean isFromSaveFile) throws InvalidDeadlineException{
        String[] descriptionAndDeadline = description.split("/by");
        if (descriptionAndDeadline.length != 2) {
            throw new InvalidDeadlineException();
        }
        String descriptionText = descriptionAndDeadline[0].trim();
        String by = descriptionAndDeadline[1].trim();

        Deadline deadline = new Deadline(descriptionText, by);
        this.list.add(deadline);

        if (!isFromSaveFile) {
            this.ui.printBlock(String.format("Got it. Task added\n %s", deadline));
        }
    }

    public void addEvent(String description, boolean isFromSaveFile) {
        String[] descriptionAndEventTimeline = description.split("/from");
        String descriptionText = descriptionAndEventTimeline[0].trim();
        String[] eventTimeline = descriptionAndEventTimeline[1].split("/to");
        String from = eventTimeline[0].trim();
        String to = eventTimeline[1].trim();
        
        Event event = new Event(descriptionText, from, to);
        this.list.add(event);

        if (!isFromSaveFile) {
            this.ui.printBlock(String.format("Got it. Task added\n %s", event));
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public TaskList(Storage storage) {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
        this.parser = new Parser(storage, this);
        ArrayList<String> commandArray = storage.getCommandArray();
        commandArray.forEach((command) -> {
            this.parser.parse(command, true);
            try {
                String[] lineArr = command.trim().split(" ");
                String description;
                switch (lineArr[0].toLowerCase()) {
                case "mark":
                    int position = Integer.parseInt(lineArr[1]);
                    markTask(position, true);
                    break;
                case "todo":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addToDo(description, true);
                    break;
                case "deadline":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addDeadline(description, true);
                    break;
                case "event":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addEvent(description, true);
                    break;
                default:
                    this.ui.printBlock("An error occured while loading the save file");
                    break;
                }
            } catch (InvalidDeadlineException error) {
                System.out.println(error);
            }
        });
    }

    public TaskList() {
    }
}
