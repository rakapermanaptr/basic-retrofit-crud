package com.example.rakapermanaputra.datamahasiswa.network;


import com.example.rakapermanaputra.datamahasiswa.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

//    //GET
//    @FormUrlEncoded
    @GET("data")
    Call<List<Data>> getData();

    //POST
    @FormUrlEncoded
    @POST("/data")
    Call<Data> postData(
            @Field("nama") String nama,
            @Field("npm") String npm,
            @Field("kelas") String kelas,
            @Field("email") String email
    );

    //UPDATE / PUT
    @FormUrlEncoded
    @PUT("/data/{id}")
    Call<Data> updateData(
            @Path("id") String id,
            @Field("nama") String nama,
            @Field("npm") String npm,
            @Field("kelas") String kelas,
            @Field("email") String email );

    //DELETE
    @DELETE("/data/{id}")
    Call<Data> deleteData(
            @Path("id") String id );

}
