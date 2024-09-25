public class Parser {
    public static boolean parse(String userInput) {
        UI ui = new UI();
        String[] parts = userInput.split(" ");
        String command = parts[0];
        String description;

        switch (command) {
            case "quag":
                ui.showExit();
                return false;

            case "list":
                TaskList.displayList();
                break;

            case "mark":
                if (parts.length >= 2) {
                    try {
                        int taskIndex = Integer.parseInt(parts[1]);
                        TaskList.markAsDone(taskIndex);
                    } catch (NumberFormatException e) {
                        ui.notNumberEntered();
                    }
                } else {
                    ui.noNumberEntered();
                }
                break;

            case "unmark":
                if (parts.length >= 2) {
                    try {
                        int taskIndex = Integer.parseInt(parts[1]);
                        TaskList.markAsNotDone(taskIndex);
                    } catch (NumberFormatException e) {
                        ui.notNumberEntered();
                    }
                } else {
                    ui.noNumberEntered();
                }
                break;

            case "todo":
                //description is from parts[1] to end
                description = userInput.substring("todo".length()).trim();
                TaskList.addTodoToList(description);
                break;

            case "deadline":
                //description is from parts[1] until / is detected
                //deadline is after /

                // int to denote length and position of "/by" separator
                int byLength = 4;
                int byIndex = userInput.indexOf("/by");

                //find position of /by separator
                if (byIndex != -1) {
                    //extract the description and deadline
                    description = userInput.substring("deadline".length(), byIndex).trim();
                    // Skip over "/by "
                    String by = userInput.substring(byIndex + byLength).trim();
                    TaskList.addDeadlineToList(description, by);
                } else {
                    ui.invalidFormat();
                }
                break;

            case "event":
                //description is from parts[1] until first /
                //from is in between first and second /
                //to is after second /

                // int to denote length and position of "/from" and "/to" separator
                int fromIndex = userInput.indexOf("/from");
                int fromLength = 6;
                int toIndex = userInput.indexOf("/to");
                int toLength = 4;

                if (fromIndex != -1 && toIndex != -1 && toIndex > fromIndex) {
                    // extract description, from and to
                    description = userInput.substring("event".length(), fromIndex).trim();
                    String from = userInput.substring(fromIndex + fromLength, toIndex).trim();
                    String to = userInput.substring(toIndex + toLength).trim();
                    TaskList.addEventToList(description, from, to);
                }  else {
                    ui.invalidFormat();
                }
                break;

            case "delete":
                if (parts.length >= 2) {
                    try {
                        int taskIndex = Integer.parseInt(parts[1]);
                        TaskList.deleteTask(taskIndex);
                    } catch (NumberFormatException e) {
                        ui.notNumberEntered();
                    }
                } else {
                    ui.noNumberEntered();
                }
                break;

            default:
                ui.showCommandList();
                break;
        }
        return true;
    }
}
