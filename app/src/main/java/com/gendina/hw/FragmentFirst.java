package com.gendina.hw;

import android.content.res.Configuration;
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


public class FragmentFirst extends Fragment {

    private RecyclerView listNumber;
    private Button button;
    public int elementCount = 100;
    private ArrayList<Data> resurse = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter(resurse);
    private final String KEY="save";






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null ) {
            elementCount = savedInstanceState.getInt(KEY);
        }
        resurse.clear();
        for (int j = 1; j <= elementCount; j++)
            resurse.add(new Data(Integer.toString(j)));
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.but);
        listNumber = view.findViewById(R.id.list);
        listNumber.setAdapter(adapter);


        int col;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) col =4;
        else col = 3;
        listNumber.setLayoutManager(new GridLayoutManager(view.getContext(), col));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementCount++;
                resurse.add(new Data(Integer.toString(elementCount)));
                adapter.notifyItemInserted(elementCount);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY, elementCount);
        super.onSaveInstanceState(outState);
    }


    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private ArrayList<Data> data;

        public MyAdapter(ArrayList data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partlist, parent, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
               holder.numberView.setText(data.get(position).numberTxt);
            if (position % 2 == 0)
                holder.numberView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
            else
                holder.numberView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView numberView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            numberView = itemView.findViewById(R.id.nomer);

            numberView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = numberView.getText().toString();
                    Fragment fragmen = new NumberFragment(txt);



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
        String numberTxt;


        public Data(String numberTxt) {
            this.numberTxt = numberTxt;

        }
    }


}