package ru.mirea.drozdovei.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextText);
    }

    public void onClickCount(View view){
        String text = editText.getText().toString();
        int count = text.length();
        showToast("СТУДЕНТ № 8 ГРУППА БСБО-10-21 Количество символов - " + count);
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();

    }
}