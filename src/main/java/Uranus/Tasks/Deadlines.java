package Uranus.Tasks;

public class Deadlines extends Task{

    protected String by;
    private static final String DEADLINE_TAG = "D";
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String DEADLINE_LABEL = "by:";

    public Deadlines(String description){
        super(description, DEADLINE_TAG);
    }

    public void setBy(String by){
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public void setDescription(String description){
        int separatorIndex = description.indexOf(' ');
        String[] str = description.split(DEADLINE_SEPARATOR);
        setBy(str[1]);
        this.description = description.substring(separatorIndex + 1, description.indexOf(DEADLINE_SEPARATOR))
                + "(" + DEADLINE_LABEL + getBy() + ")";
    }
}
