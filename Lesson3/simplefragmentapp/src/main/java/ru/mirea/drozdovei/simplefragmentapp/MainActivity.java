package ru.mirea.drozdovei.simplefragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Fragment firstFragment, secondFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
    }

    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager();
        if(view.getId() == R.id.btnFirstFragment) {
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, firstFragment).commit();
        }
        else if(view.getId() == R.id.btnSecondFragment) {
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, secondFragment).commit();
        }
    }
}