package com.ankitb.rxtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "RxTesting";
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text_view);

        /*Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted ");

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError :: " + e);

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext :: " + s);
                System.out.println(s);
            }
        };

        myObservable.subscribe(mySubscriber);

        Observable.just("Hello world again").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onAction :: " + s);
                System.out.println(s);
            }
        });

        //Lets try Lambdas. Using RetroLambdas lib to build in Android
        Observable.just("Hello world Lambdas").subscribe(s -> Log.d(TAG,"onAction:"+s));

        Observable.just("Hello world ")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s +" Adding prefix";
                    }
                })
                .map(s -> s+ " Another prefix")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> Log.d(TAG, "onAction:" + s));


        Observable.from("Text1","Text2","Text3").subscribe(s -> printText((String)s));*/


        Observable.OnSubscribe observableAction = new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World !");
                subscriber.onCompleted();
            }
        };

//        Observable<String> observable = Observable.create(observableAction);
//        observable.observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(getToastSubscriber());
//        observable.subscribe(getTextViewSubscriber());

        Action1<String> textViewOnNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                mTextView.setText(s);
            }
        };

        Func1<String, String > upperCaseFunction =  new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.toUpperCase();
            }
        };


        Observable<String> singleObservable = Observable.just("Hello people out there");
        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(String::toUpperCase)
                .subscribe(textViewOnNextAction);


        Action1<String> toastOnNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
        String[] words = {"one", "two", "three", "four"};
        Observable<String> oneByOneObservable = Observable.from(words);
        oneByOneObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(toastOnNextAction);


        Observable.just(Arrays.asList(words))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(getStrings)
                .reduce(mergeStrings)
                .subscribe(toastOnNextAction);

    }

    Func1<List<String>, Observable<String >> getStrings = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            return Observable.from(strings);
        }
    };

    Func2<String, String, String> mergeStrings = new Func2<String, String, String>() {
        @Override
        public String call(String s1, String s2) {
            return String.format("%s + %s", s1, s2);
        }
    };

    private Subscriber<String> getToastSubscriber(){
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"Toast: onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Toast: onError : " + e);

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"Toast: onNext : " + s);
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        };
    }

    private Subscriber<String> getTextViewSubscriber(){
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"Text: onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Text: onError : " + e);

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"Text: onNext : " + s);
                mTextView.setText(s);
            }
        };
    }

    public void printText(String s){
        Log.d(TAG, "printText:" + s)   ;
    }


}
