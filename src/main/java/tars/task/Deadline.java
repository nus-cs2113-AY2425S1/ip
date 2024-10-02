package tars.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate by;  // 使用 LocalDate 来存储日期

    // 构造函数：接受任务描述和截止日期
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);  // 调用父类 Task 的构造函数
        this.by = parseDate(by);  // 解析并保存截止日期
    }

    // 构造函数：接受任务描述、截止日期和任务状态
    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description);  // 调用父类 Task 的构造函数
        this.by = parseDate(by);  // 解析并保存截止日期
        this.isDone = isDone;  // 初始化任务状态
    }

    // 将字符串解析为 LocalDate
    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr.trim(), formatter);  // 将日期字符串解析为 LocalDate 对象
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
