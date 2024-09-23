package TheThinker.Exceptions;


public class FormattingException extends Exception{

    public FormattingException(String message){
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(this.getMessage());
        System.out.println("_".repeat(60));
    }
}
