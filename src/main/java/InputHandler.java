public class InputHandler {

    public static void processInputCases(String input) {
        if (input.equals("list")) {
            TaskList.listTasks();
            return;
        }

        String[] words = input.split(" ");
        int taskIndex;

        switch (words[0]) {
        case "mark":
            taskIndex = Integer.parseInt(words[1]) - 1;
            TaskList.markTask(taskIndex);
            break;
        case "unmark":
            taskIndex = Integer.parseInt(input.substring(7)) - 1;
            TaskList.unmarkTask(taskIndex);
            break;
        case "todo":
            TaskList.addTask(new ToDo(false, input.substring(5)));
            break;
        case "deadline":
            String[] deadlineParts = input.substring(9).split("/by", 2);
            TaskList.addTask(new Deadline(false, deadlineParts[0], deadlineParts[1]));
            break;
        case "event":
            String[] eventParts = input.split("/from", 2);

            // Split the remaining part by /to
            String[] timeParts = eventParts[1].split("/to", 2);

            TaskList.addTask(new Event(false, eventParts[0], timeParts[0], timeParts[1]));
            break;
        default:
            TaskList.addTask(new Task(false, input));
        }
    }
}
