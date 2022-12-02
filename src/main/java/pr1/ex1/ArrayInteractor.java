package pr1.ex1;

public class ArrayInteractor {

    public int[] newArray(int n, int min, int max) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int)((Math.random() * (max - min)) + min);
        }
        return array;
    }

    public int getMinValue(int[] array) throws InterruptedException {
        int min = array[0];
        for (int num: array){
            if (num < min)
                min = num;
            Thread.sleep(1);
        }
        return min;
    }
}
