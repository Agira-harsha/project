package com.agira.project.services;

import com.agira.project.Dtos.UserRequestDto;
import com.agira.project.Dtos.UserResponseDto;
import com.agira.project.ExceptionController.UserNotFoundException;
import com.agira.project.Utility.Mapper;
import com.agira.project.models.Role;
import com.agira.project.models.User;
import com.agira.project.repository.RoleRepository;
import com.agira.project.repository.UserReposiotry;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserReposiotry userReposiotry;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private Mapper mapper;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    public UserResponseDto createAccount(UserRequestDto userRequestDto) {
        User user = mapper.userRequestToUser(userRequestDto);
        Role role = roleRepository.findById(1L).orElseThrow(()-> new UserNotFoundException("user role not avaliabe"));
        user.getRoleList().add(role);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userReposiotry.save(user);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Welcome to Agira Cricket Portal..");
        String mailTemplate = String.format("Dear %s,\n\nWelcome to our platform!\n\nThank you for registering with us.\n\nBest regards", user.getUserName());
        simpleMailMessage.setText(mailTemplate);
        javaMailSender.send(simpleMailMessage);

        return mapper.userToResponseDto(user);

    }
    public ResponseEntity<UserResponseDto> getUser(long id) {
        User byId = userReposiotry.findById(id).get();
        UserResponseDto userResponseDto = mapper.userToResponseDto(byId);
        return ResponseEntity.ok(userResponseDto);
    }

    public ResponseEntity<UserResponseDto> deleteUser(long id) {
        User user = userReposiotry.findById(id).get();
        UserResponseDto userResponseDto = mapper.userToResponseDto(user);
        userReposiotry.delete(user);
        return ResponseEntity.ok(userResponseDto);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> all = userReposiotry.findAll();
        List<UserResponseDto> collect = all
                .stream().map(user -> mapper.userToResponseDto(user)).collect(Collectors.toList());
        return collect;
    }
    public UserResponseDto getUserByEmail(String email){
        User user = userReposiotry.findByEmail(email).get();
        return mapper.userToResponseDto(user);
    }

}
