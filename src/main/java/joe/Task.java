package joe;

public class Task {
    private static final String[] TASK_TYPES = {"todo", "deadline", "event"};
    private String itemDescription;
    private boolean isToDo;

    public Task(String itemDescription) {
        this.itemDescription = itemDescription;
        this.isToDo = true;
    }

    public Task(String itemDescription, boolean isToDo) {
        this.itemDescription = itemDescription;
        this.isToDo = isToDo;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public boolean isToDo() {
        return this.isToDo;
    }

    public void setToDo(boolean isTodo) {
        this.isToDo = isTodo;
    }

    public static String extractDescription(String input, String itemType) {
        int indexOfDescription = input.indexOf(itemType) + itemType.length();
        return input.substring(indexOfDescription, input.length()).strip();
    }

    @Override
    public String toString() {
        String checkBox;
        if (this.isToDo()) {
            checkBox = " [" + "not done" + "]";
            } else {
            checkBox = " [" + "done" + "]";
        }

        return "[T]" + checkBox + " " +
                this.getItemDescription();
    }


}

