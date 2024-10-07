package pythia.task;

import pythia.utility.WriteVisitor;

/**
 * Represents a contract for objects that can be saved.
 * Classes implementing this interface must define how they interact with a
 * {@link WriteVisitor} for the purpose of saving their state or data.
 */
public interface Savable {
    /**
     * Accepts a {@link WriteVisitor} to perform a write operation on the implementing object.
     *
     * @param visitor The visitor that handles writing the object's data to a storage format.
     * @return A string representation of the object's data formatted for saving.
     */
    public String accept(WriteVisitor visitor);
}
