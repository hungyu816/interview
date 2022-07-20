package com.example.interview;

import com.example.interview.model.Coin;
import com.example.interview.repository.CoinRepository;
import com.example.interview.service.CoindeskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootTest
class InterviewApplicationTests {

    @Autowired
    CoinRepository coinRepository;
    @Autowired
    private CoindeskService coindeskService;

    //查詢測試
    @Test
    void testGetCoinById() {
        Coin coin=coinRepository.findById("USD").orElse(null);
        System.out.println(coin.getCoinName()+","+coin.getChineseName()+","+String.valueOf(coin.getRate()));
    }

    //新增測試
    @Test
    void testAddCoin() {
        Coin coin=new Coin();
        coin.setCoinName("NTD");
        coin.setChineseName("新台幣");
        coin.setRate(123.45f);
        coinRepository.save(coin);
    }

    //更新測試
    @Test
    void testUpdateCoin() {
        Coin coin=coinRepository.findById("USD").orElse(null);
        System.out.println("更新前");
        System.out.println(coin.getCoinName()+","+coin.getChineseName()+","+String.valueOf(coin.getRate()));
        coin.setChineseName("美元修改中文名稱");
        coinRepository.save(coin);
        System.out.println("更新後");
        System.out.println(coin.getCoinName()+","+coin.getChineseName()+","+String.valueOf(coin.getRate()));

    }

    //刪除測試
    @Test
    void testDeleteCoin() {
        coinRepository.deleteById("USD");
    }


    //CoinDesk測試
    @Test
    void testGetCoinDesk() {
        String json = coindeskService.getJsonFromUrl();
        System.out.println(json);
    }
    //CoinDesk回傳測試
    @Test
    void testGetCoinDeskInfo() {
        String json = coindeskService.transDeskToInfo();
        System.out.println(json);
    }
}
