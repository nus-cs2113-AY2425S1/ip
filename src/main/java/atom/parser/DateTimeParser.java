package atom.parser;

public class DateTimeParser {
    public static boolean isValidDateTime(String date, Ui ui) throws
            ArrayIndexOutOfBoundsException, NumberFormatException{
        //format example: "12/11/2024 17:30"
        date = date.trim();
        String[] dateTimeParams = date.split(" ");

        String[] timeParams = dateTimeParams[1].split(":");
        String hourString = timeParams[0].trim();
        String minString = timeParams[1].trim();

        String[] dateParams = dateTimeParams[0].split("/");
        String dayString = dateParams[0].trim();
        String monthString = dateParams[1].trim();
        String yearString = dateParams[2].trim();

        int hour = Integer.parseInt(hourString);
        int min = Integer.parseInt(minString);

        int day = Integer.parseInt(dayString);
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);

        boolean isValidDate = isValidDay(day, ui) && isValidMonth(month, ui) && isValidYear(year, ui);
        boolean isValidTime = isValidHour(hour, ui) && isValidMinute(min, ui);

        return isValidDate && isValidTime;
    }
}
