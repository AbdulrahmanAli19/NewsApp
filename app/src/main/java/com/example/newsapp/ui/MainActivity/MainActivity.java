package com.example.newsapp.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.data.retrofit.NewsApi;
import com.example.newsapp.pojo.modules.Journals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public final static String BASE_URL = "https://newsapi.org/v2/";
    public final static String API_KEY = "7c7c933d3d5c4c559b9f6438f5447458";
    private final Context mContext = MainActivity.this;
    private RecyclerView recyclerView;
    private JournalsAdapter adapter;
    private TextView txtError;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        txtError = findViewById(R.id.txtError);
        progressBar = findViewById(R.id.progressbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News App");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        Call<Journals> call = newsApi.getAllNews(API_KEY);

        call.enqueue(new Callback<Journals>() {
            @Override
            public void onResponse(Call<Journals> call, Response<Journals> response) {
                if (!response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    txtError.setText(String.valueOf(response.code()));
                    return;
                }
                Journals journals = response.body();
                if (journals.getSources().size() != 0){
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    adapter = new JournalsAdapter(mContext, journals);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Journals> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                txtError.setText(t.getMessage());
            }
        });

    }
}