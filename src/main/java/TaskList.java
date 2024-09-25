import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    private static int taskCount;
    private static UI uiInstance;
    private Parser parserInstance;

    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
        uiInstance = null;
    }

    public static void add(Task newTask) {
        taskList.add(newTask);
        taskCount++;
    }

    public void setUI(UI ui) {
       uiInstance = ui;
    }

    public void setParser(Parser parser) {
        parserInstance = parser;
    }

    //add toDo task for level-4
    public void addToDo(String[] inputComponent) throws DukeException {
        String description = "";

        for (int i = 1; i < inputComponent.length; i++) {
            description += inputComponent[i];
            description += " ";
        }

        try {
            parserInstance.checkTodoInput(description);
            taskList.add(new ToDo(description.trim()));
            taskCount++;
            uiInstance.addTaskMessage(taskCount);
        } catch (DukeException e) {
            e.displayMessage();
        }

    }


    //add deadline task for level-4
    public void addDeadline(String[] inputComponent) throws DukeException {
        String description = "";
        String by = "";
        int state = 0;//transition from "description" to "by" string


        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/by")) {
                state += 1;
            } else {
                if (state == 1) {
                    by += inputComponent[i];
                    by += " ";
                } else {
                    description += inputComponent[i];
                    description += " ";
                }
            }
        }

        try {
            parserInstance.checkDeadlineInput(description, state);
            taskList.add(new Deadline(description.trim(), by.trim()));
            taskCount++;
            uiInstance.addTaskMessage(taskCount);
        } catch (DukeException e) {
            e.displayMessage();
        }

    }


    //add event task for level-4
    public void addEvent(String[] inputComponent) {
        String description = "";
        String from = "";
        String to = "";
        int state = 0;//transition from "description" to "from" to "to" string


        for (int i = 1; i < inputComponent.length; i++) {
            if (inputComponent[i].equals("/from")) {
                state = 1;
            } else if (inputComponent[i].equals("/to")) {
                state = 2;
            } else {
                if (state == 1) {
                    from += inputComponent[i];
                    from += " ";
                } else if (state == 2) {
                    to += inputComponent[i];
                    to += " ";
                } else {
                    description += inputComponent[i];
                    description += " ";
                }
            }
        }

        try {
            parserInstance.checkEventInput(description, state);
            taskList.add(new Event(description.trim(), from.trim(), to.trim()));
            taskCount++;
            uiInstance.addTaskMessage(taskCount);
        } catch (DukeException e) {
            e.displayMessage();
        }
    }





    //delete method for level-6
    public void deleteTask(int index) throws DukeException {
        if (index-1 < 0 || index-1 > taskCount) {
            uiInstance.Warning("You have input an invalid index");
        }

        String deleted = taskList.get(index-1).toString();

        taskList.remove(index-1);
        taskCount--;


        uiInstance.deleteTaskMessage(taskCount, deleted);
    }

    public void list() {
        if(taskCount == 0) {
            uiInstance.displayMessage("No tasks found");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                uiInstance.displayTask(i);
            }
        }
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void markAsDone(int index) throws DukeException {
        if (index < 0 || index > taskCount) {
            uiInstance.Warning("You have input an invalid index");
        }
        taskList.get(index-1).markAsDone();
        uiInstance.markTaskMessage(index);
    }
    public void markAsNotDone(int index) throws DukeException {
        if (index < 0 || index > taskCount) {
            uiInstance.Warning("You have input an invalid index");
        }
        taskList.get(index-1).markAsNotDone();
        uiInstance.unmarkTaskMessage(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void findTaskKeyword(String[] inputComponent) throws DukeException {
        String keyword = "";
        for (int i = 1; i < inputComponent.length; i++) {
            keyword += inputComponent[i];
            if(i != inputComponent.length-1) {
                keyword += " ";
            }
        }
        int count = 0;
        uiInstance.displayMessageForFind();
        for(int i = 0; i < taskCount; i++) {
            if(taskList.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                uiInstance.displayTaskForFind(i,count);
            }
        }
        if(count == 0) {
            uiInstance.displayMessage("No tasks found matching the keyword");
        }
    }


}
