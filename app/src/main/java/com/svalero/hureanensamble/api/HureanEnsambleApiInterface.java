package com.svalero.hureanensamble.api;

import com.svalero.hureanensamble.domain.Login;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Para definir las operaciones que queremos dar visibilidad en nuestro Aplicacion android provenientes de la API
 */

public interface HureanEnsambleApiInterface {

    //login
    @POST("/login")
    Call<User> login(@Body Login login);

    //songs
    @GET("/songs")
    Call<List<Song>> getSongs();

    @POST("/songs")
    Call<Song> addSong(@Body Song song);

    @DELETE("/songs/{id}")
    Call<Void> deleteSong(@Path("id") long id);

    @PUT("/songs/{id}")
    Call<Song> modifySong(@Path("id") long id, @Body Song song);
}
