package com.ajustadoati.backend.application.service;

import com.ajustadoati.backend.adapter.rest.dto.request.UserRequest;
import com.ajustadoati.backend.adapter.rest.dto.response.UserResponseDto;
import com.ajustadoati.backend.adapter.rest.exception.UserNotFoundException;
import com.ajustadoati.backend.adapter.rest.mapper.UserMapper;
import com.ajustadoati.backend.adapter.rest.repository.CategoryRepository;
import com.ajustadoati.backend.adapter.rest.repository.LocationRepository;
import com.ajustadoati.backend.adapter.rest.repository.RoleRepository;
import com.ajustadoati.backend.adapter.rest.repository.UserRepository;
import com.ajustadoati.backend.domain.Category;
import com.ajustadoati.backend.domain.Location;
import com.ajustadoati.backend.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequest dto) {
        var roles = new HashSet<>(roleRepository.findAllById(dto.getRoleIds()));

        User user = User.builder()
            .name(dto.getName())
            .numberId(dto.getNumberId())
            .mobileNumber(dto.getMobileNumber())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .createdAt(Instant.now())
            .roles(roles)
            .build();

        User savedUser = userRepository.save(user);
        if (dto.getCategoryIds() != null && !dto.getCategoryIds()
            .isEmpty()) {
            var categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));
            savedUser.setCategories(categories);
            savedUser = userRepository.save(savedUser);
        }

        if (dto.getLocations() != null && !dto.getLocations()
            .isEmpty()) {
            User finalSavedUser = savedUser;
            List<Location> locations = dto.getLocations()
                .stream()
                .map(loc ->
                    Location.builder()
                        .latitude(loc.getLatitude())
                        .longitude(loc.getLongitude())
                        .user(finalSavedUser)
                        .timestamp(Instant.now())
                        .build()
                )
                .collect(Collectors.toList());
            savedUser.setLocations(locations);

            locationRepository.saveAll(locations);
        }

        return userMapper.toDto(savedUser);
    }

    public UserResponseDto getUserByUserId(Integer userId) {

        return userMapper.toDto(userRepository.findById(userId)
            .orElseThrow(()-> new UserNotFoundException("User Not Found")));
    }

    public List<UserResponseDto> getAll() {

        return userRepository.findAll()
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

    public List<UserResponseDto> getAllByCategory(Category category) {
        return userRepository.findByCategories(List.of(category))
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

}
