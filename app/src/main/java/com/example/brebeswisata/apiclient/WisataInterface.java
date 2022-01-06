package com.example.brebeswisata.apiclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WisataInterface {
    @GET("DB_wisata/")
    Call<List<Wisata>> getWisata();

    @POST("DB_wisata/")
    Call<Wisata> postCurhat(@Field("nama_wisata") String nama_wisata, @Field("deskripsi") String deskripsi, @Field("alamat") String alamat, @Field("harga_tiker") int harga_tiket, @Field("gambar") String gambar);

    @DELETE("DB_wisata/")
    Call<Wisata> delCurhat(@Query("id") int id);

}
