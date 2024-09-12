import freedom.exceptions.DescriptionEmpty;

public class ToDo extends Task {
    public ToDo(String description) throws Exception {
        super(description.trim());
        try {
            if (getDescription().isEmpty()) {
                throw new DescriptionEmpty();
            }
        } catch (DescriptionEmpty e) {
            printEmptyDescriptionError();
            throw new Exception("Description is empty");
        }
    }

    public String printLine() {
        return "[T]" + super.printLine();
    }
}
