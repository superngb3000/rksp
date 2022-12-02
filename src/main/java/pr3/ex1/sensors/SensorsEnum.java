package pr3.ex1.sensors;

public enum SensorsEnum {
    CO2("CO2"),
    TEMPERATURE("Temperature");

    private String sensorName;

    SensorsEnum(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }
}
