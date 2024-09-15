package yapper.instructions;

// Handles the various input instructions for Yapper
public class Instruction {
    public enum InstructionType {
        LIST,
        TODO, DEADLINE, EVENT,
        DELETE, MARK, UNMARK
    }
    private InstructionType instructionType;
    private String[] taskDates; // Non-Desc
    private String instructionDesc; // Desc
    private Integer taskOrdinal; // Ordinal

    // Constructors
    public Instruction(InstructionType type) {
        this.instructionType = type; // LIST
    }
    public Instruction(InstructionType type, String taskDesc) {
        this.instructionType = type; // TODO
        this.instructionDesc = taskDesc;
    }
    public Instruction(InstructionType type, String taskDesc, String endDate) {
        this.instructionType = type; // DEADLINE
        this.instructionDesc = taskDesc;
        this.taskDates = new String[] {endDate};
    }
    public Instruction(InstructionType type, String taskDesc, String startDate, String endDate) {
        this.instructionType = type; // EVENT
        this.instructionDesc = taskDesc;
        this.taskDates = new String[] {startDate, endDate};
    }
    public Instruction(InstructionType type, Integer taskOrdinal) {
        this.instructionType = type; // MARK, UNMARK
        this.taskOrdinal = taskOrdinal;
    }
    // Getters
    public InstructionType getInstructionType() {
        return instructionType;
    }
    public String[] getTaskDates() {
        return taskDates;
    }
    public String getInstructionDesc() {
        return instructionDesc;
    }
    public Integer getTaskOrdinal() {
        return taskOrdinal;
    }
}
