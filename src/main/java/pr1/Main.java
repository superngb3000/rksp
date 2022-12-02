package pr1;

import pr1.ex1.Ex1;
import pr1.ex2.Ex2;
import pr1.ex3.Ex3;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Ex1();
        new Ex2();
        new Ex3();
    }
}
