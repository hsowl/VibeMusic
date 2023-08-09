package com.example.vibemusic.crawlingTest;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CrawlingNewsTests {

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void crawlingNews(){
        try{
            Long count = 1L; // 초기값 설정
            for(int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://ent.sbs.co.kr/news/menulist.do?code_category=SS03&plink=GNB&cooper=SBSENTERNEWS&pageIdx=" + i).get();
                Elements contents = doc.select("div.w_content_sublist div.w_news_list li a div.nwl_text");
                log.info("content : {}", contents);

                for(Element content : contents){
                    String newsText = content.text();

                    // 생성한 News 객체에 값을 설정
                    News news = new News();
                    news.setN_content(newsText);
                    // 다른 필요한 정보들도 설정

                    newsRepository.save(news);
                    count++;  // count 증가
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
