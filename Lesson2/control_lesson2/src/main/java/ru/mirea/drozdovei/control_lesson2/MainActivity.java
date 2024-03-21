package ru.mirea.drozdovei.control_lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.time.Month;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String showTextInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowTimeDialog(View view) {
        MyTimeDialogFragment timeDialog = new MyTimeDialogFragment(this, (view1, hourOfDay, minute) -> {
            showTextInfo = String.format(Locale.getDefault(),
                    "Hours: %d | Minutes: %d", hourOfDay, minute);
            useSnackbar(view, showTextInfo);
        }, 20, 12, true);
        timeDialog.show();
        useSnackbar(view, "Time Picker Opened");
    }

    public void onClickShowDateDialog(View view) {
        MyDateDialogFragment dateDialog = new MyDateDialogFragment(this, (view1, year, month, dayOfMonth) -> {
            showTextInfo = String.format(Locale.getDefault(),
                    "Year: %d | Month: %s | Day: %d", year, Month.of(month + 1), dayOfMonth);
            useSnackbar(view, showTextInfo);
        }, 2023, 10, 28);
        dateDialog.show();
        useSnackbar(view, "Date Picker Opened");
    }

    public void onClickShowProgressDialog(View view) {
        MyProgressDialogFragment progressDialog = new MyProgressDialogFragment(this);
        progressDialog.setTitle("Progress Dialog");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        useSnackbar(view, "Progress Dialog Opened");
    }

    private void useSnackbar(View view, String showTextInfo) {
        Snackbar.make(view, showTextInfo, Snackbar.LENGTH_LONG).show();
    }
}