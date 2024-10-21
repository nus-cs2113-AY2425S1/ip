public class Parser {
    private static TaskList taskList;

    /**
     * Parses user input and execute corresponding commands
     */
    public Parser(Object tasks) {
        taskList = (TaskList) tasks;
    }

    /**
     * Process and execute user commands
     *
     * @param line user input
     */
    public static void handle(String line) {
        if (line.equals("list")) {
            Ui.printList();
        } else if (line.length() > 5 && line.startsWith("mark ")) {
            taskList.mark(line);
            Storage.saveFile();
        } else if (line.length() > 7 && line.startsWith("unmark ")) {
            taskList.unmark(line);
            Storage.saveFile();
        } else if (line.startsWith("todo")) {
            taskList.createTodo(line);
            Storage.saveFile();
        } else if (line.length() > 9 && line.startsWith("deadline ")) {
            taskList.createDeadline(line);
            Storage.saveFile();
        } else if (line.length() > 6 && line.startsWith("event ")) {
            taskList.createEvent(line);
            Storage.saveFile();
        } else if (line.length() > 7 && line.startsWith("delete ")) {
            taskList.deleteTask(line);
            Storage.saveFile();
        } else if (line.equals("bye")) {
            Anke.isExit = true;
        } else if (line.startsWith("find ")) {
            taskList.find(line);
        } else {
            Ui.handleWrongFormat();
        }
    }


}
