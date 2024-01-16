package com.example.tracksapp.Servicios;

import com.example.tracksapp.Models.Track;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TracksService {
    @GET("dsaApp/tracks")
    Call<ArrayList<Track>> getTracks();

    @Headers("Content-Type:application/json")
    @PUT("dsaApp/tracks")
    Call<Track> confTrack(@Body Track track);

    @Headers("Content-Type:application/json")
    @POST("dsaApp/tracks")
    Call<Track> addTrack(@Body Track track);

    @Headers("Content-Type:application/json")
    @DELETE("dsaApp/tracks/{id}")
    Call deleteTrack(@Path("id") String id);

}
