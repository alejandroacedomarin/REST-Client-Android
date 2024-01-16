package com.example.tracksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracksapp.Adaptadores.AdapterTracks;
import com.example.tracksapp.Models.Track;
import com.example.tracksapp.Servicios.TracksService;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaTracks extends AppCompatActivity {

    private ArrayList<Track> listTracks;
    private AdapterTracks adaptador;
    private RecyclerView recyclerProd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tracks);

        listTracks=new ArrayList<>();
        recyclerProd=(RecyclerView) findViewById(R.id.recyclerId);
        recyclerProd.setLayoutManager(new LinearLayoutManager(this));


        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        TracksService tracks = retrofit.create(TracksService.class);
        Call<ArrayList<Track>> call = tracks.getTracks();
        call.enqueue(new Callback<ArrayList<Track>>() {
            @Override
            public void onResponse(Call<ArrayList<Track>> call, Response<ArrayList<Track>> response) {
                if (response.isSuccessful()){
                    ArrayList<Track> listTracks = response.body();

                    AdapterTracks adapter=new AdapterTracks(listTracks,ListaTracks.this);

                    recyclerProd.setAdapter(adapter);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle miBundle=new Bundle();
                            miBundle.putString("s",listTracks.get(recyclerProd.getChildAdapterPosition(view)).getSinger());
                            miBundle.putString("id",listTracks.get(recyclerProd.getChildAdapterPosition(view)).getId());
                            miBundle.putString("t",listTracks.get(recyclerProd.getChildAdapterPosition(view)).getTitle());

                            Intent miIntentq = new Intent(ListaTracks.this, ActivityConfTrack.class);
                            miIntentq.putExtras(miBundle);

                            startActivity(miIntentq);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Track>> call, Throwable t) {
                Toast.makeText(ListaTracks.this,"error",Toast.LENGTH_SHORT).show();

            }
        });



    }
}