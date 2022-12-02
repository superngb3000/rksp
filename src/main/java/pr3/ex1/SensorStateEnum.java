package pr3.ex1;

public enum SensorStateEnum {
    OK("OK! TEMPERATURE and CO2 - NORMAL"),
    HIGH_TEMPERATURE("WARNING! TEMPERATURE - HIGH"),
    HIGH_CO2("WARNING! CO2 HIGH"),
    ALARM("ALARM!!! TEMPERATURE AND CO2 - HIGH");

    private String message;

    SensorStateEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
