package com.example.interview.service;

import com.example.interview.model.Coin;
import com.example.interview.repository.CoinRepository;
import com.example.interview.utils.BasicUtil;
import com.example.interview.utils.CoinUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CoindeskServiceImpl implements CoindeskService{


    @Autowired
    private CoinUtil coinUtil;
    @Autowired
    private BasicUtil basicUtil;
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public String getJsonFromUrl() {
        return coinUtil.getCoinDeskAPi();
    }

    @Override
    public  String transDeskToInfo(){
        String json = this.getJsonFromUrl();
        String resJson = null;
        try {
            //String to Json
            JsonNode jsonNode = mapper.readTree(json);
            //get List from json
            Map bpiMap = mapper.convertValue(jsonNode.get("bpi"),Map.class);
            Set<String> coinTypes = bpiMap.keySet();
            List<Coin> coinList = coinUtil.parseCoinMap2List(jsonNode.get("bpi"),coinTypes);
            //get update Time
            String updTime = jsonNode.get("time").get("updated").asText();
            //composition json
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("updateTime",basicUtil.utc2LocalTime(updTime));
            map.put("bpi" , coinList);
            resJson = mapper.writeValueAsString(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resJson;
    }
}
