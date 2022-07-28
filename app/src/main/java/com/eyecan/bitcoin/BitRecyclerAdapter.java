package com.eyecan.bitcoin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BitRecyclerAdapter extends RecyclerView.Adapter<BitRecyclerAdapter.MyViewHolder> {
 ArrayList<Coins> cointlist;
    BitRecyclerAdapter (ArrayList<Coins> list){
        cointlist=list;

    }

    @NonNull
    @Override
    public BitRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.coinname_list_item,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull BitRecyclerAdapter.MyViewHolder holder, int position) {

        String name = cointlist.get(position).getName();
        String symbol = cointlist.get(position).getSymbol();
        String price = cointlist.get(position).getPrice();

        holder.name.setText(name);
        holder.symbol.setText(symbol);
        holder.price.setText(price);
        holder.coinlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( holder.coinlinear.getContext(),coindetail.class);
                i.putExtra("symbol",symbol);
                holder.coinlinear.getContext().startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return cointlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, symbol, price;
        LinearLayout coinlinear;

        public MyViewHolder(@NonNull View itemView) {
             super(itemView);
            name= itemView.findViewById(R.id.name_text);
            symbol= itemView.findViewById(R.id.symbol_text);
            price= itemView.findViewById(R.id.price_text);
            coinlinear = itemView.findViewById(R.id.listLinear);


        }
    }
}
