public class List {
    public static final String INVALID_EVENT_INPUT_MESSAGE = "event <event name> /from <start date/time> /end <end date/time>";
    public static final String INVALID_DEADLINE_INPUT_MESSAGE = "deadline <deadline name> /by <deadline>";
    public static final String INVALID_TODO_INPUT_MESSAGE = "todo <task name>";
    private int numItems;
    private Task[] itemList = new Task[100];

    public List() {
        numItems = 0;
    }

    public int getNumItems() {
        return numItems;
    }

    public void addItem(String line) {
        if (line.length() >= 7 && line.substring(0, 5).equals("event")) {
            if (line.contains("/from") && line.contains("/to")) {
                addEvent(line);
            } else {
                System.out.println("\tInvalid command format: " + System.lineSeparator() + "\t\t" +
                        INVALID_EVENT_INPUT_MESSAGE);
            }
        } else if (line.length() >= 10 && line.substring(0, 8).equals("deadline")) {
            if (line.contains("/by")) {
                addDeadline(line);
            } else {
                System.out.println("\tInvalid command format: " + System.lineSeparator() + "\t\t" +
                        INVALID_DEADLINE_INPUT_MESSAGE);
            }
        } else if (line.length() >= 6 && line.substring(0, 4).equals("todo")){
            addTodo(line);
        } else {
            System.out.println("\tInvalid command format: " + System.lineSeparator() + "\t\t" + INVALID_TODO_INPUT_MESSAGE
                    + System.lineSeparator() + "\t\t" + INVALID_DEADLINE_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                    + INVALID_EVENT_INPUT_MESSAGE);
        }
    }

    private void addEvent(String line) {
        String eventStartDate = extractEventStartDate(line);
        String eventEndDate = extractEventEndDate(line);
        String eventDescription = extractEventDescription(line);
        itemList[numItems] = new Event(eventDescription, eventStartDate, eventEndDate);
        outputAddedMessage(itemList[numItems]);
        numItems += 1;
    }

    private void addTodo(String line) {
        String todoDescription = extractTodoDescription(line);
        itemList[numItems] = new Todo(todoDescription);
        outputAddedMessage(itemList[numItems]);
        numItems += 1;
    }

    private void addDeadline(String line) {
        String deadlineDate = extractDeadlineDate(line);
        String deadlineDescription = extractDeadlineDescription(line);
        itemList[numItems] = new Deadline(deadlineDescription, deadlineDate);
        outputAddedMessage(itemList[numItems]);
        numItems += 1;
    }

    private void outputAddedMessage(Task task) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (numItems+1) + " tasks in the list.");
    }

    private String extractTodoDescription(String line) {
        String todoDescription;
        todoDescription = line.trim().replace("todo ", "");

        return todoDescription;
    }

    private String extractDeadlineDescription(String line) {
        String deadlineDescription;
        final int indexOfDeadlinePrefix = line.indexOf("/by");
        deadlineDescription = line.substring(0, indexOfDeadlinePrefix).trim().replace("deadline ", "");

        return deadlineDescription;
    }

    private String extractDeadlineDate(String line) {
        String deadlineDate;
        final int indexOfDeadlinePrefix = line.indexOf("/by");
        deadlineDate = line.substring(indexOfDeadlinePrefix).trim().replace("/by ", "");

        return deadlineDate;
    }

    private String extractEventDescription(String line) {
        String eventDescription;
        final int indexOfStartDatePrefix = line.indexOf("/from");
        final int indexOfEndDatePrefix = line.indexOf("/to");
        if (indexOfEndDatePrefix > indexOfStartDatePrefix) {
            eventDescription = line.substring(0, indexOfStartDatePrefix).trim().replace("event ", "");
        } else {
            eventDescription = line.substring(0, indexOfEndDatePrefix).trim().replace("event ", "");
        }

        return eventDescription;
    }

    private String extractEventEndDate(String line) {
        String eventEndDate;
        final int indexOfStartDatePrefix = line.indexOf("/from");
        final int indexOfEndDatePrefix = line.indexOf("/to");
        if (indexOfEndDatePrefix > indexOfStartDatePrefix) {
            eventEndDate = line.substring(indexOfEndDatePrefix).trim().replace("/to ", "");
        } else {
            eventEndDate = line.substring(indexOfEndDatePrefix, indexOfStartDatePrefix).trim().replace("/to ", "");
        }

        return eventEndDate;
    }

    private String extractEventStartDate(String line) {
        String eventStartDate;
        final int indexOfStartDatePrefix = line.indexOf("/from");
        final int indexOfEndDatePrefix = line.indexOf("/to");
        if (indexOfStartDatePrefix > indexOfEndDatePrefix) {
            eventStartDate = line.substring(indexOfStartDatePrefix).trim().replace("/from ", "");
        } else {
            eventStartDate = line.substring(indexOfStartDatePrefix, indexOfEndDatePrefix).trim().replace("/from ", "");
        }

        return eventStartDate;
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numItems; i++) {
            //System.out.println("\t" + (i + 1) + ".[" + itemList[i].getTaskTypeIcon() + "][" + itemList[i].getDoneStatusIcon() + "] " + itemList[i].description);
            System.out.println("\t" + (i + 1) + "." + itemList[i]);
        }
    }

    public void markItem(String line) {
        int itemNum = Integer.parseInt(line.substring(5));
        if (itemNum > this.getNumItems() || itemNum <= 0) {
            System.out.println("\tInput item number out of range.");
        } else {
            this.markListItemAsDone(itemNum);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t[" + this.itemGetDoneStatusIcon(itemNum) + "] " + this.getItemDescription(itemNum));
        }
    }

    public void unmarkItem(String line) {
        int itemNum = Integer.parseInt(line.substring(7));
        if (itemNum > this.getNumItems() || itemNum <= 0) {
            System.out.println("\tInput item number out of range.");
        } else {
            this.markListItemAsUnDone(itemNum);
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t[" + this.itemGetDoneStatusIcon(itemNum) + "] " + this.getItemDescription(itemNum));
        }
    }

    public String getItemDescription(int itemNum) {
        return itemList[itemNum - 1].description;
    }

    public void markListItemAsDone(int itemNum) {
        itemList[itemNum - 1].markAsDone();
    }

    public void markListItemAsUnDone(int itemNum) {
        itemList[itemNum - 1].markAsUnDone();
    }

    public String itemGetDoneStatusIcon(int itemNum) {
        return itemList[itemNum - 1].getDoneStatusIcon();
    }
}
