package com.example.interview.utils;

import com.example.interview.model.Coin;
import com.example.interview.repository.CoinRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Component
public class CoinUtil {

    @Autowired
    private Environment env;
    @Autowired
    CoinRepository coinRepository;

    private ObjectMapper mapper = new ObjectMapper();;

    public String getCoinDeskAPi(){
        String url = env.getProperty("apiurl.coindeskAPI");
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public List<Coin> parseCoinMap2List(JsonNode node , Set<String> coinTypes){
        List<Coin> coinList = new ArrayList<>();
        for(String key : coinTypes){
            Map coinMap = mapper.convertValue(node.get(key),Map.class);
            Coin ori = coinRepository.findById(key).orElse(null);
            Coin coin = new Coin();
            coin.setChineseName(null==ori? "" : ori.getChineseName());
            coin.setCoinName(key);
            coin.setRate(Float.parseFloat(String.valueOf(coinMap.get("rate_float"))) );
            coinList.add(coin);
        }
        return coinList;
    }

}
