package com.example.interview.repository;


import com.example.interview.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository  extends JpaRepository<Coin, String> {
}
