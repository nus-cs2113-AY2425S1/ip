public class UranusExceptions extends Exception{
    public UranusExceptions(){
        super();
    }

    // Overloaded in the event that a message is needed
    public UranusExceptions(String message){
        super(message);
    }
}
