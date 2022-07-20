package com.example.interview.service;

import com.example.interview.model.Coin;
import com.example.interview.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoinService {

    Optional<Coin> getCoin(String name);

    void updateCoin(Coin coin);

    void saveCoin(Coin coin);

    void deleteCoin(String name);

}
