package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.TalksService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    HttpServletRequest request;
    protected final UserService userService = new UserService();
    protected final EventService eventService = new EventService();
    protected final TalksService talksService = new TalksService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    public void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        setUser(view);
        setMessage(view);
        view.put("userCount", userService.findCount());
    }

    void setMessage(Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    public void setUser(Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    public User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
    }
}
