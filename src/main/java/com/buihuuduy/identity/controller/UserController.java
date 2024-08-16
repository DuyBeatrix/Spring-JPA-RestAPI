package com.buihuuduy.identity.controller;

import com.buihuuduy.identity.DTO.request.UserCreationRequest;
import com.buihuuduy.identity.DTO.request.UserUpdateRequest;
import com.buihuuduy.identity.DTO.response.ApiResponse;
import com.buihuuduy.identity.DTO.response.UserResponse;
import com.buihuuduy.identity.entity.User;
import com.buihuuduy.identity.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * Controller handles the creation of a new user.
     *
     * @param userCreationRequest
     * The request containing user details for creating a new user.
     * This parameter is validated using javax.Validation annotations.
     * @return ApiResponse<User>
     * An ApiResponse object containing the created user and a status code.
     */
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest)
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationRequest));
        apiResponse.setCode(201);
        return apiResponse;
    }

    /**
     * Controller handles fetching all users.
     *
     * @return ApiResponse<List<User>>
     * An ApiResponse object containing a list of all users and a status code.
     */
    @GetMapping
    public ApiResponse<List<User>> getAllUsers()
    {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUsers());
        apiResponse.setCode(200);
        return apiResponse;
    }

    /**
     * Controller handles fetching a user by their ID.
     *
     * @param userId
     * The ID of the user to fetch.
     * @return ApiResponse<UserResponse>
     * An ApiResponse object containing the requested user and a status code.
     */
    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserByID(@PathVariable String userId)
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserByID(userId));
        apiResponse.setCode(200);
        return apiResponse;
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo()
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        apiResponse.setCode(200);
        return apiResponse;
    }

    /**
     * Controller handles updating a user.
     *
     * @param userId The ID of the user to update.
     * @param userUpdateRequest The request containing updated user details.
     * This parameter is validated using javax.Validation annotations.
     * @return ApiResponse<UserResponse>
     * An ApiResponse object containing the updated user and a status code.
     */
    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest)
    {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, userUpdateRequest));
        apiResponse.setCode(200);
        return apiResponse;
    }

    /**
     * Controller handles deleting a user.
     *
     * @param userId
     * The ID of the user to delete.
     * @return ApiResponse<Object>
     * An ApiResponse object containing a status code and a message indicating successful deletion.
     */
    @DeleteMapping("/{userId}")
    public ApiResponse deleteUser(@PathVariable String userId)
    {
        userService.deleteUser(userId);
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Successfully deleted user");
        return apiResponse;
    }
}
