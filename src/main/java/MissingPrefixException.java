public class MissingPrefixException extends Exception {
    public String missingPrefix;

    public MissingPrefixException(String missingPrefix) {
        this.missingPrefix = missingPrefix;
    }
}
