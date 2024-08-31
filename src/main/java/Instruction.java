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
    public Instruction(InstructionType type, String arg) {
        this.instructionType = type; // ADD, TODO
        this.instructionStringArg = arg;
    }
    public Instruction(InstructionType type, String arg1, String arg2) {
        this.instructionType = type; // DEADLINE
        this.instructionStringArg = arg1;
        this.instructionStringArgs = new String[] {arg2};
    }
    public Instruction(InstructionType type, String arg1, String arg2, String arg3) {
        this.instructionType = type; // EVENT
        this.instructionStringArg = arg1;
        this.instructionStringArgs = new String[] {arg2, arg3};
    }
    public Instruction(InstructionType type, Integer arg) {
        this.instructionType = type; // MARK, UNMARK
        this.instructionIntegerArg = arg;
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
