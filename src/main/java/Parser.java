public class Parser {

    private TaskList taskListInstance;
    private UI uiInstance;

    public Parser(TaskList taskList, UI uiInstance) {
        this.taskListInstance = taskList;
        this.uiInstance = uiInstance;
    }

    //exception handler for todo level-5
    public void checkTodoInput(String input) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            uiInstance.Warning("Description for a todo cannot be empty");
        }
    }

    //exception handler for deadline level-5
    public void checkDeadlineInput(String input, int state) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            uiInstance.Warning("Description for a deadline cannot be empty");
        } else if (state == 0) {
            uiInstance.Warning("There is no date for a deadline");
        } else if (state > 1) {
           uiInstance.Warning("Too many /by statement");
        }
    }


    //exception handler for event level-5
    public void checkEventInput(String input, int state) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Throw a custom exception for empty input
            uiInstance.Warning("Description for an event cannot be empty");
        } else if (state == 0) {
            uiInstance.Warning("There is no start and end for this event");
        } else if (state == 1) {
            uiInstance.Warning("There is no end for this event");
        }
    }


    //exception handler for mark and unmark level-5
    public void checkMarkUnmarkInput(int index, int taskCount) throws DukeException {
        if (index < 0 || index > taskCount) {
            uiInstance.Warning("You have input an invalid index");
        }
    }

    //exception handler for general input level-5
    public void checkGeneralInput() throws DukeException {
        uiInstance.Warning("Sorry I cannot understand that");
    }

    public int processCommand(String input) throws DukeException {
        String[] inputComponent = input.split(" ");

        //switch case based on the first word of input line
        try {
            switch (inputComponent[0]) {
                case "bye":
                    uiInstance.displayMessage("Bye. Hope to see you again soon!");
                    return -1;
                case "list":
                    taskListInstance.list();
                    break;
                case "mark":
                    try {
                        TaskList.markAsDone(Integer.parseInt(inputComponent[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        uiInstance.displayMessage("Warning: You haven't input any number");
                    }
                    break;
                case "unmark":
                    try {
                        taskListInstance.markAsNotDone(Integer.parseInt(inputComponent[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        uiInstance.displayMessage("Warning: You haven't input any number");
                    }
                    break;
                case "todo":
                    taskListInstance.addToDo(inputComponent);
                    break;
                case "deadline":
                    taskListInstance.addDeadline(inputComponent);
                    break;
                case "event":
                    taskListInstance.addEvent(inputComponent);
                    break;
                case "delete":
                    taskListInstance.deleteTask(Integer.parseInt(inputComponent[1]));
                    break;
                case "find":
                    taskListInstance.findTaskKeyword(inputComponent);
                    break;
                default:
                        checkGeneralInput(); //check for invalid command

            }
        } catch (DukeException e) {
            e.displayMessage();
        }
        return 0;
    }


}
