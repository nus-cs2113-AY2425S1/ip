package tommi;

public class InputHandler {

    public static void processInputCases(String input) {
        try {
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
                if (words.length > 1 && !words[1].isEmpty()) {
                    TaskList.addTask(new ToDo(false, input.substring(5)));
                } else {
                    throw new IllegalArgumentException(
                            "____________________________________________________________\n" +
                            "Please provide what should be in the todo.\n" +
                            "____________________________________________________________"
                    );
                }
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
                throw new IllegalArgumentException(
                        "____________________________________________________________\n" +
                        "Please use a todo, event or deadline. List all tasks with list or exit with bye!\n" +
                        "____________________________________________________________"
                );
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
