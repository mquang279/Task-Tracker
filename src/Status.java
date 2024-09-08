public enum Status {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private final String value;

    Status(String value){
        this.value = value;
    }

}