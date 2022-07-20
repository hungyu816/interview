package com.example.interview.service;

import com.example.interview.model.Coin;
import com.example.interview.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinServiceImpl implements  CoinService{

    @Autowired
    CoinRepository coinRepository;


    @Override
    public Optional<Coin> getCoin(String name){
        return coinRepository.findById(name) ;
    }

    @Override
    public void updateCoin(Coin coin){
        coinRepository.save(coin);
    }
    @Override
    public void saveCoin(Coin coin) {
        coinRepository.save(coin);
    }

    @Override
    public void deleteCoin(String name) {
        coinRepository.deleteById(name);
    }
}
