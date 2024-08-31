public class UserInputParser {
    public String userInput;

    public UserInputParser(String userInput) {
        this.userInput = userInput.trim();
    }

    public Task parseTask(){

        String taskType = userInput.substring(0 , 4);
        String taskDescription = userInput.substring(4).trim();
        char taskTypeChar = getCorrespondingCharForTaskType(taskType);
        return new Task(taskDescription , taskTypeChar);
    }

    public Event parseEvent(){

        String remainingTaskDescription = userInput.substring(6);
        int indexOfFirstSlash = remainingTaskDescription.indexOf('/');
        String taskDescription = remainingTaskDescription.substring(0, indexOfFirstSlash).trim();
        remainingTaskDescription = remainingTaskDescription.substring(indexOfFirstSlash + 6);
        int indexOfSecondSlash = remainingTaskDescription.indexOf('/');
        String startTime = remainingTaskDescription.substring(0, indexOfSecondSlash).trim();
        String endTime = remainingTaskDescription.substring(indexOfSecondSlash + 4).trim();
        return new Event(taskDescription , startTime, endTime);
    }

    public Deadline parseDeadline(){

        String remainingTaskDescription = userInput.substring(9);
        int indexOfSlash = remainingTaskDescription.indexOf('/');
        String taskDescription = remainingTaskDescription.substring(0, indexOfSlash).trim();
        String deadline = remainingTaskDescription.substring(indexOfSlash + 4);
        return new Deadline(taskDescription , deadline);
    }

    public char getCorrespondingCharForTaskType(String type){
        switch(type){
        case "todo" :
            return 'T';
        case "event" :
            return 'E';
        case "deadline" :
            return 'D';
        default:
            return ' ';
        }
    }
}
