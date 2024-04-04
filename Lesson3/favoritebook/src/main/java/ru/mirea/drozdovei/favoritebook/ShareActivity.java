package ru.mirea.drozdovei.favoritebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        textView = findViewById(R.id.textViewShareActivity);
        editText = findViewById(R.id.editTextShareActivity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String input = extras.getString(MainActivity.KEY);
            textView.setText(String.format("Любимая книга разработчика – %s", input));
        }
    }

    public void onClickSendData(View view) {
        Intent data = new Intent();
        if(editText.getText().length() > 0) {
            String text = editText.getText().toString();
            data.putExtra(MainActivity.USER_MESSAGE, text);
            setResult(Activity.RESULT_OK, data);
        }
        else {
            setResult(Activity.RESULT_CANCELED, data);
        }
        finish();
    }
}