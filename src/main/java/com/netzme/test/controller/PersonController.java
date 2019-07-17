package com.netzme.test.controller;

import com.netzme.test.model.RandomUser;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class PersonController {

    private static final String BASE_URI = "https://randomuser.me/api/";

    @GetMapping("/person")
    public RandomUser getPerson(){
        Object result = new Object();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        RandomUser randomUser = new RandomUser("Male","Ryan Safary Hidayat",
                "Kuningan","kampret");

        return randomUser;
    }
}
