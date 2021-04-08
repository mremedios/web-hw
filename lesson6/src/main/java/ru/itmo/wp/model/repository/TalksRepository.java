package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface TalksRepository {
    void save(Talk talk);
    List<Talk>  findById(long id);
}
