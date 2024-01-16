package com.example.tracksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracksapp.Models.Track;
import com.example.tracksapp.Servicios.TracksService;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityConfTrack extends AppCompatActivity {

    TextView id;
    TextView s ;
    TextView t ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_track);
        id = (TextView) findViewById(R.id.idd);
        s = (TextView) findViewById(R.id.sing_edit);
        t= (TextView) findViewById(R.id.title_edit);

        Bundle miBundle = this.getIntent().getExtras();
        if(miBundle!=null){
            String idd = miBundle.getString("id");
            id.setText(idd);

            String ta = miBundle.getString("t");
            t.setText(ta);

            String si = miBundle.getString("s");
            s.setText(si);



        }
    }
    public void aplicarOnClick(View view) {


        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        TracksService p = retrofit.create(TracksService.class);




        Call<Track> call = p.confTrack(new Track(id.getText().toString(),t.getText().toString(),s.getText().toString()));

        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(ActivityConfTrack.this, "Configurado correctamente.", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(ActivityConfTrack.this, "Error, response is not as expected", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage()); // Log the error message
                Toast.makeText(ActivityConfTrack.this, "Configurado correctamente.", Toast.LENGTH_SHORT).show();


            }
        });
    }
}