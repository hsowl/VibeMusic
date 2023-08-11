package com.example.vibemusic.service;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;


    @Override
    public NewsDTO read1news(Long nNo) {
        Optional<News> result = newsRepository.findById(nNo);
        News news = result.orElseThrow();
        NewsDTO newsDTO = modelMapper.map(news, NewsDTO.class);

        return newsDTO;
    }

//    @Override
//    public List list() {
//        List list = newsRepository.findAll();
//
//        log.info("list => {}", list);
//        return list;
//    }


}
