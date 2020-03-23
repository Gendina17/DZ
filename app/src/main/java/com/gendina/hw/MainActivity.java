package com.gendina.hw;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private static final int FINAL_COUNT=101;
private static final String KEY="save";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            FragmentFirst.element_count = FINAL_COUNT;
            FragmentFirst first = new FragmentFirst();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fr, first)
                    .commit();
        }
        else {
            FragmentFirst.element_count = savedInstanceState.getInt(KEY);

        }

    }

   @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY, FragmentFirst.element_count);
        super.onSaveInstanceState(outState);
    }

}