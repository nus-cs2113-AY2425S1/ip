public class PythiaException extends RuntimeException {
  private String userMessage;
  public PythiaException(String message, String userMessage) {
      super(message);
      this.userMessage = userMessage;
  }

  public String getUserMessage() {
    return userMessage;
  }
}
