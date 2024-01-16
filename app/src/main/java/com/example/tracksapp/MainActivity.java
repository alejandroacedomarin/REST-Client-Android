package com.example.tracksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void verListaOnClick(View view){
        Intent intent =new Intent(this, ListaTracks.class);
        startActivity(intent);

    }
    public void añadirOnClick(View view){
        Intent intent =new Intent(this, ActivityAdd.class);
        startActivity(intent);

    }
    public void borrarOnClick(View view){
        Intent intent =new Intent(this, ActivityDelete.class);
        startActivity(intent);

    }
}