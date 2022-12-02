package pr1.ex3;

public enum FileTypeEnum {
    XML ("xml"),
    JSON ("json"),
    XLS ("xls");

    private final String type;

    FileTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
