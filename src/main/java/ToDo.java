import exception.InvalidCreateToDoException;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static void createNewToDo(String userInput) throws InvalidCreateToDoException {
        if (userInput.length() > 0) {
            ToDo todo = new ToDo(userInput);
            Aerus.tasks.add(todo);
            UI.printContent("Added ToDo: " + todo.toString());
        } else {
            throw new InvalidCreateToDoException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void printMark() {
        UI.printContent("Nice! You have done this ToDo:\n\t" + this.toString());
    }

    @Override
    public void printUnmark() {
        UI.printContent("I have unmarked this ToDo:\n\t" + this.toString());
    }
}
