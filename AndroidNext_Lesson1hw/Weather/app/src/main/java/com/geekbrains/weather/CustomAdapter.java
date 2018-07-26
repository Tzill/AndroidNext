package com.geekbrains.weather;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by shkryaba on 07/07/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<String> cityList = new ArrayList<>();
    private CreateActionFragment.OnHeadlineSelectedListener mCallback;
    private Context context;
    private ArrayList<String> selectedCities = new ArrayList<>();
    private Boolean isRemoove = false;

    public CustomAdapter(Context context, ArrayList<String> cityList, CreateActionFragment.OnHeadlineSelectedListener mCallback) {
        this.cityList = cityList;
        this.mCallback = mCallback;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(cityList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectedCities.size(); i++) {
                    if (selectedCities.get(i).equals(cityList.get(position))) {
                        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                        selectedCities.remove(selectedCities.get(i));
                        mCallback.onArticleSelected(selectedCities);
                        isRemoove = true;
                    }
                }
                if (!isRemoove) {
                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.blue));
                    selectedCities.add(cityList.get(position));
                    mCallback.onArticleSelected(selectedCities);
                } else {
                    isRemoove = false;
                }
            }
        });

        if (cityList.get(position).equals("Moscow")) {
            holder.cardView.setCardBackgroundColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setMoscowRed() {
        cityList.add("Tula");
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private LinearLayout linearLayout;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearlayout_item);
            textView = itemView.findViewById(R.id.textview_item);
            cardView = itemView.findViewById(R.id.card_view);

        }
    }
}
