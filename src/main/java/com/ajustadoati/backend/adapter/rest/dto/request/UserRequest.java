package com.ajustadoati.backend.adapter.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String numberId;
    private String mobileNumber;
    private String email;
    private String password;
    @NotNull
    private Set<Long> roleIds;
    private Set<Integer> categoryIds;
    private List<LocationDto> locations;
}

