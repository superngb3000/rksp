package pr3.ex1;

import io.reactivex.rxjava3.core.Observable;
import pr3.ex1.sensors.CO2Sensor;
import pr3.ex1.sensors.Sensor;
import pr3.ex1.sensors.SensorsEnum;
import pr3.ex1.sensors.TemperatureSensor;

import java.util.HashMap;
import java.util.Map;


public class Ex1 {
    private final float normalCO2 = 70;
    private final float normalTemperature = 25;
    private final float minCO2 = 30;
    private final float maxCO2 = 100;
    private final float minTemperature = 15;
    private final float maxTemperature = 30;

    static private Map<SensorsEnum, Float> sensorsDataMapper(Sensor temperatureSensor, Sensor co2Sensor) {
        Map<SensorsEnum, Float> sensorsData = new HashMap<>();
        sensorsData.put(SensorsEnum.TEMPERATURE, temperatureSensor.getCurrentValue());
        sensorsData.put(SensorsEnum.CO2, co2Sensor.getCurrentValue());
        return sensorsData;
    }

    public Ex1() {
        System.out.println("-==1==-");

        AlarmSystem alarmSystem = new AlarmSystem(normalCO2, normalTemperature);

        Observable.zip(
                Observable.create(tempEmitter -> {
                    Thread temperatureSensorThread = new Thread(new TemperatureSensor(minTemperature, maxTemperature, tempEmitter));
                    temperatureSensorThread.start();
                }),
                Observable.create(tempEmitter -> {
                    Thread co2SensorThread = new Thread(new CO2Sensor(minCO2, maxCO2, tempEmitter));
                    co2SensorThread.start();
                }),
                Ex1::sensorsDataMapper)
                .subscribe(alarmSystem);
    }
}
