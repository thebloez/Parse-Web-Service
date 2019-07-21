package com.netzme.test.controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.netzme.test.model.RandomUser;
import com.netzme.test.model.random.RandomResponse;
import com.netzme.test.model.random.Result;
import com.netzme.test.repository.ClientRepos;
import okhttp3.OkHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PersonController {

    private static final String BASE_URI = "https://randomuser.me";

    @GetMapping("/person")
    public String getPerson(){
        RandomResponse rest = new RandomResponse();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ClientRepos clientRepos = retrofit.create(ClientRepos.class);
        Call<RandomResponse> callSync = clientRepos.getUser();

        try{
            retrofit2.Response<RandomResponse> response = callSync.execute();
            rest = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Result> resultList = rest.getResults();

        Map<String, Object> map = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse((Reader) resultList);

            JSONArray jsonArray = (JSONArray) obj;

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()){
                JSONObject jsonObject = iterator.next();
                String gender = (String) jsonObject.get("gender");
                Object name = (Object) jsonObject.get("name");
                map.put(gender, name);
            }

        } catch (Exception e){
            e.getStackTrace();
        }


        return (String) map.get("gender");
//        RandomUser randomUser = new RandomUser("male","Ryan Safary Hidayat",
//                "Kuningan","kampret");
//
//        return rest;
    }
}
