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

    System.out.print("Nice! I've marked this task as done: \n  ");
    printTask();
  }

  public void markAsUndone() {
    this.isDone = false;

    System.out.print("OK, I've marked this task as not done yet: \n  ");
    printTask();
  }

  public static int getNumberOfTasks() {
    return numberOfTasks;
  }

  public void printTask() {
    System.out.println("[" + this.getStatusIcon() + "] " + this.description);
  }

}
