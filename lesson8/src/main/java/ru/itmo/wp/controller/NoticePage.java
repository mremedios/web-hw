package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.service.NoticeService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String createNotice(Model model, HttpSession httpSession) {
        model.addAttribute("noticeForm", new NoticeCredentials());
        if (getUser(httpSession) == null) {
            return "redirect:/enter";
        }
        return "NoticePage";
    }

    @PostMapping("/notice")
    public String createNotice(@Valid @ModelAttribute("noticeForm") NoticeCredentials noticeForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }
        noticeService.save(noticeForm);
        putMessage(httpSession, "Successfully published");
        return "redirect:/notice";
    }
}
