package com.app.ecom.service;

import com.app.ecom.dto.AddressDto;
import com.app.ecom.dto.UserRequestDto;
import com.app.ecom.dto.UserResponseDto;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.model.UserRole;
import com.app.ecom.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){
        this.repo=repo;
    }
    public UserResponseDto save(UserRequestDto userRequestDto)
    {
       User is_user=repo.findByEmail(userRequestDto.getEmail());
       User user = new User();
       if(is_user != null)
       {

          is_user.setName(userRequestDto.getName());
          is_user.setEmail(userRequestDto.getEmail());
            is_user.setAddress(userRequestDto.getAddress());
            is_user.setSalary(userRequestDto.getSalary()); 
            is_user.setRole(UserRole.USER); 
            User user1=repo.save(is_user);
            return userResponse(user1);
       }
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setAddress(userRequestDto.getAddress());
        user.setSalary(userRequestDto.getSalary());
        User savedUser = repo.save(user);
        return userResponse(savedUser);
    }
    public UserResponseDto getUser(Long id) 
    {
      Optional<User> user = repo.findById(id);
      if(user.isPresent())
      {
            return userResponse(user.get());
        }
        else
        {
            throw new RuntimeException("User not found with id: " + id);
      }
        
    }

    public UserResponseDto userResponse(User savedUser) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setRole(savedUser.getRole().name()); // Assuming UserRole is an enum
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setSalary(savedUser.getSalary());
        
        if(savedUser.getAddress() != null) {
            Address address = savedUser.getAddress();
            AddressDto addressDto = new AddressDto();
            addressDto.setStreet(address.getStreet());
            addressDto.setVillage(address.getVillage());    
            addressDto.setState(address.getState());
            addressDto.setZipcode(address.getZipcode());
            addressDto.setCountry(address.getCountry());
            userResponseDto.setAddress(addressDto);
        }
        userResponseDto.setCreatedAt(savedUser.getCreatedAt());
        userResponseDto.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponseDto;
    }
    public List<UserResponseDto> getAllUsers()
    {
       return  repo.findAll().stream().map(this::userResponse).toList();
    }
    public void deleteUser(Long id) {
        Optional<User> user = repo.findById(id);
        if(user.isPresent()) {
            repo.delete(user.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
