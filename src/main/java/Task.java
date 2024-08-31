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
		String[] textParts = details.split("/", 3);
		this.description = textParts[0];

		int numOfParts = textParts.length;
		switch (numOfParts) {
		case 1:
			this.date = "";
			break;
		case 2:
			this.date = "("
					+ formatDate(textParts[1])
					+ ")";
			break;
		case 3:
			this.date = "("
					+ formatDate(textParts[1])
					+ formatDate(textParts[2])
					+ ")";
		}
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
