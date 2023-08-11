package com.example.vibemusic.controller;

import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.service.NewsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping
//@RequiredArgsConstructor
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    /**
     GET	Get 메소드는 서버로부터 정보를 가져올 때 사용이 됩니다.(Read)
     */
    @GetMapping("/news/newsRead")
    public void read(Long nNo, Model model){
        log.info("nNo=============>"+nNo);
        NewsDTO newsDTO = newsService.read1news(nNo);

        model.addAttribute("dto", newsDTO);
    }

    @GetMapping("/blog")
    public void list(){
//        List list = newsService.list();
//        model.addAttribute("list", list);
//        log.info("News ======> : {}", list);

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
