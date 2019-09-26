package com.tilseier.studying.screens.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tilseier.studying.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class RxActivity extends AppCompatActivity {

    private static final String TAG = "RxActivity";

    DisposableObserver<Integer> rxNumbers;
    DisposableSubscriber<Integer> rxFlowableNumbers;
    DisposableSingleObserver<List> rxSingleList;
    DisposableCompletableObserver rxCompleteList;
    DisposableMaybeObserver rxMaybeList;

    DisposableObserver<String> rxMaps;

    CompositeDisposable disposableBag;

    Button btnRxFlowable;
    Button btnRxCountFlowable;
    Button btnRxCountSingle;
    Button btnRxCountComplete;
    Button btnRxCountMaybe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        disposableBag = new CompositeDisposable();

        btnRxFlowable = findViewById(R.id.btn_rx_count);
        btnRxCountFlowable = findViewById(R.id.btn_rx_flowable_count);
        btnRxCountSingle = findViewById(R.id.btn_rx_single_count);
        btnRxCountComplete = findViewById(R.id.btn_rx_complete_count);
        btnRxCountMaybe = findViewById(R.id.btn_rx_maybe_count);
    }

    public void onRxSimpleClick(View view){
        Observable<String> observable = Observable.just("Hello", "World", "RxJava");
        Single<String> single = Single.just("one");
        Maybe<String> maybe = Maybe.just("one");
        Flowable<String> flowable = Flowable.just("one", "two", "tree");
//        Completable<String> completable = Completable.just("one", "two", "tree");

        Disposable d = observable.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.wtf(TAG, s+"; ");
            }

            @Override
            public void onError(Throwable e) {
                Log.wtf(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        disposableBag.add(d);
    }

    public void onRxCountClick(View view){

        rxNumbers = getRxNumbers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer i) {
                        btnRxFlowable.setText("i = " + i);
                        Log.wtf(TAG, i+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.wtf(TAG, e.getMessage());
                        Log.wtf(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        disposableBag.add(rxNumbers);
    }

    public void onRxFlowableCountClick(View view){

        rxFlowableNumbers = getRxFlowableNumbers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer i) {
                        btnRxCountFlowable.setText("i = " + i);
                        Log.wtf(TAG, i+"; ");
                    }

                    @Override
                    public void onError(Throwable t) {
//                        Log.wtf(TAG, t.getMessage());
                        Log.wtf(TAG, t.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        disposableBag.add(rxFlowableNumbers);

    }

    public void onRxSingleClick(View view){

        rxSingleList = getRxSingleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List>() {
                    @Override
                    public void onSuccess(List list) {
                        btnRxCountSingle.setText("list = " + list.toString());
                        Log.wtf(TAG, list+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, ""+e.getLocalizedMessage());
                    }
                });

        disposableBag.add(rxSingleList);

    }

    public void onRxCompleteClick(View view){

        rxCompleteList = getRxCompleteList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.wtf(TAG, "Complete");
                        btnRxCountComplete.setText("Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, ""+e.getLocalizedMessage());
                    }
                });

        disposableBag.add(rxCompleteList);

    }

    public void onRxMaybeClick(View view){

        rxMaybeList = getRxMaybeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableMaybeObserver<List>() {
                    @Override
                    public void onSuccess(List list) {
                        btnRxCountMaybe.setText("list = " + list.toString());
                        Log.wtf(TAG, list+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, ""+e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        disposableBag.add(rxMaybeList);

    }

    public void onRxMapsClick(View view){

        //Map - right order, fast
        //each item emitted by a source Observable and emits the modified item
        rxMaps = getRxStrings()
                .subscribeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + ".length = " + s.length();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.wtf(TAG, s+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.wtf(TAG, "Map Complete");
                    }
                });


        //flatMap - order doesn't matter, fast, return Observables
        //flatMap, switchMap, concatMap also applies a function on each emitted item but instead of returning the modified item,
        // it returns the Observable itself which can emit data again
        rxMaps = getRxStrings()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {

                        int delay = new Random().nextInt(10);
                        Log.i(TAG, "delay = " + delay);
                        return Observable.just(s).delay(delay, TimeUnit.SECONDS);

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.wtf(TAG, s+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.wtf(TAG, "Map Complete");
                    }
                });



        //switchMap
        rxMaps = getRxStrings()
                .subscribeOn(Schedulers.io())
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        int delay = new Random().nextInt(10);
                        Log.i(TAG, "delay = " + delay);
                        return Observable.just(s).delay(delay, TimeUnit.SECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.wtf(TAG, s+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.wtf(TAG, "Map Complete");
                    }
                });



        //concatMap - work with elements step by step, right order, long because of delay for processing each element
        rxMaps = getRxStrings()
                .subscribeOn(Schedulers.io())
                .concatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        int delay = new Random().nextInt(10);
                        Log.i(TAG, "delay = " + delay);
                        return Observable.just(s).delay(delay, TimeUnit.SECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.wtf(TAG, s+"; ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.wtf(TAG, "Map Complete");
                    }
                });


    }

    Observable<Integer> getRxNumbers(){
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for(int i = 0; i < 10; i++){
                    Thread.sleep(1000);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });
    }

    Observable<String> getRxStrings(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String[] str = {"Anton", "Gregor", "Lolita", "Maksym", "Oleksamdr", "Vlad"};
                for (String s: str){
                    emitter.onNext(s);
                }
                emitter.onComplete();
            }
        });
    }

    Flowable<Integer> getRxFlowableNumbers(){
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for(int i = 0; i < 10000; i++){
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);
    }

    Single<List> getRxSingleList(){
        return Single.create(new SingleOnSubscribe<List>() {
            @Override
            public void subscribe(SingleEmitter<List> emitter) throws Exception {
//                int[] list2 = {2,3,45,5,7,8,5,6,84,2,3};
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                emitter.onSuccess(list);
            }
        });
    }

    Completable getRxCompleteList(){
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onComplete();
            }
        });
    }

    Maybe<List> getRxMaybeList(){
        return Maybe.create(new MaybeOnSubscribe<List>() {
            @Override
            public void subscribe(MaybeEmitter<List> emitter) throws Exception {
                List<Integer> list = new ArrayList<>();
                list.add(3);
                list.add(6);
                list.add(9);
                list.add(12);

                emitter.onSuccess(list);
                //or
//                emitter.onComplete();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposableBag.clear();
    }
}
