public class Deadlines extends Task{

    protected String by;

    public Deadlines(String description){
        super(description, "D");
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
        String[] str = description.split("/by");
        setBy(str[1]);
        this.description = description.substring(separatorIndex + 1, description.indexOf('/'))
                + "(by:" + getBy() + ")";
    }
}
