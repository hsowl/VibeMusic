package com.example.vibemusic.crawlingTest;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.repository.VibeMusicRepository;
import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Optional;

@SpringBootTest
@Slf4j
class CrawlingTests {

    @Autowired
    private VibeMusicRepository vibeMusicRepository;

    @Test
    public void extractMp3Metadata(){
        File directory = new File("C:\\Users\\Admin\\Downloads\\Music");

        if(!directory.isDirectory()){
            System.out.println("Invalid directory path.");
            return;
        }

        File[] mp3Files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

        if(mp3Files == null || mp3Files.length == 0){
            System.out.println("No mp3 files found in the directory");
            return;
        }

        for(File mp3File : mp3Files){
            try{
                AudioFile audioFile = AudioFileIO.read(mp3File);
                Tag tag = audioFile.getTag();

                MP3AudioHeader audioHeader = (MP3AudioHeader) audioFile.getAudioHeader();

                Music music = Music.builder()
                        .m_title(tag.getFirst(FieldKey.TITLE))
                        .m_artist(tag.getFirst(FieldKey.ARTIST))
                        .m_genre(tag.getFirst(FieldKey.GENRE))
                        .m_playtime(audioHeader.getTrackLengthAsString())
                        .build();

                Music result = vibeMusicRepository.save(music);
                log.info("mno : {}",result.getM_no());

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void imgCrawling() {
        try {
            Long count = 1L;  // 초기값 설정
            for (int i = 1; i < 10; i++) {
                Document doc = Jsoup.connect("https://www.genie.co.kr/newest/song?GenreCode=hot&pg=" + i).get();
                Elements posters = doc.select("div.newest-list div.music-list-wrap table.list-wrap tbody tr.list td a.cover img");
                for (Element poster : posters) {
                    String imgUrl = poster.attr("src");

                    Optional<Music> byId = vibeMusicRepository.findById(count);
                    Music music = byId.orElseThrow();

                    music.updateImgUrl(imgUrl);

                    vibeMusicRepository.save(music);
                    count++;  // count 증가
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
