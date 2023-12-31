package com.company.harmonytunes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    ArrayList<String> list;
    Context mContext;

    public MusicAdapter(ArrayList<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music,parent,false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final String filePath=list.get(position);
        Log.e("filePath : ",filePath);
        final String title= filePath.substring(filePath.lastIndexOf("/")+1);
        holder.textViewFileName.setText(title);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, MusicActivity.class);
                i.putExtra("title",title);
                i.putExtra("filepath",filePath);
                i.putExtra("position",position);
                i.putExtra("list",list);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewFileName;
        private CardView cardView;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFileName=itemView.findViewById(R.id.textViewFileName);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
