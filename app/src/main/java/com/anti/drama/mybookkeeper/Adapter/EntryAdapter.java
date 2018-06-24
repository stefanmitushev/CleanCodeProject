package com.anti.drama.mybookkeeper.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anti.drama.mybookkeeper.Activities.Entry.SingleEntryActivity;
import com.anti.drama.mybookkeeper.App.EntryClickListener;
import com.anti.drama.mybookkeeper.Models.Entry;
import com.anti.drama.mybookkeeper.R;

import java.util.ArrayList;
import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder>{

    private List<Entry> entries;

    private EntryClickListener entryClickListener;



    public EntryAdapter(){
        this.entries = new ArrayList<Entry>();
    }
    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntries(List<Entry> entries) {
        int size = this.entries.size();
        this.entries.addAll(entries);
        notifyItemInserted(size);
    }
    public void addEntry(Entry model){
        if(this.entries == null){
            this.entries = new ArrayList<>();
        }
        this.entries.add(model);
        notifyItemInserted(entries.size() -1);
    }

    public void setEntryClickListener(EntryClickListener entryClickListener){
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public EntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.entry_card, viewGroup, false);
        return new EntryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EntryAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.entryDescription.setText(entries.get(i).getDescription());
        viewHolder.entryComment.setText(entries.get(i).getComment());
        viewHolder.entryValue.setText(entries.get(i).getValue().toString());
        viewHolder.entryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entryClickListener.onClick(entries.get(i).getId());
            }
        });
        if(!entries.get(i).getType()){
            viewHolder.entryValue.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView entryComment;
        public TextView entryDescription;
        public TextView entryValue;
        public CardView entryCard;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            entryComment = (TextView) itemView.findViewById(R.id.entry_comment);
            entryDescription = (TextView) itemView.findViewById(R.id.entry_description);
            entryValue = (TextView) itemView.findViewById(R.id.entry_value);
            entryCard = (CardView) itemView.findViewById(R.id.entry_card_view);
        }
    }
}
