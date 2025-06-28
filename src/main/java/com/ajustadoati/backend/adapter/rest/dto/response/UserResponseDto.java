package com.ajustadoati.backend.adapter.rest.dto.response;

import com.ajustadoati.backend.adapter.rest.dto.request.LocationDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
public class UserResponseDto {
    private Integer userId;
    private String name;
    private String email;
    private String username;
    private String mobileNumber;
    private Instant createdAt;
    private Set<String> roles;
    private Set<String> categories;
    private List<LocationDto> locations;
}
