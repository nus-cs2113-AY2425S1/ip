public class Task {
	protected String description;
	protected boolean isDone;
	protected String date;

	private static String formatDate(String date) { // from firstDateString /to secondDateString
		int endIndexOfFirstPreposition = date.indexOf(' ');
		String firstPreposition = date.substring(0, endIndexOfFirstPreposition) + ": "; // from

		int secondSeparatorIndex = date.indexOf('/');
		boolean hasNoSecondPart = (secondSeparatorIndex == -1);
		String firstPart = firstPreposition;
		if (hasNoSecondPart) {
			String firstDateString = date.substring(endIndexOfFirstPreposition + 1);
			return "(" + firstPart + firstDateString + ")";
		} else {
			String firstDateString = date.substring(endIndexOfFirstPreposition + 1, secondSeparatorIndex);
			firstPart += firstDateString;
		}

		String secondDate = date.substring(secondSeparatorIndex + 1);
		int endIndexOfSecondPreposition = secondDate.indexOf(' ');
		String secondPreposition = secondDate.substring(0, endIndexOfSecondPreposition) + ": ";
		String secondDateString = secondDate.substring(endIndexOfSecondPreposition + 1);
		String secondPart = secondPreposition + secondDateString;
		return "(" + firstPart + secondPart + ")";
	}


	public Task(String text) {
		this.isDone = false;
		int descriptionIndex = text.indexOf(' ') + 1;
		boolean hasNoDescription = (descriptionIndex == 0);
		if (hasNoDescription) {
			throw new RuntimeException("Missing description");
		}

		int dateSeparatorIndex = text.indexOf('/', descriptionIndex);
		boolean hasNoDateInput = (dateSeparatorIndex == -1);
		if (hasNoDateInput) {
			this.description = text.substring(descriptionIndex);
			this.date = "";
		} else {
			this.description = text.substring(descriptionIndex, dateSeparatorIndex);
			String dateString = text.substring(dateSeparatorIndex + 1);
			this.date = formatDate(dateString);
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
