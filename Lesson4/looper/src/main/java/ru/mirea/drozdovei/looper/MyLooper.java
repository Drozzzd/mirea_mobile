package ru.mirea.drozdovei.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.Locale;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;
    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String  data = msg.getData().getString("KEY");
                int age = msg.getData().getInt("age");
                Log.d("MyLooper get message: ", data);

                int count = data.length();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format(Locale.getDefault(), "Полученная строка: %s\nДлина строки: %d", data, count));
                message.setData(bundle);

//				mainHandler.sendMessage(message);

                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainHandler.sendMessage(message);
                    }
                }, age);
            }
        };
        Looper.loop();
    }
}
