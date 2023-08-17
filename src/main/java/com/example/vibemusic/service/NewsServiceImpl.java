package com.example.vibemusic.service;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<News> list(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

//    @Override
//    public PageResponseDTO<NewsDTO> listWithPaging(PageRequestDTO pageRequestDTO) {
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("nNo");
//
//        Page<News> result = newsRepository.searchAll(types, keyword, pageable);
//
//        List<NewsDTO> dtoList = result.getContent().stream().map(news -> modelMapper.map(news, NewsDTO.class)).collect(Collectors.toList());
//
//        PageResponseDTO<NewsDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, dtoList, (int)result.getTotalElements());
//
//        return pageResponseDTO;
//    }


}
