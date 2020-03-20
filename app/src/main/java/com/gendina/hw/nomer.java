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


public class nomer extends Fragment {

    String txt;

    public nomer(String txt) {
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
        View v = inflater.inflate(R.layout.fragment_nomer, container, false);
        TextView asd;
        asd = v.findViewById(R.id.txt);
        asd.setText(txt);
        if (Integer.parseInt(txt) % 2 == 0)
            asd.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        else
            asd.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
        return  v;
    }

   /* static nomer newFragment(){
        nomer fragment = new nomer();
        return fragment;
    } */

}