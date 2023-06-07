package id.inixindo.androidjavaretrofit.apis;

import id.inixindo.androidjavaretrofit.models.ModelResponse;
import id.inixindo.androidjavaretrofit.models.ModelResponseUsers;
import id.inixindo.androidjavaretrofit.models.ModelUsers;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequestData {
    @GET("view.php")
    Call<ModelResponse> getAllData();

    @FormUrlEncoded
    @POST("insert.php")
    Call<ModelResponse> insertData(
            @Field("nama") String nama,
            @Field("nomor") String nomor,
            @Field("sponsor") String sponsor,
            @Field("negara") String negara
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> updateData(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("nomor") String nomor,
            @Field("sponsor") String sponsor,
            @Field("negara") String negara
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> deleteData(
            @Field("id") int id
    );

    // =====================================================================================================

    @GET("getAllUsers.php")
    Call<ModelResponseUsers> getAllUsers();

    @GET("getUserById")
    Call<ModelResponseUsers> getUserById(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("insertUser.php")
    Call<ModelResponseUsers> insertUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("updateUser.php")
    Call<ModelResponseUsers> updateUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<ModelResponseUsers> deleteUser(
            @Field("id") String id
    );
}
