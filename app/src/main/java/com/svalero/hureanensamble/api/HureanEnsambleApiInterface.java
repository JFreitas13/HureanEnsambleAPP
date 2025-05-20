package com.svalero.hureanensamble.api;

import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Login;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.Product;
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

    //product
    @GET("/products")
    Call<List<Product>> getProducts();

    @POST("/products")
    Call<Product> addProduct(@Body Product product);

    @DELETE("/products/{id}")
    Call<Void> deleteProduct(@Path("id") long id);

    @PUT("/products/{id}")
    Call<Product> modifyProduct(@Path("id") long id, @Body Product product);

    //playlist
    @GET("/playlists")
    Call<List<Playlist>> getPlaylists();

    @POST("/playlists")
    Call<Playlist> addPlaylist(@Body Playlist playlist);

    @DELETE("/playlists/{id}")
    Call<Void> deletePlaylist(@Path("id") long id);

    @PUT("/playlists/{id}")
    Call<Playlist> modifyPlaylist(@Path("id") long id, @Body Playlist playlist);

    @GET("playlists/{id}")
    Call<Playlist> getPlaylist(@Path("id") long id);

    @POST("playlists/{playlistId}/songs/{songId}")
    Call<Playlist> addSongToPlaylist(@Path("playlistId") long playlistId, @Path("songId") long songId);

    @DELETE("playlists/{playlistId}/songs/{songId}")
    Call<Void> deleteSongFromPlaylist(@Path("playlistId") long playlistId, @Path("songId") long songId);

    @GET("/users/{id}/playlists")
    Call<List<Playlist>> getPlaylistByUserId(@Path("id") long userId);

    //user
    @GET("/users")
    Call<List<User>> getUsers();

    @POST("/users")
    Call<User> addUser(@Body User user);

    @DELETE("/users/{id}")
    Call<Void> deleteUser(@Path("id") long id);

    @PUT("/users/{id}")
    Call<User> modifyUser(@Path("id") long id, @Body User user);

    //event
    @GET("/events")
    Call<List<Event>> getEvents();

    @POST("/events")
    Call<Event> addEvent(@Body Event event);

    @DELETE("/events/{id}")
    Call<Void> deleteEvent(@Path("id") long id);

    @PUT("/events/{id}")
    Call<Event> modifyEvent(@Path("id") long id, @Body Event event);

    @GET("/users/{id}/events")
    Call<List<Event>> getEventsByUserId(@Path("id") long userId);

}
