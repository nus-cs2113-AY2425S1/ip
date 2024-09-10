public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        description = "";
        isDone = false;
    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() throws DukeException{
        try{
            if (isDone) {
                throw new DukeException("This task is already marked as done");//throw exception if the task is marked
            }
            isDone = true;
            System.out.println(this.toString());
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    public void markAsNotDone() throws DukeException{
        try {
            if (!isDone) {
                throw new DukeException("This task is already marked as undone");//throw exception if the task is unmark
            }
            isDone = false;
            System.out.println(this.toString());
        } catch (DukeException e) {
            e.displayMessage();
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.getDescription();
    }
}
