package pr1.ex1;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;

public class Ex1 {

    private int F1(int[] array) throws InterruptedException {
        return new ArrayInteractor().getMinValue(array);
    }

    private int F2(int[] array) throws ExecutionException, InterruptedException {
        MinValueTask minValueTask = new MinValueTask(Arrays.copyOfRange(array, 0, array.length / 2));
        MinValueTask minValueTask1 = new MinValueTask(Arrays.copyOfRange(array, array.length / 2, array.length));

        FutureTask<Integer> futureTask = new FutureTask<>(minValueTask);
        FutureTask<Integer> futureTask1 = new FutureTask<>(minValueTask1);

        Thread thread = new Thread(futureTask);
        Thread thread1 = new Thread(futureTask1);

        thread.start();
        thread1.start();

        return (futureTask1.get() < futureTask1.get())? futureTask1.get() : futureTask1.get();
    }

    private int F3(int[] array){
        RecursiveMinValue recursiveMinValue = new RecursiveMinValue(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(recursiveMinValue);
    }


    public Ex1() throws InterruptedException, ExecutionException {
        System.out.println("-==1==-");

        int[] array = new ArrayInteractor().newArray(1000, 10, 50);

        System.out.println("\tПоследовательно");
        long start = System.currentTimeMillis();
        System.out.println("\t\tМинимальное значение: " + F1(array));
        long end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));

        System.out.println("\tFutureTask");
        start = System.currentTimeMillis();
        System.out.println("\t\tМинимальное значение: " + F2(array));
        end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));

        System.out.println("\tForkJoin");
        start = System.currentTimeMillis();
        System.out.println("\t\tМинимальное значение: " + F3(array));
        end = System.currentTimeMillis();
        System.out.println("\t\tВремя выполнение: " + (end - start));
    }
}
