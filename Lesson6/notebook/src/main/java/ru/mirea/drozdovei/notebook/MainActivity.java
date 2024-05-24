package ru.mirea.drozdovei.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.mirea.drozdovei.notebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final int REQUEST_CODE_PERMISSION = 200;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!isExternalStorageReadable() || !isExternalStorageWritable()) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }

        binding.buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFileFromExternalStorage();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToExternalStorage();
            }
        });
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private void writeToExternalStorage() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String filename = binding.editTextFileName.getText().toString() + ".txt";
        File file = new File(path, filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
            output.write(binding.editTextQuote.getText().toString());
            output.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "ErrorWriting" + file, e);
        }
    }

    private void readFileFromExternalStorage() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String filename = binding.editTextFileName.getText().toString() + ".txt";
        File file = new File(path, filename);
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                    StandardCharsets.UTF_8);
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            String resultLine = String.join("\n", lines);
            binding.editTextQuote.setText(resultLine);
            Log.w("ExternalStorage", String.format(Locale.getDefault(), "Read from file " +
                    "%s successfull", lines.toString()));
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format(Locale.getDefault(), "Read from file " +
                    "%s failed", e.getMessage()));
        }
    }
}