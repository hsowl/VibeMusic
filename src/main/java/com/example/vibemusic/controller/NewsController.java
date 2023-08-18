package com.example.vibemusic.controller;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.NewsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsService newsService;

    /**
     GET	Get 메소드는 서버로부터 정보를 가져올 때 사용이 됩니다.(Read)
     */
    @GetMapping("/newsRead")
    public void read(Long nNo, Model model){
        log.info("nNo=============> : {}", nNo);
        NewsDTO newsDTO = newsService.read1news(nNo);

        model.addAttribute("dto", newsDTO);
    }

    @GetMapping("/blog")
    public String list(Pageable pageable, Model model){

        // Set the number of items per page
        int pageSize = 5;
        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);

        // Retrieve a Page of news items using the NewsService
        Page<News> newsPage = newsService.list(pageable);

        // Add the Page of news items to the model
        model.addAttribute("newsPage", newsPage);

        // Return the view name for rendering
        return "blog";  // blog.html

    }

    /**
     POST	리소스를 생성할 때 사용이 됩니다.(Create)
     */




    /**
     PUT	리소스를 수정(update) 할 때 사용이 됩니다.(Update)
     */




    /**
     DELETE	리소스를 제거할 때 사용이 됩니다.(Delete)
     */

}
