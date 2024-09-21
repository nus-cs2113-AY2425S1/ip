package erika.settings;

import java.time.format.DateTimeFormatter;

public class Settings {
    public static final int FROM_LENGTH_OFFSET = 6;
    public static final int TO_LENGTH_OFFSET = 4;
    public static final int BY_LENGTH_OFFSET = 4;
    public static final int SPACE_OFFSET = 1;
    public static final int BY_REAR_OFFSET = 1;
    public static final int FROM_REAR_OFFSET = 1;
    public static final int TO_REAR_OFFSET = 1;

    public static final String DIRECTORY = "data";
    public static final String FILENAME = DIRECTORY + "/tasks.txt";
    public static final String SEPARATOR = ",";

    public static final DateTimeFormatter DATE_IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_TIME_IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static final DateTimeFormatter DATE_OUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    public static final DateTimeFormatter DATE_TIME_OUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
}
