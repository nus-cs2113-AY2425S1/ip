package melchizedek.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of the Task class,
     * mainly used when loading tasks from save file only.
     *
     * @param description Task description
     * @param isDone Truth value of whether task has been marked as done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Method to get the value of whether the task has been marked as done.
     *
     * @return isDone
     */
    public boolean getMarkAsDone() {
        return isDone;
    }

    /**
     * Method to mark the task as done.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Method to unmark the task as done.
     */
    public void unmarkTaskAsDone() {
        isDone = false;
    }

    private String getStatusMark(){
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Method to check if the task contains the keyword.
     * Case of keyword is ignored.
     *
     * @param keyword The keyword to check for
     * @return The truth value of whether the task contains the keyword
     */
    public boolean containsKeyword(String keyword) {
        String keywordLowerCase = keyword.toLowerCase();
        String descriptionLowerCase = description.toLowerCase();
        return descriptionLowerCase.contains(keywordLowerCase);
    }

    /**
     * Method to convert task to the save file format.
     *
     * @return Task in File format
     */
    public String taskToFile() {
        if (isDone) {
            return "1 | " + description;
        }
        return "0 | " + description;
    }

    /**
     * Override method to format task to printable String
     *
     * @return Task in formatted String
     */
    @Override
    public String toString() {
        return "[" + getStatusMark() + "] " + description;
    }
}
