import static java.lang.Integer.parseInt;

public class InputHandler {
    public static int inputHandler(String userInput) {
        switch (userInput) {

        // Case: Terminate program
        case "bye":
            return -1;

        // Case: See list
        case "list":
            if (Task.tasksCount == 0) {
                UI.printContent("You don't have any tasks!");
                return 1;
            }

            System.out.println(UI.DIVIDER_LINE);
            for (int i = 1; i <= Task.tasksCount; i++) {
                System.out.println(i + ". " + Aerus.tasks[i - 1].toString());
            }
            System.out.println(UI.DIVIDER_LINE);
            return 1;


        default:
            // Case: Mark & Unmark
            String[] userInputSplit = userInput.split(" ");

            // Test if the input is formatted like a mark/unmark command
            if (userInputSplit.length == 2 && userInputSplit[1].matches("\\d+(\\.\\d+)?") &&
                    parseInt(userInputSplit[1]) <= Task.tasksCount) {
                int taskNumber = parseInt(userInputSplit[1]) - 1;
                if (userInputSplit[0].equals("mark")) {
                    Aerus.tasks[taskNumber].isDone = true;
                    UI.printContent("Nice! You have done this task:\n\t" + Aerus.tasks[taskNumber].toString());
                    return 1;
                }
                if (userInputSplit[0].equals("unmark")) {
                    Aerus.tasks[taskNumber].isDone = false;
                    UI.printContent("I have unmarked this task:\n\t" + Aerus.tasks[taskNumber].toString());
                    return 1;
                }
            }

            // Case: Add task
            if (!userInput.isEmpty()) {
                if (userInputSplit[0].equals("todo")) {
                    Aerus.tasks[Task.tasksCount] = new ToDo(userInput.substring(5));
                    UI.printContent("Added ToDo: " + userInput.substring(5));
                } else if (userInputSplit[0].equals("event")) {
                    Aerus.tasks[Task.tasksCount] = new Event(userInput.substring(6));
                    UI.printContent("Added Event: " + userInput.substring(6));
                } else if (userInputSplit[0].equals("deadline")) {
                    Aerus.tasks[Task.tasksCount] = new Deadline(userInput.substring(9));
                    UI.printContent("Added Deadline: " + userInput.substring(9));
                } else {
                    Aerus.tasks[Task.tasksCount] = new Task(userInput);
                    UI.printContent("Added Task: " + userInput);
                }
                return 1;
            }
            return 1;
        }
    }
}
