package com.app.ecom.controller;

import com.app.ecom.dto.UserRequestDto;
import com.app.ecom.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto)
    {
        return ResponseEntity.ok(userService.save(userRequestDto));

    }
    @GetMapping("getById")
    public ResponseEntity<?> getUserById(@RequestParam Long id)
    {
        return ResponseEntity.ok(userService.getUser(id));
    }
    @GetMapping()
    public ResponseEntity<?> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto userRequestDto)
    {
        return ResponseEntity.ok(userService.save(userRequestDto));
    }
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestParam Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
