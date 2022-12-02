package pr1.ex2;

import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2 {

    private void F() throws ExecutionException, InterruptedException {
        int nThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        Scanner scanner = new Scanner(System.in);

        double num;
        String input = "";

        while (true){
            input = scanner.nextLine();

            try{
                num = Double.parseDouble(input);
            } catch(NumberFormatException e){
                System.out.println("END");
                break;
            }

            PowTask powTask = new PowTask(num);
            FutureTask<Double> futureTask = new FutureTask<>(powTask);
            executorService.submit(futureTask);

            System.out.println("\t" + num + "^2 = " + futureTask.get());
        }
        executorService.shutdown();
    }

    public Ex2() throws ExecutionException, InterruptedException {
        System.out.println("-==2==-");
        this.F();
    }
}
