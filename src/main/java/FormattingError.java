public class FormattingError extends Exception{

    public FormattingError(String message){
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(this.getMessage());
    }
}
