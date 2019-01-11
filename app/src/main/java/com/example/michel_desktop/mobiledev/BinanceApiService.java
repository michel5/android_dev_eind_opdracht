package com.example.michel_desktop.mobiledev;

import com.example.michel_desktop.mobiledev.models.BalanceModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BinanceApiService {
    String BASE_URL = "http:136.144.180.47:8069/";
    /**
     * Create a retrofit client.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/api/balance")
    Call<List<BalanceModel>> getBalance();
}