package com.gendina.hw;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class fragmentfirst extends Fragment {

    private RecyclerView a;
    private Button b;
    public static int i = 0;
    private ArrayList<Data> resurs = new ArrayList<>();
    private MyAd ad = new MyAd(resurs);

    public fragmentfirst() {
        for (int j = 1; j < i; j++)
            resurs.add(new Data(Integer.toString(j)));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_fragmentfirst, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("lflf", "jjjj");
        b = view.findViewById(R.id.but);
        a = view.findViewById(R.id.list);

        a.setAdapter(ad);


        int col;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) col =4;
        else col = 3;
        a.setLayoutManager(new GridLayoutManager(view.getContext(), col));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resurs.add(new Data(Integer.toString(i)));
                ad.notifyDataSetChanged();
                i++;
            }
        });
    }

    class MyAd extends RecyclerView.Adapter<MyHo> {
        private ArrayList<Data> Darr;

        public MyAd(ArrayList Darr) {
            this.Darr = Darr;
        }

        @NonNull
        @Override
        public MyHo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.partlist, parent, false);
            MyHo hol = new MyHo(v);
            return hol;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHo holder, int position) {
            holder.b1.setText(Darr.get(position).a1);
            if (position % 2 == 0)
                holder.b1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
            else
                holder.b1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));

        }

        @Override
        public int getItemCount() {
            return Darr.size();
        }
    }

    class MyHo extends RecyclerView.ViewHolder {
        TextView b1;

        public MyHo(@NonNull View itemView) {
            super(itemView);

            b1 = itemView.findViewById(R.id.nomer);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = b1.getText().toString();
                    Fragment fragmen = new nomer(txt);



                    FragmentManager fn = getFragmentManager();
                    FragmentTransaction ft =fn.beginTransaction();

                    ft.replace(R.id.fr, fragmen);

                    ft.addToBackStack(null);
                    ft.commit();

                }
            });


        }
    }


    class Data {
        String a1;


        public Data(String a1) {
            this.a1 = a1;

        }
    }


}