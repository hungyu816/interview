package com.example.interview.controller;

import com.example.interview.model.Coin;
import com.example.interview.service.CoinService;
import com.example.interview.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CoinService coinService;
    @Autowired
    private CoindeskService coindeskService;

    //查詢
    @GetMapping("/getCoin/{name}")
    public ResponseEntity<Object> getCoin(@PathVariable("name")String name) {
        if(coinService.getCoin(name)!=null) {
            return new ResponseEntity<Object>(coinService.getCoin(name), HttpStatus.OK);
        }
        return new ResponseEntity<Object>("123", HttpStatus.OK);
    }

    //新增
    @PostMapping("/addCoin")
    public ResponseEntity<Object> addCoin(@RequestBody Coin coin){
        coinService.saveCoin(coin);
        return new ResponseEntity<Object>("200", HttpStatus.OK);
    }

    //修改
    @PutMapping("/updCoin")
    public ResponseEntity<Object> updateCoin(@RequestBody Coin coin ){
        coinService.updateCoin(coin);
        return new ResponseEntity<Object>(coin, HttpStatus.OK);
    }

    //刪除
    @DeleteMapping("/delCoin/{name}")
    public ResponseEntity<Void> deleteCoin(@PathVariable("name")String name) {
        coinService.deleteCoin(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/getCoinDesk")
    public ResponseEntity getCoinDesk()
    {
        String json = coindeskService.getJsonFromUrl();
        return ResponseEntity.ok(json);
    }

    @GetMapping(value = "/getCoinInfo")
    public ResponseEntity getCoinInfo()
    {
        String json = coindeskService.transDeskToInfo();
        return ResponseEntity.ok(json);
    }
}
