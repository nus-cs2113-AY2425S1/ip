package pythia.task;

import pythia.utility.WriteVisitor;

public interface Savable {
    public String accept(WriteVisitor visitor);
}
