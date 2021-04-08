package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String show(@PathVariable String id, Model model) {
        Post post = findPost(id);
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String createComment(@PathVariable String id,
                                @Valid @ModelAttribute("comment") Comment comment,
                                BindingResult bindingResult,
                                HttpSession httpSession,
                                Model model) {

        Post post = findPost(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }

        User user = getUser(httpSession);
        if (user != null && post != null) {
            comment.setPost(post);
            comment.setUser(user);
            postService.writeComment(post, comment);
        }

        return "redirect:/post/{id}";
    }
}
