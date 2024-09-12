
public class DukeException extends Exception {

    //Constructor
    public DukeException(String message) {
        super(message);
    }


    //Display warning message
    public void displayMessage() {
        System.out.println("Warning: " + getMessage());
    }

}
