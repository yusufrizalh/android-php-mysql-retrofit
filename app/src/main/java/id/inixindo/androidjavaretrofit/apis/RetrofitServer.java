package id.inixindo.androidjavaretrofit.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String baseURL = "http://192.168.99.2/android-api/";
    private static Retrofit retrofit;

    public static Retrofit connectRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
