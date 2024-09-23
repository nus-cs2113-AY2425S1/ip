package yapper.instructions;

// Handles the various input instructions for Yapper

/**
 * Represents an Instruction in Yapper.
 * <p>
 * Currently consists of 7 instruction types:
 * LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK
 * <p/>
 *
 */
public class Instruction {

    /**
     * Enumeration of the types of instructions supported by Yapper.
     * <p>
     * Currently includes:
     * - LIST: Show all tasks.
     * - TODO: Add a ToDo task.
     * - DEADLINE: Add a Deadline task.
     * - EVENT: Add an Event task.
     * - DELETE: Remove a task.
     * - MARK: Mark a task as complete.
     * - UNMARK: Unmark a task as incomplete.
     * <p/>
     */
    public enum InstructionType {
        LIST,
        TODO, DEADLINE, EVENT,
        DELETE, MARK, UNMARK
    }

    /**
     * The type of the instruction.
     *
     * It is used in all instruction types.
     */
    private InstructionType instructionType;
    /**
     * An array representing the start and/or end date of a task, if applicable.
     *
     * It is used in these instruction types:
     * For DEADLINE tasks, it contains one date (the deadline).
     * For EVENT tasks, it contains two dates (start and end date).
     */
    private String[] taskDates; // Non-Desc
    /**
     * The description of the task associated with the instruction.
     *
     * It is used in all instruction types.
     */
    private String instructionDesc; // Desc
    /**
     * The ordinal number of a task,
     *
     * It is used in these instruction types:
     * DELETE, MARK, UNMARK.
     */
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
        this.instructionType = type; // DELETE, MARK, UNMARK
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
