package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.ChartService;
import com.example.vibemusic.service.MusicService;
import com.example.vibemusic.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;
    private final MessageSource messageSource;
    private final ChartService chartService;


    @GetMapping({"/contact","/elements"})
    public void main() {

    }

    @GetMapping("/test")
    public void test(Model model) {
        MusicDTO musicDTO = musicService.readOne(1L);
        model.addAttribute("dto",musicDTO);
    }

    @GetMapping("/albums-store")
    public void list(PageRequestDTO pageRequestDTO, Model model, Pageable pageable){
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithPaging(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/read")
    public void readOne(Long no, Model model,Integer mPlayCount){

        if (mPlayCount == null) {
            mPlayCount = 0; // 기본값을 0으로 설정.
        }

        // Increase the view count for the news
        musicService.increaseViewCount(no);
        MusicDTO musicDTO = musicService.readOne(no);
        model.addAttribute("dto",musicDTO);
    }

    @GetMapping("/index")
    public String getIndexPage(Long no, PageRequestDTO pageRequestDTO, Model model, Locale locale) {
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithNewMusic(pageRequestDTO);

        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        String translatedDay = getMessageForDay(currentDay, locale);
        String translatedMessage = messageSource.getMessage("chart.recommendations", new Object[]{translatedDay}, locale);

        List<MusicDTO> recommendedMusic = new ArrayList<>();
        switch (currentDay) {
            case MONDAY:
                recommendedMusic = chartService.BalladeGenre(no);
                break;
            case TUESDAY:
                recommendedMusic = chartService.HipHopGenre(no);
                break;
            case WEDNESDAY:
                recommendedMusic = chartService.PopGenre(no);
                break;
            case THURSDAY:
                recommendedMusic = chartService.BalladeGenre(no);
                break;
            case FRIDAY:
                recommendedMusic = chartService.DanceGenre(no);
                break;
            case SATURDAY:
                recommendedMusic = chartService.PopGenre(no);
                break;
            case SUNDAY:
                recommendedMusic = chartService.BalladeGenre(no);
                break;
        }

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("recommendedMusic", recommendedMusic);
        model.addAttribute("currentDay", currentDay.toString());
        model.addAttribute("translatedMessage", translatedMessage);

        return "index";
    }

    private String getMessageForDay(DayOfWeek day, Locale locale) {
        return messageSource.getMessage(day.toString().toLowerCase(), null, day.toString(), locale);
    }





}

