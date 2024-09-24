package yapper.instructions;

/**
 * Represents an Instruction in Yapper.
 * <p>
 * Currently consists of 9 instruction types:
 * HELP, FIND, LIST, TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK
 * <p/>
 *
 */
public class Instruction {

    /**
     * Enumeration of the types of instructions supported by Yapper.
     *
     * <p>
     * Currently includes:
     * <li> HELP: Prints a help message. <li/>
     * FIND: Show tasks that contains the query. <li/>
     * LIST: Show all tasks. <li/>
     * TODO: Add a ToDo task. <li/>
     * DEADLINE: Add a Deadline task. <li/>
     * EVENT: Add an Event task. <li/>
     * DELETE: Remove a task. <li/>
     * MARK: Mark a task as complete. <li/>
     * UNMARK: Unmark a task as incomplete.
     * <p/>
     *
     */
    public enum InstructionType {
        HELP, LIST, FIND,
        TODO, DEADLINE, EVENT,
        DELETE, MARK, UNMARK,
    }

    /**
     * The type of the instruction.
     *
     * <p>
     * It is used in all instruction types.
     * </p>
     *
     */
    private InstructionType instructionType;
    /**
     * An array representing the start and/or end date of a task, if applicable.
     *
     * <p>
     * It is used in these instruction types:
     * For DEADLINE tasks, it contains one date (the deadline).
     * For EVENT tasks, it contains two dates (start and end date).
     * </p>
     *
     */
    private String[] taskDates; // Non-Desc
    /**
     * The description of the task associated with the instruction.
     *
     * <p>
     * It is used in all instruction types.
     * </p>
     *
     */
    private String instructionDesc; // Desc
    /**
     * The ordinal number of a task,
     *
     * <p>
     * It is used in these instruction types:
     * DELETE, MARK, UNMARK.
     * </p>
     *
     */
    private Integer taskOrdinal; // Ordinal


    // Constructors

    /**
     * Constructs an Instruction with the specified type.
     *
     * <p>
     * Used for instructions that don't require additional information,
     * such as LIST and HELP.
     * </p>
     *
     * @param type The type of instruction.
     */
    public Instruction(InstructionType type) {
        this.instructionType = type;
    }
    /**
     * Constructs an Instruction with the specified type and task description.
     *
     * <p>
     * Used for instructions that require a task description,
     * such as TODO and FIND.
     * </p>
     *
     * @param type The type of instruction.
     * @param taskDesc The description of the task.
     */
    public Instruction(InstructionType type, String taskDesc) {
        this.instructionType = type;
        this.instructionDesc = taskDesc;
    }
    /**
     * Constructs an Instruction with the specified type, task description, and end date.
     *
     * <p>
     * Used for DEADLINE instructions, which require a task description and a deadline.
     * </p>
     *
     * @param type The type of instruction.
     * @param taskDesc The description of the task.
     * @param endDate The deadline of the task.
     */
    public Instruction(InstructionType type, String taskDesc, String endDate) {
        this.instructionType = type;
        this.instructionDesc = taskDesc;
        this.taskDates = new String[] {endDate};
    }
    /**
     * Constructs an Instruction with the specified type, task description, start date, and end date.
     *
     * <p>
     * Used for EVENT instructions, which require a task description, start date, and end date.
     * </p>
     *
     * @param type The type of instruction.
     * @param taskDesc The description of the task.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Instruction(InstructionType type, String taskDesc, String startDate, String endDate) {
        this.instructionType = type;
        this.instructionDesc = taskDesc;
        this.taskDates = new String[] {startDate, endDate};
    }
    /**
     * Constructs an Instruction with the specified type and task ordinal.
     *
     * <p>
     * Used for DELETE, MARK, and UNMARK instructions, which require a task ordinal number.
     * </p>
     *
     * @param type The type of instruction.
     * @param taskOrdinal The ordinal number of the task.
     */
    public Instruction(InstructionType type, Integer taskOrdinal) {
        this.instructionType = type; // DELETE, MARK, UNMARK
        this.taskOrdinal = taskOrdinal;
    }


    // Getters

    /**
     * Returns the type of the instruction.
     *
     * @return The instruction type.
     */
    public InstructionType getInstructionType() {
        return instructionType;
    }
    /**
     * Returns the dates associated with the task, if applicable.
     *
     * @return An array of dates for DEADLINE and EVENT instructions, or null for other types.
     */
    public String[] getTaskDates() {
        return taskDates;
    }
   /**
     * Returns the description of the task associated with the instruction.
     *
     * @return The task description, or null if not applicable.
     */
    public String getInstructionDesc() {
        return instructionDesc;
    }
    /**
     * Returns the ordinal number of the task associated with the instruction.
     *
     * @return The task ordinal, or null if not applicable.
     */
    public Integer getTaskOrdinal() {
        return taskOrdinal;
    }
}
