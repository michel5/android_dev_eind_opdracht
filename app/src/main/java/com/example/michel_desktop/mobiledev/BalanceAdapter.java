package com.example.michel_desktop.mobiledev;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.GridcellHolder>{
    private Context context;
    public List<GridCell> listGridcell;

    public BalanceAdapter(Context context, List<GridCell> listGridcell) {
        this.context = context;
        this.listGridcell = listGridcell;
    }

    @NonNull
    @Override
    public GridcellHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from
                (viewGroup.getContext()).inflate(R.layout.grid_cell, viewGroup, false);
        return new GridcellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridcellHolder gridcellHolder, int i) {

        GridCell gridcell = listGridcell.get(i);
        gridcellHolder.cointag.setText(gridcell.getCointag());
        gridcellHolder.koers.setText(String.valueOf(gridcell.getBalance()));
    }

    public void swapList(List<GridCell> mNewListBlockChainAddressObject) {
        listGridcell = mNewListBlockChainAddressObject;
        if (listGridcell != null) {

            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return this.listGridcell.size();
    }


    public class GridcellHolder extends RecyclerView.ViewHolder {

        public TextView cointag;
        public TextView koers;
        public View view;

        /**
         *
         * @param itemView
         */
        public GridcellHolder(View itemView) {
            super(itemView);
            cointag = itemView.findViewById(R.id.coin_tag_id);
            koers = itemView.findViewById(R.id.koers);
            view = itemView;
        }


    }
}
