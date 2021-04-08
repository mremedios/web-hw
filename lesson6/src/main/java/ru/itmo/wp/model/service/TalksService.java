package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalksRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.TalksRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class TalksService {
    private final TalksRepository talksRepository = new TalksRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void save(Talk talk) {
        talksRepository.save(talk);
    }

    public List<Talk> findById(long id) {
        return talksRepository.findById(id);
    }

    public void validateTalk(String talk, String targetUserIdString) throws ValidationException {
        if (Strings.isNullOrEmpty(targetUserIdString)) {
            throw new ValidationException("Choose a person");
        }

        long targetUserId = Long.parseLong(targetUserIdString);

        if (userRepository.find(targetUserId) == null) {
            throw new ValidationException("Incorrect target user");
        }
        if (Strings.isNullOrEmpty(talk)) {
            throw new ValidationException("Message can't be empty");
        }
        if (talk.length() > 240) {
            throw new ValidationException("Message can't be longer than 240 letters");
        }
    }
}
