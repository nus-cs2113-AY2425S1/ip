public class Parser {
    public Parser() {

    }

    public static void handle(String line) {
        if (line.equals("list")) {
            Ui.printList();
        } else if (line.length() > 5 && line.startsWith("mark ")) {
            TaskList.mark(line);
            Storage.saveFile();
        } else if (line.length() > 7 && line.startsWith("unmark ")) {
            TaskList.unmark(line);
            Storage.saveFile();
        } else if (line.startsWith("todo")) {
            TaskList.createTodo(line);
            Storage.saveFile();
        } else if (line.length() > 9 && line.startsWith("deadline ")) {
            TaskList.createDeadline(line);
            Storage.saveFile();
        } else if (line.length() > 6 && line.startsWith("event ")) {
            TaskList.createEvent(line);
            Storage.saveFile();
        } else if (line.length() > 7 && line.startsWith("delete ")) {
            TaskList.deleteTask(line);
            Storage.saveFile();
        } else if (line.equals("bye")) {
            Anke.isExit = true;
        } else if (line.startsWith("find ")) {
            TaskList.find(line);
        } else {
            Ui.handleWrongFormat();
        }
    }


}
