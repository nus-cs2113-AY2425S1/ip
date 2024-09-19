public class Task {

    static String typeIcon = "[X]";

    String taskName;
    boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(boolean newStatus) {
        this.isDone = newStatus;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    /* Dummy method to allow for "by" data in Deadline-type tasks to be
    accessed.
    */
    public String getBy() {
        System.out.println("AN ERROR OCCURRED: THE 'GETBY' METHOD WAS " +
                        "CALLED ON A 'TASK'");
        System.exit(1);
        return null;
    }


    /* Dummy method to allow for "From" data in Event-type tasks to be
accessed.
*/
    public String getEventStart() {
        System.out.println("AN ERROR OCCURRED: THE 'GETEVENTSTART' METHOD WAS " +
                "CALLED ON A 'TASK'");
        System.exit(1);
        return null;
    }

    /* Dummy method to allow for "To" data in Event-type tasks to be
accessed.
*/
    public String getEventEnd() {
        System.out.println("AN ERROR OCCURRED: THE 'GETEVENTEND' METHOD WAS " +
                "CALLED ON A 'TASK'");
        System.exit(1);
        return null;
    }
}
