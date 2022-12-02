package pr3.ex2;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

public class Ex2 {

    private void ex1(){
        System.out.println("\t№1");
        Random random = new Random();
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1000);
        }
        System.out.print("\t\tSource array: [ ");
        for (int i: arr){
            System.out.print(i + " ");
        }
        System.out.println("]");
        Observable<Integer> observable = Observable.create(tempEmitter -> {
            for (int num: arr){
                tempEmitter.onNext(num);
            }
        });
        System.out.print("\t\tResult array: [ ");
        observable.subscribe(tempEmitter -> {
            System.out.print(tempEmitter * tempEmitter + " ");
        });
        System.out.println("]");
    }

    private void ex2(){
        System.out.println("\t№2");
        Random random = new Random();
        int n = 1000;
        char[] chars = new char[n];
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            chars[i] = (char) ('a' + random.nextInt(26));
            ints[i] = random.nextInt(1000);
        }
        System.out.print("\t\tSource characters: [ ");
        for (int i: chars){
            System.out.print((char) i + " ");
        }
        System.out.println("]");
        System.out.print("\t\tSource integers: [ ");
        for (int i: ints){
            System.out.print(i + " ");
        }
        System.out.println("]");
        System.out.print("\t\tResult array: [ ");
        Observable.zip(
                Observable.create(tempEmitter -> {
                    for (char c: chars){
                        tempEmitter.onNext(c);
                    }
                }),
                Observable.create(tempEmitter -> {
                    for (int num: ints){
                        tempEmitter.onNext(num);
                    }
                }),
                (character, integer) -> ""+character + integer)
                .subscribe(
                        tempEmitter -> {
                            System.out.print(tempEmitter + " ");
                        });
        System.out.println("]");
    }

    private void ex3(){
        System.out.println("\t№3");
        Random random = new Random();
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1000);
        }
        System.out.print("\t\tSource array: [ ");
        for (int i: arr){
            System.out.print(i + " ");
        }
        System.out.println("]");
        System.out.print("\t\tResult array: [ ");
        Observable.create(
                tempEmitter -> {
                    for (int num: arr){
                        tempEmitter.onNext(num);
                    }
                })
                .skip(3)
                .subscribe(
                        tempEmitter -> {
                            System.out.print(tempEmitter + " ");
                        });
        System.out.println("]");
    }

    public Ex2() {
        System.out.println("-==2==-");
        ex1();
        ex2();
        ex3();
    }
}
