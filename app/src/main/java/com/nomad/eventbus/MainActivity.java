package com.nomad.eventbus;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nomad.event.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyEvent event = new MyEvent("msg from publisher main thread...");
                EventBus.getDefault().post(event);
            }
        }).start();

        /*MyEvent event = new MyEvent("msg from publisher main thread...");
        EventBus.getDefault().post(event);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostingEvent(MyEvent event) {
        Log.d(TAG, "onPostingEvent: " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(MyEvent event) {
        Log.d(TAG, "onMainEvent: " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEvent(MyEvent event) {
        Log.d(TAG, "onBackgroundEvent: " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEvent(MyEvent event) {
        Log.d(TAG, "onAsyncEvent: " + Thread.currentThread().getName());
    }

}
