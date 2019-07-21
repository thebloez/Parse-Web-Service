package com.netzme.test.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netzme.test.repository.ClientRepos;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private String TAG = "[" + this.getClass().getSimpleName() +" ]";

    private ClientRepos clientRepos;


    public PersonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        clientRepos = retrofit.create(ClientRepos.class);
    }

    public void mappingResponse(JSONObject responseTemplate, JSONObject responseFromBiller,
                                LinkedHashMap<String, Object> mapResponseToCore,
                                HashMap<String, Object> complementMap) {

        boolean errFlag = false;
        mapResponseToCore.put("PURCHASE_STATUS_DESC", "");
        mapResponseToCore.put("REFERENCE_NUMBER", "");
        try
        {
            if (responseFromBiller.get("error").equals("true"))
            {
                mapResponseToCore.put("PURCHASE_STATUS", responseFromBiller.get("errorNum"));
                mapResponseToCore.put("PURCHASE_STATUS_DESC", responseFromBiller.get("message"));
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }


        Iterator<?> keysResponseTemplate = responseTemplate.keys();
        while (keysResponseTemplate.hasNext()) {
            String key = (String) keysResponseTemplate.next();
            if (responseTemplate.get(key) instanceof JSONObject) {
                mappingResponse((JSONObject) responseTemplate.get(key), responseFromBiller, mapResponseToCore, complementMap);
            } else {
                changeResponse(key, responseTemplate.get(key).toString(), responseFromBiller, mapResponseToCore,
                        responseTemplate);
            }
        }
    }

    public void changeResponse(String keyValue, String value, JSONObject responseFromBiller,
                               LinkedHashMap<String, Object> mapResponseToCore, JSONObject responseTemplate) {

        Iterator<?> keysResponseFromBiller = responseFromBiller.keys();
        try
        {
            logger.debug ("Response From Biller : " + responseFromBiller.toString());
            logger.debug ("Response Template : " + responseTemplate.toString());
            while (keysResponseFromBiller.hasNext()) {
                try
                {
                    String key = (String) keysResponseFromBiller.next();
                    //                logger.debug ("(ChangeResp) key : " + key);
                    if (responseFromBiller.get(key) instanceof JSONObject) {
                        changeResponse(keyValue, value, (JSONObject) responseFromBiller.get(key), mapResponseToCore,
                                responseTemplate);
                    } else {
                        if (responseFromBiller.keySet().contains(keyValue)) {
                            if (mapResponseToCore.keySet().contains(responseTemplate.get(key).toString())) {
                                mapResponseToCore.put(responseTemplate.get(key).toString(),
                                        responseFromBiller.get(key));
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    logger.debug (TAG + "Exception while mapping response (loop) : " + e.getMessage());
                }
            }
            logger.debug ("(ChangeResp) Response to Core : " + mapResponseToCore.toString());
        }
        catch (Exception e)
        {
            logger.debug (TAG + "Error while mapping response : " + e.getMessage());
        }
    }

}