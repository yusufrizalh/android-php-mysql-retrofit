package id.inixindo.androidjavaretrofit.apis;

import id.inixindo.androidjavaretrofit.models.ModelResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
