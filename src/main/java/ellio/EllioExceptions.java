package ellio;

public class EllioExceptions extends Exception{

    public EllioExceptions (String message){
        super(message);
    }

    public static class WrongDeadlineFormatTimeException extends EllioExceptions{
        public WrongDeadlineFormatTimeException (){
            super(BotText.lineBorder + BotText.messageInvalidDeadlineDateFormat + BotText.lineBorder);
        }
    }
    public static class WrongEventEndFormatException extends EllioExceptions{
        public WrongEventEndFormatException (){
            super(BotText.lineBorder + BotText.messageInvalidEventEndFormat +BotText.lineBorder);
        }
    }

    public static class WrongEventStartFormatException extends EllioExceptions{
        public WrongEventStartFormatException (){
            super(BotText.lineBorder + BotText.messageInvalidEventStartFormat +BotText.lineBorder);
        }
    }
}
