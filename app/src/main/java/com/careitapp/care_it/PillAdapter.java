package com.careitapp.care_it;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.MyViewHolder> {

    private final List<Pill> pillList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pill_name) TextView pillName;
        @BindView(R.id.pill_timeline) TextView pillTimeline;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    PillAdapter(List<Pill> pillList, Context mContext) {
        this.pillList = pillList;
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pillView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pill_model, parent, false);

        return new MyViewHolder(pillView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Pill pill = pillList.get(position);
        Log.d("onDataChange", "in view holder: " + pill.getPillName());
        holder.pillName.setText(pill.getPillName());
        holder.pillTimeline.setText("Take " + pill.getPillsPerSession() + " pills, " + pill.getSessionsPerDay() + " times a day.");

        holder.itemView.setOnClickListener(v -> {
            pillList.remove(position);
        });

    }

    @Override
    public int getItemCount() {
        return pillList.size();
    }
}