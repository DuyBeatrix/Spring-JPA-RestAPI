package com.buihuuduy.identity.service;

import com.buihuuduy.identity.DTO.request.UserCreationRequest;
import com.buihuuduy.identity.DTO.request.UserUpdateRequest;
import com.buihuuduy.identity.DTO.response.UserResponse;
import com.buihuuduy.identity.entity.User;
import com.buihuuduy.identity.exception.AppException;
import com.buihuuduy.identity.exception.ErrorCode;
import com.buihuuduy.identity.mapper.UserMapper;
import com.buihuuduy.identity.repository.RoleRepository;
import com.buihuuduy.identity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleRepository roleRepository;

    /**
     * The function is a service is to create user
     * @param userCreationRequest UserCreationRequest
     * @return User
     */
    public UserResponse createUser(UserCreationRequest userCreationRequest)
    {
        if(userRepository.existsByUsername(userCreationRequest.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXITST);
        }
        User user = userMapper.toUser(userCreationRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("User created has the ID is: {}", user.getId());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    /** The function is a service is to show all users */
    // @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * The function is a service is to get user by ID
     * @param id String
     * @return User
     */
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserByID(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    /**
     * The function is a service is to update user by ID
     * @param id String, userUpdateRequest
     * @return User
     */
    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest)
    {
        User user = userRepository.findById(id).orElseThrow(() ->
            new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, userUpdateRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    /** The function is a service is to delete user by ID */
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    public UserResponse getMyInfo()
    {
        String yourName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(yourName);
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return userMapper.toUserResponse(user);
    }
}
