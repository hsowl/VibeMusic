package com.example.vibemusic.controller;

import com.example.vibemusic.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
//@RequiredArgsConstructor
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }



}
