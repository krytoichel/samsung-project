package com.example.myapplication644;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TradeInfoAdapter extends RecyclerView.Adapter<TradeInfoAdapter.ViewHolder> {
    static int i;
    interface OnTradeClickListener{
        void onTradeClick(TradeInfo tradeInfo, int position);
    }

    private final OnTradeClickListener onClickListener;
    private List<TradeInfo> tradeInfos;


    public TradeInfoAdapter(List<TradeInfo> tradeInfos, OnTradeClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.tradeInfos = tradeInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TradeInfo tradeInfo = tradeInfos.get(position);
        holder.name_trade.setText(tradeInfo.getName_trade());
        holder.feature_acc.setText(tradeInfo.getFeature_acc());
        holder.min_request.setText(tradeInfo.getMin_request());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onTradeClick(tradeInfo, position);
            }
        });
        i = position;
    }

    @Override
    public int getItemCount() {
        return tradeInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView feature_acc, name_trade, min_request;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feature_acc = itemView.findViewById(R.id.textView22);
            name_trade = itemView.findViewById(R.id.textView20);
            min_request = itemView.findViewById(R.id.textView24);
        }
    }
}
