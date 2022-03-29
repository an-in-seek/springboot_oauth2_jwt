package com.example.oauth2jwt.user.controller;

import com.example.oauth2jwt.user.dto.UserDto;
import com.example.oauth2jwt.user.entity.User;
import com.example.oauth2jwt.user.map.UserMapStruct;
import com.example.oauth2jwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapStruct userMapStruct;

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody UserDto userDto) {
        User user = userMapStruct.toEntity(userDto);
        userService.save(user);
        UserDto responseUserDto = userMapStruct.toDto(user);
        return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserDto userDto = userMapStruct.toDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
