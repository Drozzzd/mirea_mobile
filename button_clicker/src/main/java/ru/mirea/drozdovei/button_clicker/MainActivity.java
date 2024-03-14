package ru.mirea.drozdovei.button_clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textShowInfo;
    private Button btnWhoAmI;
    private Button btnItsNotMe;
    private CheckBox chbChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textShowInfo = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItsNotMe = findViewById(R.id.btnItIsNotMe);
        chbChange = findViewById(R.id.chbChange);


        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShowInfo.setText("Мой номер по списку № 8");
                chbChange.setChecked(!chbChange.isChecked());
            }
        };

        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }

    public void onMyButtonClick(View view) {
        textShowInfo.setText("Это не я сделал");
        chbChange.setChecked(!chbChange.isChecked());
    }
}
