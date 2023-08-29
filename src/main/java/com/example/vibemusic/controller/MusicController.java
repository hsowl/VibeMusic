package com.example.vibemusic.controller;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.ChartService;
import com.example.vibemusic.service.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.MessageSource;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;
    private final MessageSource messageSource;
    private final ChartService chartService;


    @GetMapping({"/contact", "/elements"})
    public void main() {

    }

    @GetMapping("/test")
    public void test(Model model) {
        MusicDTO musicDTO = musicService.readOne(1L);
        model.addAttribute("dto", musicDTO);
    }

    @GetMapping("/albums-store")
    public void list(PageRequestDTO pageRequestDTO, Model model, Pageable pageable) {
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithPaging(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/read")
    public void readOne(Long no, Model model, Integer mPlayCount) {

        if (mPlayCount == null) {
            mPlayCount = 0; // 기본값을 0으로 설정.
        }

        // Increase the view count for the news
        musicService.increaseViewCount(no);
        MusicDTO musicDTO = musicService.readOne(no);
        model.addAttribute("dto", musicDTO);
    }

    @GetMapping("/index")
    public String getIndexPage(Long no, PageRequestDTO pageRequestDTO, Model model, Locale locale) throws Exception {
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithNewMusic(pageRequestDTO);

        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        String translatedDay = getMessageForDay(currentDay, locale);
        String translatedMessage = messageSource.getMessage("chart.recommendations", new Object[]{translatedDay}, locale);

        //////////////////// 날씨 api //////////////////////////////////
        String weatherApiData = restApiGetWeather();
        JSONObject weatherJson = new JSONObject(weatherApiData);

        List<MusicDTO> weatherMusic = new ArrayList<>();
        boolean isRainyWeather = false;
        boolean isCloudyWeather = false;
        boolean isColdWeather = false;
        boolean isHumidWeather = false;


        JSONObject result = weatherJson.getJSONObject("result");
        JSONObject response = result.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray itemArray = items.getJSONArray("item");

        System.out.println(items);

        int popValue = -1;
        String ptyValue = "";
        int rehValue = -1;
        int skyValue = -1;
        int tmpValue = -1;

        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject item = itemArray.getJSONObject(i);
            String category = item.getString("category");

            switch (category) {
                case "POP":
                    popValue = item.getInt("fcstValue");
                    break;
                case "PTY":
                    ptyValue = item.getString("fcstValue");
                    break;
                case "REH":
                    rehValue = item.getInt("fcstValue");
                    break;
                case "SKY":
                    skyValue = item.getInt("fcstValue");
                    break;
                case "TMP":
                    tmpValue = item.getInt("fcstValue");
                    break;
                // Add cases for other weather categories...
            }
        }

        final int RAIN_THRESHOLD = 60;
        final int CLOUDY_SKY_THRESHOLD = 3;
        final int COLD_TEMP_THRESHOLD = 10;

        if (popValue >= RAIN_THRESHOLD || ptyValue.equals("1")) {
            isRainyWeather = true;
            weatherMusic = chartService.DanceGenre(no);
        } else if (skyValue <= CLOUDY_SKY_THRESHOLD) {
            isCloudyWeather = true;
            weatherMusic = chartService.PopGenre(no);
        } else if (tmpValue <= COLD_TEMP_THRESHOLD) {
            isColdWeather = true;
            weatherMusic = chartService.BalladeGenre(no);
        } else if (rehValue >= 70) {
            isHumidWeather = true;
            weatherMusic = chartService.HipHopGenre(no);
        }
        //////////////////////////////////////////////////////

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
        model.addAttribute("weatherMusic", weatherMusic);

        if (isRainyWeather) {
            model.addAttribute("WeatherMessage", "비");
        } else if (isCloudyWeather) {
            model.addAttribute("WeatherMessage", "흐림");
        } else if (isColdWeather) {
            model.addAttribute("WeatherMessage", "추움");
        } else if (isHumidWeather) {
            model.addAttribute("WeatherMessage", "습함");
        }
        return "index";
    }

    private String getMessageForDay(DayOfWeek day, Locale locale) {
        return messageSource.getMessage(day.toString().toLowerCase(), null, day.toString(), locale);
    }


    public HashMap<String, Object> getDataFromJson(String url, String encoding, String type, String jsonStr) throws Exception {
        boolean isPost = false;

        if ("post".equals(type)) {
            isPost = true;
        } else {
            url = "".equals(jsonStr) ? url : url + "?request=" + jsonStr;
        }

        return getStringFromURL(url, encoding, isPost, jsonStr, "application/json");
    }

    public HashMap<String, Object> getStringFromURL(String url, String encoding, boolean isPost, String parameter, String contentType) throws Exception {
        URL apiURL = new URL(url);

        HttpURLConnection conn = null;
        BufferedReader br = null;
        BufferedWriter bw = null;

        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        try {
            conn = (HttpURLConnection) apiURL.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);

            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", contentType);
                conn.setRequestProperty("Accept", "*/*");
            } else {
                conn.setRequestMethod("GET");
            }

            conn.connect();

            if (isPost) {
                bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                bw.write(parameter);
                bw.flush();
                bw = null;
            }

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));

            String line = null;

            StringBuffer result = new StringBuffer();

            while ((line = br.readLine()) != null) result.append(line);

            ObjectMapper mapper = new ObjectMapper();

            resultMap = mapper.readValue(result.toString(), HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(url + " interface failed" + e.toString());
        } finally {
            if (conn != null) conn.disconnect();
            if (br != null) br.close();
            if (bw != null) bw.close();
        }

        System.out.println(resultMap);
        return resultMap;
    }

    public String restApiGetWeather() throws Exception {
        /*
            @ API LIST ~

            getUltraSrtNcst 초단기실황조회
            getUltraSrtFcst 초단기예보조회
            getVilageFcst 동네예보조회
            getFcstVersion 예보버전조회
        */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String currentDate = dateFormat.format(new Date());
        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst" + "?serviceKey=JzYvT6RQrXmtQCgsNWOH42YroAeLsBvabX5rjpwY6Gn8ujFPW%2FvT4rmYvsD55OxEhnCJHOZyGCB00AF3vAvI9Q%3D%3D" + "&dataType=JSON"            // JSON, XML
                + "&numOfRows=20"             // 페이지 ROWS
                + "&pageNo=1"                 // 페이지 번호
                + "&base_date=" + currentDate       // 발표일자
                + "&base_time=0500"           // 발표시각
                + "&nx=60"                    // 예보지점 X 좌표
                + "&ny=120";                  // 예보지점 Y 좌표

        HashMap<String, Object> resultMap = getDataFromJson(url, "UTF-8", "get", "");

        System.out.println("# RESULT : " + resultMap);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("result", resultMap);

        return jsonObj.toString();
    }
}





