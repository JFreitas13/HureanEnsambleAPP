package com.svalero.hureanensamble.api;

import com.svalero.hureanensamble.domain.Login;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Para definir las operaciones que queremos dar visibilidad en nuestro Aplicacion android provenientes de la API
 */

public interface HureanEnsambleApiInterface {


    /**
     * Login
     * @param login
     */
    @POST("/login")
    Call<User> login(@Body Login login);

    @GET("/songs")
    Call<List<Song>> getSongs();

}
