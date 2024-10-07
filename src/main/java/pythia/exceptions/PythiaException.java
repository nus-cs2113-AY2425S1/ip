package pythia.exceptions;

/**
 * Represents a custom exception for the Pythia task management system.
 * This exception extends {@link RuntimeException} and is used to signal
 * errors that occur during the execution of Pythia's functionalities.
 */
public class PythiaException extends RuntimeException {
  private String userMessage;

    /**
     * Constructs a PythiaException with the specified detail message and user message.
     *
     * @param message    The detail message for the exception, which is logged for debugging purposes.
     * @param userMessage A user-friendly message that can be displayed to the user.
     */
  public PythiaException(String message, String userMessage) {
      super(message);
      this.userMessage = userMessage;
  }

  public String getUserMessage() {
    return userMessage;
  }
}
