package com.tilseier.studying.screens.Sockets;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;

import com.tilseier.studying.R;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SocketsActivity extends AppCompatActivity {

    private static final String TAG = "SocketsActivity_TAG";
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sockets);

        Observable complete = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("ObservableOnSubscribe");
                try(Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress("developer.android.com", 80), 2000);
                    Scanner scanner = new Scanner(socket.getInputStream());
                    while (scanner.hasNext()) {
                        emitter.onNext(scanner.nextLine());
//                        System.out.println(scanner.nextLine());
                    }
                }catch (NetworkOnMainThreadException | IOException e){
                    emitter.onError(e);
                }
            }
        });
        DisposableObserver disposableObserver = new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                Timber.tag(TAG).e("RESULT: %s", s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Timber.tag(TAG).e("COMPLETE");
            }
        };
        complete
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);

    }
}
