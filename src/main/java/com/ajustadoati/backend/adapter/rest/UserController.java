package com.ajustadoati.backend.adapter.rest;

import com.ajustadoati.backend.adapter.rest.dto.request.UserRequest;
import com.ajustadoati.backend.adapter.rest.dto.response.UserResponseDto;
import com.ajustadoati.backend.application.service.UserService;
import com.ajustadoati.backend.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequest userRequest) {

        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer userId){

        return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{username}/username")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username){

        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){

        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value="/{categoryId}/category")
    public ResponseEntity<List<UserResponseDto>> getAllByCategory(@PathVariable Long categoryId){
        Category category = Category.builder()
            .id(categoryId)
            .build();
        return new ResponseEntity<>(userService.getAllByCategory(category), HttpStatus.OK);
    }

}
