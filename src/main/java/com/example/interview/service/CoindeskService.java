package com.example.interview.service;

import org.springframework.stereotype.Service;
@Service
public interface CoindeskService {

    String getJsonFromUrl();

    String transDeskToInfo();
}
