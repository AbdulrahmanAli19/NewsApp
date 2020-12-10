package com.example.newsapp.ui.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.modules.Journals;
import com.example.newsapp.pojo.modules.Source;
import com.example.newsapp.ui.SecondActivity.SecondActivity;

public class JournalsAdapter  extends RecyclerView.Adapter<JournalsAdapter.JournalViewHolder> {

    private Context mContext;
    private Journals journals;

    public JournalsAdapter(Context mContext, Journals journals) {
        this.mContext = mContext;
        this.journals = journals;
    }

    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.news_layout, parent, false);
        JournalViewHolder viewHolder = new JournalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {
        final Source currentSource = journals.getSources().get(position);
        holder.tvJournalName.setText(currentSource.getName());
        holder.tvJournalDesc.setText(currentSource.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SecondActivity.class);
                intent.putExtra("id", currentSource.getId());
                intent.putExtra("name", currentSource.getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return journals.getSources().size();
    }

    class JournalViewHolder extends RecyclerView.ViewHolder {
        TextView tvJournalName, tvJournalDesc ;
        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJournalName = itemView.findViewById(R.id.txtJournalName);
            tvJournalDesc = itemView.findViewById(R.id.txtJournalDescription);
        }
    }
}
