package pr3.ex1.sensors;

import java.util.Random;

public class ValueGenerator {
    static float valueGenerator(float min, float max){
        return min + new Random().nextFloat() * (max - min);
    }
}
