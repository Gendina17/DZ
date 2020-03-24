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

    private RecyclerView list_number;
    private Button button;
    public int element_count = 100;
    private ArrayList<Data> resurse = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter(resurse);
    private final String KEY="save";






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null ) {
            element_count = savedInstanceState.getInt(KEY);
        }
        resurse.clear();
        for (int j = 1; j <= element_count; j++)
            resurse.add(new Data(Integer.toString(j)));
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.but);
        list_number = view.findViewById(R.id.list);
        list_number.setAdapter(adapter);


        int col;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) col =4;
        else col = 3;
        list_number.setLayoutManager(new GridLayoutManager(view.getContext(), col));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                element_count++;
                resurse.add(new Data(Integer.toString(element_count)));
                adapter.notifyItemInserted(element_count);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY, element_count);
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
               holder.number_view.setText(data.get(position).number_txt);
            if (position % 2 == 0)
                holder.number_view.setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
            else
                holder.number_view.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView number_view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            number_view = itemView.findViewById(R.id.nomer);

            number_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = number_view.getText().toString();
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
        String number_txt;


        public Data(String number_txt) {
            this.number_txt = number_txt;

        }
    }


}