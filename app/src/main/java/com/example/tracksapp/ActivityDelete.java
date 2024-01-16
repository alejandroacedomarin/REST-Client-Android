package com.example.tracksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracksapp.Models.Track;
import com.example.tracksapp.Servicios.TracksService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityDelete extends AppCompatActivity {
    TextView id;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        id = (TextView) findViewById(R.id.editTextText);

    }
    public void deleteOnClick(View view) {


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




        Call call = p.deleteTrack(id.getText().toString());


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {

                    Toast.makeText(ActivityDelete.this, "Configurado correctamente.", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(ActivityDelete.this, "Error, response is not as expected", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Retrofit", "Error: " + t.getMessage()); // Log the error message
                Toast.makeText(ActivityDelete.this, "Configurado correctamente.", Toast.LENGTH_SHORT).show();

            }


        });
    }
}