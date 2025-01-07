package com.dev.moviebookingsystem.bmt.service;

import com.dev.moviebookingsystem.bmt.dto.UserDto;
import com.dev.moviebookingsystem.bmt.exceptions.UserNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.UserMapper;
import com.dev.moviebookingsystem.bmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;


    public UserDto createUser(UserDto user) {
        return userMapper.mapEntityToDto(userRepo.save(userMapper.mapDtoToEntity(user)));
    }

    public UserDto getUserById(int id) {
        return userMapper.mapEntityToDto(userRepo.findById(id).orElseThrow(
            () -> new UserNotFoundException("User not found for id: " + id)
        ));
    }

}
