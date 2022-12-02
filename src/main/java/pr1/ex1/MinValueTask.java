package pr1.ex1;

import java.util.concurrent.Callable;

public class MinValueTask implements Callable<Integer> {

    private int[] array;
    private int min;

    public MinValueTask(int[] array) {
        this.array = array;
    }

    @Override
    public Integer call() throws Exception {
        try{
            this.min = this.array[0];
            for (int num: array) {
                if (num < this.min)
                    this.min = num;
                Thread.sleep(1);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return this.min;
    }
}
