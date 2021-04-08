package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TalksPage extends Page {

    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request,view);
        if (getUser() == null) {
            request.getSession().setAttribute("message", "Only registered users can use Talks");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
        view.put("talks", talksService.findById(getUser().getId()));
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {

    }

    private void send(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String talk = request.getParameter("talk");
        String targetUserIdString = request.getParameter("targetUserId");
        talksService.validateTalk(talk, targetUserIdString);

        long targetUserId = Long.parseLong(request.getParameter("targetUserId"));
        talksService.save(new Talk(getUser().getId(), targetUserId, talk));
        action(request, view);
        throw new RedirectException("/talks");
    }
}
