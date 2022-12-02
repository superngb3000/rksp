package pr1.ex1;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class RecursiveMinValue extends RecursiveTask<Integer> {

    private int[] array;

    public RecursiveMinValue(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2){
            try {
                Thread.sleep(1);
                return Arrays.stream(array).min().getAsInt();
            } catch (InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
        }

        RecursiveMinValue recursiveMinValue = new RecursiveMinValue(Arrays.copyOfRange(array, 0, array.length / 2));
        RecursiveMinValue recursiveMinValue1 = new RecursiveMinValue(Arrays.copyOfRange(array, array.length / 2, array.length));

        recursiveMinValue.fork();
        recursiveMinValue1.fork();

        return (recursiveMinValue.join() < recursiveMinValue1.join()) ? recursiveMinValue.join() : recursiveMinValue1.join();
    }
}
