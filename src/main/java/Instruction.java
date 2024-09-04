// Handles the various input instructions for Yapper
public class Instruction {
    public enum InstructionType {
        ADD, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE
    }
    private InstructionType instructionType;
    private String[] instructionStringArgs;
    private String instructionStringArg;
    private Integer instructionIntegerArg;

    // Constructors
    public Instruction(InstructionType type) {
        this.instructionType = type; // LIST, BYE
    }
    public Instruction(InstructionType type, String taskDesc) {
        this.instructionType = type; // ADD, TODO
        this.instructionStringArg = taskDesc;
    }
    public Instruction(InstructionType type, String taskDesc, String endDate) {
        this.instructionType = type; // DEADLINE
        this.instructionStringArg = taskDesc;
        this.instructionStringArgs = new String[] {endDate};
    }
    public Instruction(InstructionType type, String taskDesc, String startDate, String endDate) {
        this.instructionType = type; // EVENT
        this.instructionStringArg = taskDesc;
        this.instructionStringArgs = new String[] {startDate, endDate};
    }
    public Instruction(InstructionType type, Integer taskOrdinal) {
        this.instructionType = type; // MARK, UNMARK
        this.instructionIntegerArg = taskOrdinal;
    }
    // Getters
    public InstructionType getInstructionType() {
        return instructionType;
    }
    public String[] getInstructionStringArgs() {
        return instructionStringArgs;
    }
    public String getInstructionStringArg() {
        return instructionStringArg;
    }
    public Integer getInstructionIntegerArg() {
        return instructionIntegerArg;
    }
}
