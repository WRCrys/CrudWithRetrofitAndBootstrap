package com.crystyanoalmeida.crudretrofit.Helper;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by cryst on 19/01/2018.
 */

public class AppConfig {


    public interface insert{
        @FormUrlEncoded
        @POST("/retrofitcrud/insertData.php")
        void insertData(@Field("name") String name,
                        @Field("age") String age,
                        @Field("mobile") String mobile,
                        @Field("email") String email, Callback<Response> callback);
    }

    public interface read{
        @GET("/retrofitcrud/displayAll.php")
        void readData(Callback<JsonElement> callback);
    }

    public interface delete{
        @FormUrlEncoded
        @POST("/retrofitcrud/deleteData.php")
        void deleteData(@Field("id") String id,
                        Callback<Response> callback);
    }

    public interface update{
        @FormUrlEncoded
        @POST("/retrofitcrud/updateData.php")
        void updateData(@Field("id") String id,
                        @Field("name") String name,
                        @Field("age") String age,
                        @Field("mobile") String mobile,
                        @Field("email") String email,
                        Callback<Response> callback);
    }
}