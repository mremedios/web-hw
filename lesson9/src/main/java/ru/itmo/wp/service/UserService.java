package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.*;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.repository.RoleRepository;
import ru.itmo.wp.repository.TagRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * @noinspection FieldCanBeLocal, unused
     */
    private final RoleRepository roleRepository;
    private final TagRepository tagRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.roleRepository = roleRepository;
        for (Role.Name name : Role.Name.values()) {
            if (roleRepository.countByName(name) == 0) {
                roleRepository.save(new Role(name));
            }
        }
    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public void writePost(User user, PostCredentials postCredentials) {
        Post post = new Post();
        post.setTitle(postCredentials.getTitle());
        post.setText(postCredentials.getText());

        String[] stringsTags = postCredentials.getTags().split("\\s+");
//        Set<Tag> tags = Arrays.stream(stringsTags).filter((str) -> !str.isEmpty()).map(Tag::new).collect(Collectors.toSet());
        Set<Tag> tags = new HashSet<>();
        for (String name : stringsTags) {
            if (!name.isEmpty()) {
                if (tagRepository.countByName(name) == 0) {
                    tagRepository.save(new Tag(name));
                }
                Tag tag = tagRepository.findByName(name);
                tags.add(tag);
            }
        }

        post.setTags(tags);
        user.addPost(post);
        userRepository.save(user);
    }
}
