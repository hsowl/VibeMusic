package com.example.vibemusic.controller;

import com.example.vibemusic.domain.EventBoard;
import com.example.vibemusic.dto.EventBoardDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.EventBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
public class EventController {

    private final EventBoardService eventBoardService;

    @GetMapping("/event")
    public String Elist(Pageable pageable, Model model, PageRequestDTO pageRequestDTO) {

        int pageSize = 10;
        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);

        Page<EventBoard> eventPage = eventBoardService.Elist(pageable);

        model.addAttribute("eventPage", eventPage);

        if (pageRequestDTO.getPage() < 1) {
            pageRequestDTO.setPage(1);
        }

        PageResponseDTO<EventBoardDTO> responseDTO = eventBoardService.list(pageRequestDTO);

        model.addAttribute("eList", responseDTO);

//        boolean loggedIn = true;
//        model.addAttribute("loggedIn", loggedIn);

        return "event";
    }

    @GetMapping({"/eventread", "/eventmodify"})
    public void read(Long ebno, PageRequestDTO pageRequestDTO, Model model) {

        EventBoardDTO eventBoardDTO = eventBoardService.select(ebno);

        log.info(eventBoardDTO);

        model.addAttribute("dto", eventBoardDTO);
    }

    @GetMapping("/eventregister")
    public void registerGET() {

    }

    @PostMapping("/eventregister")
    public String registerPost(@Valid EventBoardDTO eventBoardDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("has errors~~~~~~~");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/eventregister";
        }

        log.info(eventBoardDTO);

        Long ebno = eventBoardService.register(eventBoardDTO);

        redirectAttributes.addFlashAttribute("result", ebno);

        return "redirect:/event";
    }

    @PostMapping("eventmodify")
    public String modify(@Valid EventBoardDTO eventBoardDTO,
                         BindingResult bindingResult,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {

        log.info("Event board modify post~~~~~~~~" + eventBoardDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors~~~~~~~~~~");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getAllErrors());

            redirectAttributes.addAttribute("ebno", eventBoardDTO.getEbno());

            return "redirect:/eventmodify?"+link;
        }

        eventBoardService.modify(eventBoardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("ebno", eventBoardDTO.getEbno());

        return "redirect:/eventread";
    }

    @PostMapping("/eventremove")
    public String remove(Long ebno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {

        log.info("Remove POST..."+ebno);
        log.info("link~~~~~~~~~~~~~~~===>"+ pageRequestDTO.getLink());

        String link = pageRequestDTO.getLink();
        eventBoardService.remove(ebno);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/event?"+link;
    }

}
