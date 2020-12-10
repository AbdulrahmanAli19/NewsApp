package com.example.newsapp.ui.SecondActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.modules.Article;
import com.example.newsapp.pojo.modules.JournalDetails;
import com.example.newsapp.ui.webviewActivity.WebviewActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.time.Instant;


public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.HeadlinesViewHolder> {
    private static final String TAG = "HeadlinesAdapter";
    private Context mContext;
    private JournalDetails details;

    public HeadlinesAdapter(Context mContext, JournalDetails details) {
        this.mContext = mContext;
        this.details = details;
    }

    @NonNull
    @Override
    public HeadlinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.headline_layout, parent, false);
        HeadlinesViewHolder viewHolder = new HeadlinesViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final HeadlinesViewHolder holder, int position) {
        final Article currentArticle = details.getArticles().get(position);
        holder.txtNewsTitle.setText(currentArticle.getTitle());

        holder.txtNewsAuthor
                .setText("Author: " + ((currentArticle.getAuthor() == null)
                        ? currentArticle.getSource().getName() : currentArticle.getAuthor()));

        Instant instant = Instant.parse(currentArticle.getPublishedAt());
        java.util.Date date = java.util.Date.from(instant);

        holder.txtNewsDate.setText("Date: " + date.toString());


        if (currentArticle.getDescription() != null) {
            holder.txtNewsDescription.setText(currentArticle.getDescription());

        } else {
            holder.txtNewsDescription.setText(" ");
        }

        if (currentArticle.getUrlToImage() == null || !currentArticle.getUrlToImage().isEmpty()) {
            if (currentArticle.getUrlToImage() == null || currentArticle.getUrlToImage().equals("null")) {
                holder.imgNewsImage.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.GONE);
            } else {
                Picasso.get()
                        .load(currentArticle.getUrlToImage())
                        .into(holder.imgNewsImage, new Callback() {
                            @Override
                            public void onSuccess() {
                                holder.progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                holder.progressBar.setVisibility(View.GONE);
                                holder.txtError.setText(e.getMessage());
                            }
                        });
            }
        } else {
            holder.progressBar.setVisibility(View.GONE);
            holder.imgNewsImage.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebviewActivity.class);
                intent.putExtra("url", currentArticle.getUrl());
                intent.putExtra("name", currentArticle.getSource().getName());
                mContext.startActivity(intent);
            }
        });
    }

    /*private String formatDate()  {
        String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        TimeZone UTC;

        final SimpleDateFormat sdf = new SimpleDateFormat(ISO_8601_24H_FULL_FORMAT);
        sdf.setTimeZone(UTC);
        return formattedDate;
    }*/

    @Override
    public int getItemCount() {
        return details.getArticles().size();
    }

    class HeadlinesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNewsDescription, txtNewsTitle, txtNewsDate, txtNewsAuthor, txtError;
        ImageView imgNewsImage;
        ProgressBar progressBar;

        public HeadlinesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtError = itemView.findViewById(R.id.txtError);
            imgNewsImage = itemView.findViewById(R.id.imgNews);
            txtNewsAuthor = itemView.findViewById(R.id.txtNewsAuthor);
            txtNewsDate = itemView.findViewById(R.id.txtNewsData);
            txtNewsTitle = itemView.findViewById(R.id.txtNewsTitle);
            txtNewsDescription = itemView.findViewById(R.id.txtNewsDescription);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }
}
