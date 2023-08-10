package com.example.vibemusic.service;

import com.example.vibemusic.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;



}
