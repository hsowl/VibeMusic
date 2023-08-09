package com.example.vibemusic.crawlingTest;

import com.example.vibemusic.repository.MusicRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CrawliingNewsTests {

    @Autowired
    private MusicRepository musicRepository;

    @Test
    public void crawlingNews(){
        try{
            Long count = 1L; // 초기값 설정
            for(int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements articles = doc.select("div.w_content_sublist div.w_news_list li a div.w_nwl_text div.nwl_text");
                log.info("articles : {}", articles);

//                vibeMusicRepository.save(n_news);
//                count++;  // count 증가
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
