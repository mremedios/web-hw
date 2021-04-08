package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.DisabledCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/all")
    public String users(HttpSession httpSession, Model model) {
        model.addAttribute("disabledForm", new DisabledCredentials());
        if (getUser(httpSession) == null) {
            return "redirect:/enter";
        }
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String updateStatus(@Valid @ModelAttribute("disabledForm") DisabledCredentials disabledForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "UsersPage";
        }
        if (getUser(httpSession) != null)
            userService.updateStatus(disabledForm);
        putMessage(httpSession, "Status changed");
        return "redirect:/users/all";
    }
}
