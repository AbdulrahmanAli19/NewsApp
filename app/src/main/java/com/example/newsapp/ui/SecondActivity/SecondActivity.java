package com.example.newsapp.ui.SecondActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.data.retrofit.NewsApi;
import com.example.newsapp.pojo.modules.JournalDetails;
import com.example.newsapp.pojo.modules.Source2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    public final static String BASE_URL = "https://newsapi.org/v2/";
    public final static String API_KEY = "7c7c933d3d5c4c559b9f6438f5447458";
    private final Context mContext = SecondActivity.this;
    private RecyclerView recyclerView;
    private HeadlinesAdapter adapter;
    private TextView txtError;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        Source2 source2 = handelNullException(name, id);
        name = source2.getName();
        id = source2.getId();
        recyclerView = findViewById(R.id.recyclerView);
        txtError = findViewById(R.id.txtError);
        progressBar = findViewById(R.id.progressbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(name);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        Call<JournalDetails> call = newsApi.getTopHeadLines(id, API_KEY);
        Log.d(TAG, "onCreate: id = " + id);

        call.enqueue(new Callback<JournalDetails>() {
            @Override
            public void onResponse(Call<JournalDetails> call, Response<JournalDetails> response) {
                if (!response.isSuccessful()) {
                    txtError.setText(String.valueOf(response.code()));
                    progressBar.setVisibility(View.GONE);
                }
                JournalDetails details = response.body();
                if (details.getArticles().size() != 0) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    adapter = new HeadlinesAdapter(mContext, details);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JournalDetails> call, Throwable t) {
                txtError.setText(t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private Source2 handelNullException(String name, String id) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref", MODE_PRIVATE);
        if (name == null || id == null) {
            name = sharedPreferences.getString("name", "");
            id = sharedPreferences.getString("id", "");
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("id", id);
            editor.apply();

        }
        Source2 source = new Source2();
        source.setmId(id);
        source.setmName(name);
        return source;
    }

}