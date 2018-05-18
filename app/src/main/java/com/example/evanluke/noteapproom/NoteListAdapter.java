package com.example.evanluke.noteapproom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by evanluke on 5/17/18.
 */



public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    // Define listener member variable
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onNoteClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnNoteClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemItemView;
        private final TextView itemAmountView;

        private NoteViewHolder(final View itemView) {
            super(itemView);
            itemItemView = itemView.findViewById(R.id.textView);
            itemAmountView = itemView.findViewById(R.id.numberView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onNoteClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Note> mNotes; // Cached copy of items

    NoteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.itemItemView.setText(current.getTitle());
           // holder.itemAmountView.setText(Integer.toString(current.getCount()));
        } else {
            // Covers the case of data not being ready yet.
            holder.itemItemView.setText("No Item");
        }
    }

    void setNotes(List<Note> items){
        mNotes = items;
        notifyDataSetChanged();
    }
    public int getId(int position) {
        Note note = mNotes.get(position);
        return note.getId();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }




}
