package ru.mirea.drozdovei.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import ru.mirea.drozdovei.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.infoTextView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("Мой номер группы: 10, номер по списку: 8, мой любимый фильм: Терминатор 2");
        binding.infoTextView.append("\nНовое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));

        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long endTime = System.currentTimeMillis() + 20 * 10000;

                while(System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.buttonMirea2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы " +
                                "№ %s номер по списку № %d", numberThread, "БСБО-10-21", 8));

                        long endTime = System.currentTimeMillis() + 20 * 10000;

                        while(System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
            }
        });

    }
}