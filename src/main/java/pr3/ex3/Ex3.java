package pr3.ex3;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

public class Ex3 {

    private UserFriend[] arr;

    Observable<UserFriend> getFriends(int userId){
        return Observable.fromArray(arr).filter(userFriend -> userFriend.getFriendId() == userId);
    }

    public Ex3() {
        System.out.println("-==3==-");
        Random random = new Random();
        int n = 100;
        arr = new UserFriend[n];
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++){
            arr[i] = new UserFriend(i, random.nextInt(100));
            ids[i] = random.nextInt(100);
        }
        Observable.fromArray(ids)
                .flatMap(i -> getFriends(i))
                .subscribe(i -> System.out.println("\t" + i));
    }
}
