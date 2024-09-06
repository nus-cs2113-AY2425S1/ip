public class Task {
    protected String description;
    protected boolean isDone;
    protected String date;

    private static String formatDate(String date) {
		String[] textParts = date.split(" ", 2);
		return textParts[0] + ": " + textParts[1];
    }


    public Task(String details) {
        this.isDone = false;
		String[] textParts = details.split("/");
		this.description = textParts[0];

		int numOfParts = textParts.length;
        if (numOfParts == 1) {
            this.date = "";
            return;
        }
        String date = "(";
        for (int i = 1; i < numOfParts; i++) {
            date = date.concat(formatDate(textParts[i]));
        }
        this.date = date.concat(")");
    }

    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    @Override
    public String toString() {
        return getStatus() + description + date;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkFromDone() {
        isDone = false;
    }
}
