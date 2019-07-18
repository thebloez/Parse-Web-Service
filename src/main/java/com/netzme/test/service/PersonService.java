package com.netzme.test.service;

import com.netzme.test.repository.ClientRepos;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class PersonService {

    private ClientRepos clientRepos;

    public PersonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        clientRepos = retrofit.create(ClientRepos.class);
    }
}