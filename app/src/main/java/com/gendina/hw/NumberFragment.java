package com.gendina.hw;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NumberFragment extends Fragment {

    String txt;

    public NumberFragment(String txt) {
        this.txt = txt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number, container, false);
        TextView text;
        text = view.findViewById(R.id.txt);
        text.setText(txt);
        if (Integer.parseInt(txt) % 2 == 0)
            text.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        else
            text.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
        return  view;
    }



}