package hsien.datetime;

import hsien.exception.HsienException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime
{
    public void checkDate(String dateTime) throws HsienException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            dateTime = String.valueOf(LocalDateTime.parse(dateTime, formatter));
        } catch (DateTimeParseException e) {
            throw new HsienException("Please enter date in proper format! (YYYY-MM-DD HHMM)");
        }
    }

    public String formatDate(String dateTime) {
        LocalDateTime date = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }
}
