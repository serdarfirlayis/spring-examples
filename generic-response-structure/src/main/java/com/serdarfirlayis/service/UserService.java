package com.serdarfirlayis.service;

import com.serdarfirlayis.entity.UserEntity;
import com.serdarfirlayis.exception.UserNotFoundException;
import com.serdarfirlayis.mapper.UserMapper;
import com.serdarfirlayis.model.User;
import com.serdarfirlayis.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Get username by id
     *
     * @param id user id
     * @return user name
     */
    public String getUserName(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userEntity.getName();
    }

    /**
     * Get all users with pagination
     *
     * @param page page number
     * @param size page size
     * @return list of users
     */
    public List<User> getAllUsers(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserEntity> userPage = userRepository.findAll(pageRequest);
        return userPage.getContent().stream()
                .map(userMapper::UserEntityToUser)
                .collect(Collectors.toList());
    }
}
