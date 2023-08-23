package com.example.vibemusic.controller;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.service.ChartService;
import com.example.vibemusic.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
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
public class ChartController {

    private final MusicService musicService;
    private final MessageSource messageSource;
    private final ChartService chartService;
    
    
    @GetMapping("/chart")
    public String getMusicRecommendations(Long no, Model model, Locale locale) {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        String translatedDay = getMessageForDay(currentDay, locale);
        String translatedMessage = messageSource.getMessage("chart.recommendations", new Object[]{translatedDay}, locale);




        List<MusicDTO> recommendedMusic = new ArrayList<>();
        switch (currentDay) {
            case MONDAY:
                recommendedMusic = chartService.BalladGenre(no);
                break;
            case TUESDAY:
                recommendedMusic = chartService.HipHopGenre(no);
                break;
            case WEDNESDAY:
                recommendedMusic = chartService.PopGenre(no);
                break;
            case THURSDAY:
                recommendedMusic = chartService.BalladGenre(no);
                break;
            case FRIDAY:
                recommendedMusic = chartService.DanceGenre(no);
                break;
            case SATURDAY:
                recommendedMusic = chartService.PopGenre(no);
                break;
            case SUNDAY:
                recommendedMusic = chartService.BalladGenre(no);
                break;
        }

        model.addAttribute("recommendedMusic", recommendedMusic);
        model.addAttribute("currentDay", currentDay.toString());
        model.addAttribute("translatedMessage", translatedMessage);


        return "chart";
    }

    private String getMessageForDay(DayOfWeek day, Locale locale) {
        return messageSource.getMessage(day.toString().toLowerCase(), null, day.toString(), locale);
    }

}
