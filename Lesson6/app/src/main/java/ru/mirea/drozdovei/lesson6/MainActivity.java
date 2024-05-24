package ru.mirea.drozdovei.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

import ru.mirea.drozdovei.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);

        binding.editTextGroupNumber.setText(sharedPreferences.getString("GROUP", "Unknown"));

        binding.editTextListNumber.setText(String.format(Locale.getDefault(), "%d", sharedPreferences.getInt("NUMBER", 0)));

        binding.editTextFavFilm.setText(sharedPreferences.getString("FILM_TITLE", "Unknown"));

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("GROUP", binding.editTextGroupNumber.getText().toString());
                editor.putInt("NUMBER", Integer.parseInt(binding.editTextListNumber.getText().toString()));
                editor.putString("FILM_TITLE", binding.editTextFavFilm.getText().toString());

                editor.apply();
            }
        });
    }
}