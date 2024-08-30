public class Task {
  protected String description;
  protected boolean isDone;

  private static int numberOfTasks = 0;

  public Task(String description) {
    this.description = description;
    this.isDone = false;

    numberOfTasks++;
  }

  public String getDescription() {
    return this.description;
  }

  public void changeDescription(String newDescription) {
    this.description = newDescription;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  public static int getNumberOfTasks() {
    return numberOfTasks;
  }

  public void printTask() {
    System.out.println("[" + this.getStatusIcon() + "] " + this.description);
  }

}
