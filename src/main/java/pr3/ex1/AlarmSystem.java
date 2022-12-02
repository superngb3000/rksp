package pr3.ex1;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import pr3.ex1.sensors.SensorsEnum;

import java.util.HashMap;
import java.util.Map;

public class AlarmSystem implements Observer{
    private final float normalCO2;
    private final float normalTemperature;

    private SensorStateEnum sensorsState(float currentTemperature, float currentCO2){
        if (currentCO2 > normalCO2 && currentTemperature > normalTemperature)
            return SensorStateEnum.ALARM;
        if (currentCO2 > normalCO2)
            return SensorStateEnum.HIGH_CO2;
        if (currentTemperature > normalTemperature)
            return SensorStateEnum.HIGH_TEMPERATURE;
        return SensorStateEnum.OK;
    }

    public AlarmSystem(float normalCO2, float normalTemperature) {
        this.normalCO2 = normalCO2;
        this.normalTemperature = normalTemperature;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Object o) {
        Map<String, Float> sensorsData = (HashMap) o;
        System.out.println("\t" + sensorsState(sensorsData.get(SensorsEnum.TEMPERATURE), sensorsData.get(SensorsEnum.CO2)).getMessage() +
                "|\t" + SensorsEnum.TEMPERATURE + ": " + sensorsData.get(SensorsEnum.TEMPERATURE) +
                " | " + SensorsEnum.CO2 + ": " + sensorsData.get(SensorsEnum.CO2));
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
