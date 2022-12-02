package pr1.ex2;

import java.util.concurrent.Callable;

public class PowTask implements Callable<Double> {

    private double num;
    private double result;

    public PowTask(double num) {
        this.num = num;
    }


    @Override
    public Double call() {
        try{
            Thread.sleep((int)((Math.random() * 4000) + 1000));
            this.result = Math.pow(num, 2);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return this.result;
    }
}
