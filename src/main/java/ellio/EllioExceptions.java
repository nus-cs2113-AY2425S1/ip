package ellio;

public class EllioExceptions extends Exception{

    public EllioExceptions (String message){
        super(message);
    }

    public static class WrongDeadlineFormatTimeException extends EllioExceptions{
        public WrongDeadlineFormatTimeException (){
            super(BotText.LINE_BORDER +
                    BotText.MESSAGE_INVALID_DEADLINE_TIME_FORMAT +
                    BotText.LINE_BORDER);
        }
    }
    public static class WrongEventEndFormatException extends EllioExceptions{
        public WrongEventEndFormatException (){
            super(BotText.LINE_BORDER +
                    BotText.MESSAGE_INVALID_EVENT_END_FORMAT +
                    BotText.LINE_BORDER);
        }
    }

    public static class WrongEventStartFormatException extends EllioExceptions{
        public WrongEventStartFormatException (){
            super(BotText.LINE_BORDER +
                    BotText.MESSAGE_INVALID_EVENT_START_FORMAT +
                    BotText.LINE_BORDER);
        }
    }

    public static class UnknownCommandException extends EllioExceptions{
        public UnknownCommandException (){
            super(BotText.LINE_BORDER +
                    BotText.MESSAGE_INVALID_COMMAND +
                    BotText.LINE_BORDER);
        }
    }

    public static class ErrorLoadingFileException extends EllioExceptions{
        public ErrorLoadingFileException (){
            super("Unable to load file");
        }
    }
}
