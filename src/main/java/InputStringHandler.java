// Input Text Parser for Yapper
public class InputStringHandler {
     public static Instruction parseUserInput(String userInputString) {
        String[] instruction = userInputString.split(" ", 2);
        String instructionType = instruction[0].toLowerCase(); // need to convert to lowercase?
        String instructionArguments = instruction.length > 1 ? instruction[1] : "";
        // account for OOB in case command is 1 word long

        switch (instructionType) {
        case "add":
            return new Instruction(Instruction.InstructionType.ADD, instructionArguments);
        case "todo":
            return new Instruction(Instruction.InstructionType.TODO, instructionArguments);
        case "deadline":
            String[] args = instructionArguments.split(" /by ", 2);
            String taskDesc = args[0].trim();
            String deadline = args[1].trim();
            return new Instruction(Instruction.InstructionType.DEADLINE, taskDesc, deadline);
        case "event":
            String[] args1 = instructionArguments.split(" /from ", 2);
            String eventDesc = args1[0].trim();
            String[] args2 = args1[1].split(" /to ", 2);
            String startDate = args2[0].trim();
            String endDate = args2[1].trim();
            return new Instruction(Instruction.InstructionType.EVENT, eventDesc, startDate, endDate);
        case "list":
            return new Instruction(Instruction.InstructionType.LIST);
        case "mark":
            return new Instruction(Instruction.InstructionType.MARK, Integer.parseInt(instructionArguments));
        case "unmark":
            return new Instruction(Instruction.InstructionType.UNMARK, Integer.parseInt(instructionArguments));
        case "bye":
            return new Instruction(Instruction.InstructionType.BYE);
        }
        return new Instruction(null); // shouldn't happen
    }
}
