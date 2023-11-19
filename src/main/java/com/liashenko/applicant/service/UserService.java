package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.request.UserRequestDto;
import com.liashenko.applicant.dtos.response.UserResponseDto;
import com.liashenko.applicant.entity.User;
import com.liashenko.applicant.entity.UserSetting;
import com.liashenko.applicant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDto> findByChatId(Long chatId) {
        String stringChatId = chatId.toString();
        Optional<User> user = this.userRepository.findByChatId(stringChatId);

        return user.map(this::toDto);
    }

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User userFromDto = this.fromDto(userRequestDto);
        User user = this.userRepository.save(userFromDto);

        return this.toDto(user);
    }

    public List<UserResponseDto> getUserWithEnableNotification() {
        List<User> users = this.userRepository.findByEnabledNotification(true);

        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void updateIsShowNotification(String chatId, boolean isShowNotification) {
        Optional<User> optionalUser = this.userRepository.findByChatId(chatId);

        if (optionalUser.isEmpty()) return;

        User user = optionalUser.get();
        user.getUserSetting().setShowNotification(isShowNotification);
        this.userRepository.save(user);
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFistName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setChatId(user.getChatId());

        return userResponseDto;
    }

    public User fromDto(UserRequestDto userRequestDto) {
        User user = new User();

        user.setChatId(userRequestDto.chatId);
        user.setFistName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());

        UserSetting userSetting = new UserSetting();
        userSetting.setShowNotification(userRequestDto.isShowNotification());

        user.setUserSetting(userSetting);

        return user;
    }
}
