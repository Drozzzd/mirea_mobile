package ru.mirea.drozdovei.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import ru.mirea.drozdovei.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String TAG = this.getClass().getSimpleName();
    private String fileName = "memorable_history_date.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                binding.editTextMultiLine.post(new Runnable() {
                    public void run() {
                        binding.editTextMultiLine.setText(getTextFromFile());
                    }
                });
            }
        }).start();

        //binding.editTextMultiLine.setText(getTextFromFile());

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String result = binding.editTextMultiLine.getText().toString();
                    outputStream.write(result.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getTextFromFile() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(fileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(TAG, text);
            return text;
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if(fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }
}