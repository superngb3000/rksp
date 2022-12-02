package pr3.ex1.sensors;

import io.reactivex.rxjava3.core.Emitter;

public abstract class Sensor implements Runnable{
    private final float minValue;
    private final float maxValue;
    private float currentValue = 0;
    private final Emitter emitter;
    private SensorsEnum sensorsEnum;

    public Sensor(float minValue, float maxValue, Emitter emitter, SensorsEnum sensorsEnum) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.emitter = emitter;
        this.sensorsEnum = sensorsEnum;

        this.generateValue();
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void generateValue(){
        currentValue = ValueGenerator.valueGenerator(minValue, maxValue);
    }

    @Override
    public void run() {
        int i = 0;
        while(i < 20){
            try {
                Thread.sleep(1000);
                this.generateValue();
                emitter.onNext(this);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
